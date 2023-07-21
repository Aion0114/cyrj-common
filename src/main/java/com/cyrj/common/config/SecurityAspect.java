package com.cyrj.common.config;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.cyrj.common.pojo.ProjectException;
import com.cyrj.common.util.*;
import com.cyrj.sys.mapper.DevelopmentMapper;
import com.cyrj.sys.pojo.Development;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.cyrj.common.db.DataSourceHolder;
import com.cyrj.common.pojo.InterfaceLog;
import com.cyrj.main.pojo.PubDomainConfig;
import com.cyrj.main.service.PubDomainConfigService;
import com.cyrj.sys.pojo.QdtenantInfo;
import com.cyrj.sys.service.QdtenantInfoService;

@Aspect
@Component
public class SecurityAspect {
	
	public Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String tokenName = "X-Token";
    private static final String userName = "X-User";
    private static final String domainName = "X-Domain";
    private static final String dataBaseName = "cyrj_information_base";
    
    @Autowired
    PubDomainConfigService pubDomainConfigService;
    
    @Autowired
    QdtenantInfoService qdtenantInfoService;

    @Autowired
    DevelopmentMapper developmentMapper;

    @Autowired
    RestHighLevelClient restHighLevelClient;
	

    @Around("execution (* com.cyrj.*.control..*.*(..))")
    public Object execute(ProceedingJoinPoint pjp) throws Throwable{
        Object result;
        String arg = getparamList(pjp);

        HttpServletRequest request =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String url = request.getRequestURI();
        boolean masterData = url.indexOf("/dataEnd/") >- 1;
        //代理的是哪一个方法
        String methodName = pjp.getSignature().getName();
//        AOP代理类的名字
        String className = pjp.getSignature().getDeclaringTypeName();

        String methodNames = className + "." + methodName;
        StringBuffer methodNameCN = new StringBuffer();
        try {
            //切换主库
            DataSourceHolder.setDataSource(null);

            // 校验access_token
            if(accessTokenCheck(request)) {
                if(masterData){
                    DataSourceHolder.setDataSource(dataBaseName);
                }
                return pjp.proceed();
            }

            // 从切点上获取目标方法
            MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
            Method method = methodSignature.getMethod();
            Class<?> clazz = method.getDeclaringClass();
            Api api = clazz.getAnnotation(Api.class);
            if(api != null){
                methodNameCN.append(api.description());
            }
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            if(apiOperation != null){
                methodNameCN.append("==>").append(apiOperation.value());
            }

            long beginTime = System.currentTimeMillis();


            // 从 request header 中获取当前 token
            String tenantIdStr = request.getHeader("xcxCode");
            if(tenantIdStr != null && !tenantIdStr.equals("")){
                String tenantCode = EncryptUtil.decryptionString(tenantIdStr).split("\\|")[0];
                DataSourceHolder.setDataSource(tenantCode);
            }

            String token = request.getHeader(tokenName);
            String username = request.getHeader(userName);

            boolean isToken = true;

            if(StringUtils.isEmpty(token) || StringUtils.isEmpty(userName)){
                isToken = false;
            }

            // 检查 token 有效性
            if (!RedisUtil.checkToken(username,token)) {
                isToken = false;
            }

            boolean isDbName = false;

            String operator = "";
            if(isToken) {

                String dbname = RedisUtil.getValueByToken("dbName",token);
                JSONObject valueObject = RedisUtil.getToken(username, token);
                if (valueObject != null && valueObject.containsKey("employeeName")) {
                    operator = valueObject.getString("employeeName");
                }

                if(StringUtils.isNotEmpty(dbname)) {
                    DataSourceHolder.setDataSource(dbname);
                    isDbName = true;
                } else {
                    DataSourceHolder.setDataSource(null);
                }
            }
            if(!isDbName) {
                tenantIdStr = request.getHeader("tenantCode");
                if (tenantIdStr != null && !tenantIdStr.equals("")) {
                    String tenantId = EncryptUtil.decryptionString(tenantIdStr).split("\\|")[0];
                    if(StringUtils.isNotBlank(tenantId))
                    {
                        QdtenantInfo tenantInfo = new QdtenantInfo();
                        tenantInfo.setId(Integer.parseInt(tenantId));
                        tenantInfo.setDelflag(1);
                        tenantInfo = qdtenantInfoService.getById(tenantInfo);
                        if (tenantInfo != null && !StringUtils.isEmpty(tenantInfo.getDbName())) {
                            DataSourceHolder.setDataSource(tenantInfo.getDbName());
                            isDbName = true;
                        }
                    }
                }
            }

            if(!isToken && !isDbName) {
                String domain = request.getHeader(domainName);
                if(!StringUtils.isEmpty(domain)) {

                    if (domain.contains("#")) {
                        domain = domain.split("#")[0];
                    }

                    if (RedisUtil.checkRedis(domain)) {
                        String tokendbname = RedisUtil.getValueByToken("dbName", domain);
                        if (StringUtils.isNotEmpty(tokendbname)) {
                            DataSourceHolder.setDataSource(tokendbname);
                        }
                    } else if (domain.equals("wx.ysy123.cn")) {
                        DataSourceHolder.setDataSource(null);
                    } else {
                        DataSourceHolder.setDataSource(null);
                        PubDomainConfig pubDomainConfig = new PubDomainConfig();
                        pubDomainConfig.setDomainName(domain);
                        pubDomainConfig = pubDomainConfigService.getById(pubDomainConfig);
                        // 域名错误
                        if (pubDomainConfig == null) {
                            Response response = new Response();
                            return response.failure("域名错误");
                        }
                        Map<String, Object> value = new HashMap<String, Object>();
                        value.put("tenantId", pubDomainConfig.getTenantId());
                        value.put("dbName", pubDomainConfig.getDbName());
                        RedisUtil.createRedis(domain, value);
                        DataSourceHolder.setDataSource(pubDomainConfig.getDbName());
                    }
                }
            }

            // 索引文档
            InterfaceLog interfaceLog = new InterfaceLog();
            interfaceLog.setDbName(DataSourceHolder.getDataSource());
            interfaceLog.setMethodArg(arg);
            interfaceLog.setUrl(url);
            interfaceLog.setToken(token);
            interfaceLog.setServerIp(NetUtil.getIpAddress());
            interfaceLog.setMethodName(methodNames);
            interfaceLog.setEndTime(new Date());
            interfaceLog.setOperator(operator);
            interfaceLog.setMethodNameCN(methodNameCN.toString());
            // 若目标方法忽略了安全性检查，则直接调用目标方法
            if ((!method.isAnnotationPresent(IgnoreSecurity.class)) || (method.isAnnotationPresent(IgnoreSecurity.class) &&
                    method.getAnnotation(IgnoreSecurity.class).val())) {
                if (!method.isAnnotationPresent(IgnoreLog.class) || !method.getAnnotation(IgnoreLog.class).val()) {
                    if (!methodNameCN.toString().equals("公共接口==>在线检测")
                            && !methodNameCN.toString().equals("拣货任务主表接口==>查找拣货任务主表列表")
                            && !methodNameCN.toString().equals("拣货任务主表接口==>自动打印")
                            && !methodNameCN.toString().equals("商品档案接口==>开单时获取商品列表")) {
                        long endTime = System.currentTimeMillis();
                        long elapsedTime = endTime - beginTime;
                        interfaceLog.setElapsedTime(elapsedTime);
                        HandleThreadSyncEs thread = new HandleThreadSyncEs(interfaceLog);
                        thread.start();
                    }
                }
                if(masterData){
                    DataSourceHolder.setDataSource(dataBaseName);
                }
                return pjp.proceed();
            }

            if(!isToken){
                String message = String.format("Token校验失败异常:token [%s] is invalid", token);
                Response response = new Response();
                return response.failure(message);
            }

            if(masterData){
                DataSourceHolder.setDataSource(dataBaseName);
            }
            result = pjp.proceed();
            if (!method.isAnnotationPresent(IgnoreLog.class) || !method.getAnnotation(IgnoreLog.class).val()) {
                if(!methodNameCN.toString().equals("商品档案接口==>开单时获取商品列表")){
                    long endTime = System.currentTimeMillis();
                    long elapsedTime = endTime - beginTime;
                    interfaceLog.setElapsedTime(elapsedTime);
                    HandleThreadSyncEs thread = new HandleThreadSyncEs(interfaceLog);
                    thread.start();
                }
            }


        } catch (Exception throwable) {
            result = handlerException(url,arg,throwable);
        }

        return result;
    }

    class HandleThreadSyncEs extends Thread {
        private InterfaceLog interfaceLog;

        public HandleThreadSyncEs(InterfaceLog interfaceLog) {
            this.interfaceLog = interfaceLog;
        }

        @Override
        public void run() {
            try {
                // 索引文档
                Map<String, Object> jsonMap = new HashMap<String, Object>();
                jsonMap.put("elapsedTime", interfaceLog.getElapsedTime());
                jsonMap.put("endTime", new Date());
                jsonMap.put("methodName", interfaceLog.getMethodName());
                jsonMap.put("serverIp", interfaceLog.getServerIp());
                jsonMap.put("url", interfaceLog.getUrl());
                jsonMap.put("token", interfaceLog.getToken());
                jsonMap.put("methodNameCN",interfaceLog.getMethodNameCN());
                jsonMap.put("operator",interfaceLog.getOperator());
                if(interfaceLog.getMethodArg() != null && interfaceLog.getMethodArg().length()>1000)
                {
                    jsonMap.put("methodArg", interfaceLog.getMethodArg().substring(0,1000));
                }else{
                    jsonMap.put("methodArg", interfaceLog.getMethodArg());
                }
                jsonMap.put("dbName", interfaceLog.getDbName());
                IndexRequest indexRequest = new IndexRequest("b2_es_logs1", "_doc", UUIDUtil.generateUpperGUID())
                        .source(jsonMap);
                // 获取响应结果
                restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

            }catch(Exception e) {
                System.out.println("elasticsearch插入错误："+e.getMessage());
            }
        }
    }

    /**
     * @Description: 处理接口调用异常
     */
    private Object handlerException(String url,String arg, Throwable e) {
        // 得到异常棧的首个元素
        StackTraceElement[] elements = e.getStackTrace();
        int len = elements.length;
        for(int i=0; i<len;i++)
        {
            StackTraceElement stackTraceElement = elements[i];
            if(stackTraceElement.toString().indexOf("com.cyrj") > -1)
            {
                ProjectException projectException = new ProjectException();
                projectException.setDbName(DataSourceHolder.getDataSource());
                projectException.setMethodArg(arg);
                projectException.setUrl(url);
                projectException.setExceptionTime(new Date());
                projectException.setMessage("错误："+ e.toString() +",相关类"+stackTraceElement.toString()+";");
                System.out.println(projectException.getMessage());

                HandleThreadSyncException thread = new HandleThreadSyncException(projectException);
                thread.start();
                break;
            }
            if(i == len-1){
                ProjectException projectException = new ProjectException();
                projectException.setDbName(DataSourceHolder.getDataSource());
                projectException.setMethodArg(arg);
                projectException.setUrl(url);
                projectException.setExceptionTime(new Date());
                projectException.setMessage("错误："+ e.toString());
                System.out.println(projectException.getMessage());
                HandleThreadSyncException thread = new HandleThreadSyncException(projectException);
                thread.start();
            }
        }

        Response response = new Response();
        return response.failure("网络异常，请重试");
    }

    class HandleThreadSyncException extends Thread {
        private ProjectException projectException;

        public HandleThreadSyncException(ProjectException projectException) {
            this.projectException = projectException;
        }

        @Override
        public void run() {
            try {
                // 索引文档
                Map<String, Object> jsonMap = new HashMap<String, Object>();

                if(projectException.getMessage() != null && projectException.getMessage().length() > 1000)
                {
                    jsonMap.put("message", projectException.getMessage().substring(0,1000));
                }else{
                    jsonMap.put("message", projectException.getMessage());
                }
                jsonMap.put("exceptionTime", new Date());
                jsonMap.put("url", projectException.getUrl());
                jsonMap.put("methodArg", projectException.getMethodArg());
                if(projectException.getMethodArg() != null && projectException.getMethodArg().length()>1000)
                {
                    jsonMap.put("methodArg", projectException.getMethodArg().substring(0,1000));
                }else{
                    jsonMap.put("methodArg", projectException.getMethodArg());
                }
                jsonMap.put("dbName", projectException.getDbName());
                IndexRequest indexRequest = new IndexRequest("b2_es_exception1", "_doc", UUIDUtil.generateUpperGUID())
                        .source(jsonMap);

                // 获取响应结果
                restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            }catch(Exception e) {
                System.out.println("elasticsearch插入错误："+e.getMessage());
            }
        }
    }


    
    
    /*************工具方法开始****************
     * 
     * @Title: getparamList
     * @Description: 获取参数列表
     * @param pjp
     * @return String 返回类型
     * @throws
     */
    private String getparamList(ProceedingJoinPoint pjp) {
        Object[] paramValues = pjp.getArgs();
        String[] paramNames = ((CodeSignature) pjp.getSignature())
                .getParameterNames();

        List<Map<String, Object>> map_list = new ArrayList<Map<String, Object>>();

        Map<String, Object> paramMap = null;
        
        for (int i = 0; i < paramNames.length; i++) {
            if (paramValues[i] != null && !paramNames[i].equals("request")) {
            	
            	paramMap = new HashMap<String, Object>();
            	paramMap.put("paramNames", paramNames[i]);
            	Class className = paramValues[i].getClass();
                if (className.equals(java.lang.Integer.class) ||
                    className.equals(java.lang.Byte.class) ||
                    className.equals(java.lang.Long.class) ||
                    className.equals(java.lang.Double.class) ||
                    className.equals(java.lang.Float.class) ||
                    className.equals(java.lang.Character.class) ||
                    className.equals(java.lang.Short.class) ||
                    className.equals(java.lang.String.class) ||
                    className.equals(java.lang.Boolean.class)) {
                	paramMap.put("paramValues",paramValues[i]);
                }else {
                	paramMap.put("paramValues",getFiledsInfo(paramValues[i]));
                }
                map_list.add(paramMap);
            }
        }
        return JSON.toJSONString(map_list);
    }

    /***
     * 
     * @Title: getFieldValueByName
     * @Description: 根据属性名获取属性值
     * @param fieldName
     * @param o
     * @return Object 返回类型
     * @throws
     */
    private Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /***
     * 
     * @Title: getFiledsInfo
     * @Description: 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
     * @param o
     * @return List<Map<String,Object>> 返回类型
     * @throws
     */
    private List<Map<String, Object>> getFiledsInfo(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> infoMap = null;
        for (int i = 0; i < fields.length; i++) {
            Object value = getFieldValueByName(fields[i].getName(), o);
            if (value != null) {
                infoMap = new HashMap<String, Object>();
                infoMap.put("name", fields[i].getName());
                infoMap.put("value", value);
                list.add(infoMap);
            }
        }
        return list;
    }

    /***
     * 在aop加入判断access_token的验证
     * 包含access_token及clientId且验证成功就可以访问接口，但只能访问指定的接口
     */
    private boolean accessTokenCheck(HttpServletRequest request){

        Map<String,Object> buf = new HashMap<>();
        buf.put("clientId", request.getParameter("client_id") );
        buf.put("accessToken", request.getHeader("access_token") );
        try{
            if(developmentMapper !=null && request.getParameter("client_id")!=null && request.getHeader("access_token")!=null) {
                String encryptVal = null;
                String iptable = null;
                Development develop = developmentMapper.findByClientId(buf);
                if(develop!=null && develop.getId()!=null) {
                    encryptVal = develop.getEncryptVal();
                    iptable = develop.getIptable();
                    String[] tokens = EncryptUtil.decryptionString(buf.get("accessToken").toString()).split("\\|");
                    if(tokens.length > 0 && MD5Encoder.validPassword(tokens[0], encryptVal)){ //获取cookie中的accessToken加密验证
                        //IP白名单验证
                        if(iptable!=null && !iptable.isEmpty()){
                            if(iptable.indexOf(request.getRemoteAddr()) == -1) {
                                return false;
                            }
                        }
                        // 验证路径
                        if(develop.getBindService() != null && develop.getBindService().length()>0) {
                            int cont = developmentMapper.findByDevelopService(develop.getBindService(),request.getRequestURI());
                            if ( cont > 0) {
                                return true;
                            }
                            return false;
                        }
                        return true;
                    }
                }
                return false;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }
}
