//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.cyrj.common.config;

import com.alibaba.fastjson.JSON;
import com.cyrj.common.db.DataSourceHolder;
import com.cyrj.common.exception.TokenException;
import com.cyrj.common.util.EncryptUtil;
import com.cyrj.common.util.IgnoreSecurity;
import com.cyrj.common.util.MD5Encoder;
import com.cyrj.common.util.Response;
import com.cyrj.main.pojo.PubDomainConfig;
import com.cyrj.main.service.PubDomainConfigService;
import com.cyrj.sys.mapper.DevelopmentMapper;
import com.cyrj.sys.pojo.Development;
import com.cyrj.sys.pojo.QdtenantInfo;
import com.cyrj.sys.service.QdtenantInfoService;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class SecurityAspect {
    public Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String tokenName = "X-Token";
    private static final String userName = "X-User";
    private static final String domainName = "X-Domain";
    @Autowired
    PubDomainConfigService pubDomainConfigService;
    @Autowired
    QdtenantInfoService qdtenantInfoService;
    @Autowired
    DevelopmentMapper developmentMapper;

    public SecurityAspect() {
    }

    @Around("execution (* com.cyrj.*.control..*.*(..))")
    public Object execute(ProceedingJoinPoint pjp) throws Throwable {
        DataSourceHolder.setDataSource((String)null);
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        if (this.accessTokenCheck(request)) {
            return pjp.proceed();
        } else {
            MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
            Method method = methodSignature.getMethod();
            String methodName = pjp.getSignature().getName();
            String className = pjp.getSignature().getDeclaringTypeName();
            this.getparamList(pjp);
            String tenantIdStr = request.getHeader("xcxCode");
            if (tenantIdStr != null && !tenantIdStr.equals("")) {
                String tenantCode = EncryptUtil.decryptionString(tenantIdStr).split("\\|")[0];
                DataSourceHolder.setDataSource(tenantCode);
            }

            tenantIdStr = request.getHeader("tenantCode");
            boolean isDbName = false;
            String token;
            if (tenantIdStr != null && !tenantIdStr.equals("")) {
                token = EncryptUtil.decryptionString(tenantIdStr).split("\\|")[0];
                QdtenantInfo tenantInfo = new QdtenantInfo();
                tenantInfo.setId(Integer.parseInt(token));
                tenantInfo.setDelflag(1);
                tenantInfo = (QdtenantInfo)this.qdtenantInfoService.getById(tenantInfo);
                if (tenantInfo != null && !StringUtils.isEmpty(tenantInfo.getDbName())) {
                    DataSourceHolder.setDataSource(tenantInfo.getDbName());
                    isDbName = true;
                }
            }

            token = request.getHeader("X-Token");
            String username = request.getHeader("X-User");
            boolean isToken = true;
            if (StringUtils.isEmpty(token) || StringUtils.isEmpty("X-User")) {
                isToken = false;
            }

            if (!RedisUtil.checkToken(username, token)) {
                isToken = false;
            }

            String domain;
            if (isToken && !isDbName) {
                domain = RedisUtil.getValueByToken("dbName", token);
                if (StringUtils.isNotEmpty(domain)) {
                    DataSourceHolder.setDataSource(domain);
                } else {
                    DataSourceHolder.setDataSource((String)null);
                }
            }

            if (!isToken && !isDbName) {
                domain = request.getHeader("X-Domain");
                if (!StringUtils.isEmpty(domain)) {
                    if (domain.indexOf("#") > -1) {
                        domain = domain.split("#")[0];
                    }

                    if (RedisUtil.checkRedis(domain)) {
                        String tokendbname = RedisUtil.getValueByToken("dbName", domain);
                        if (StringUtils.isNotEmpty(tokendbname)) {
                            DataSourceHolder.setDataSource(tokendbname);
                        }
                    } else if (domain.equals("wx.ysy123.cn")) {
                        DataSourceHolder.setDataSource((String)null);
                    } else {
                        DataSourceHolder.setDataSource((String)null);
                        PubDomainConfig pubDomainConfig = new PubDomainConfig();
                        pubDomainConfig.setDomainName(domain);
                        pubDomainConfig = (PubDomainConfig)this.pubDomainConfigService.getByObj(pubDomainConfig);
                        if (pubDomainConfig == null) {
                            Response response = new Response();
                            return response.failure("域名错误");
                        }

                        Map<String, Object> value = new HashMap();
                        value.put("tenantId", pubDomainConfig.getTenantId());
                        value.put("dbName", pubDomainConfig.getDbName());
                        RedisUtil.createRedis(domain, value);
                        DataSourceHolder.setDataSource(pubDomainConfig.getDbName());
                    }
                }
            }

            if (method.isAnnotationPresent(IgnoreSecurity.class) && (!method.isAnnotationPresent(IgnoreSecurity.class) || !((IgnoreSecurity)method.getAnnotation(IgnoreSecurity.class)).val())) {
                if (!isToken) {
                    domain = String.format("Token校验失败异常:token [%s] is invalid", token);
                    throw new TokenException(domain);
                } else {
                    Object result = pjp.proceed();
                    return result;
                }
            } else {
                return pjp.proceed();
            }
        }
    }

    private String getparamList(ProceedingJoinPoint pjp) {
        Object[] paramValues = pjp.getArgs();
        String[] paramNames = ((CodeSignature)pjp.getSignature()).getParameterNames();
        List<Map<String, Object>> map_list = new ArrayList();
        Map<String, Object> paramMap = null;

        for(int i = 0; i < paramNames.length; ++i) {
            if (paramValues[i] != null && !paramNames[i].equals("request")) {
                paramMap = new HashMap();
                paramMap.put("paramNames", paramNames[i]);
                Class className = paramValues[i].getClass();
                if (!className.equals(Integer.class) && !className.equals(Byte.class) && !className.equals(Long.class) && !className.equals(Double.class) && !className.equals(Float.class) && !className.equals(Character.class) && !className.equals(Short.class) && !className.equals(String.class) && !className.equals(Boolean.class)) {
                    paramMap.put("paramValues", this.getFiledsInfo(paramValues[i]));
                } else {
                    paramMap.put("paramValues", paramValues[i]);
                }

                map_list.add(paramMap);
            }
        }

        return JSON.toJSONString(map_list);
    }

    private Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter);
            Object value = method.invoke(o);
            return value;
        } catch (Exception var7) {
            return var7.getMessage();
        }
    }

    private List<Map<String, Object>> getFiledsInfo(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        List<Map<String, Object>> list = new ArrayList();
        Map<String, Object> infoMap = null;

        for(int i = 0; i < fields.length; ++i) {
            Object value = this.getFieldValueByName(fields[i].getName(), o);
            if (value != null) {
                infoMap = new HashMap();
                infoMap.put("name", fields[i].getName());
                infoMap.put("value", value);
                list.add(infoMap);
            }
        }

        return list;
    }

    private Boolean accessTokenCheck(HttpServletRequest request) {
        Map<String, Object> buf = new HashMap();
        buf.put("clientId", request.getParameter("client_id"));
        buf.put("accessToken", request.getHeader("access_token"));

        try {
            if (this.developmentMapper != null && request.getParameter("client_id") != null && request.getHeader("access_token") != null) {
                String encryptVal = null;
                String iptable = null;
                Development develop = this.developmentMapper.findByClientId(buf);
                if (develop != null && develop.getId() != null) {
                    encryptVal = develop.getEncryptVal();
                    iptable = develop.getIptable();
                    String[] tokens = EncryptUtil.decryptionString(buf.get("accessToken").toString()).split("\\|");
                    if (tokens.length > 0 && MD5Encoder.validPassword(tokens[0], encryptVal)) {
                        if (iptable != null && !iptable.isEmpty() && iptable.indexOf(request.getRemoteAddr()) == -1) {
                            return false;
                        }

                        if (develop.getBindService() != null && develop.getBindService().length() > 0) {
                            Map modulename = this.developmentMapper.findByDevelopService(develop.getBindService());
                            if (modulename == null || modulename.isEmpty()) {
                                return false;
                            }

                            if (modulename.get("modulePath").toString().indexOf(request.getRequestURI()) == -1) {
                                return false;
                            }
                        }

                        return true;
                    }
                }

                return false;
            }
        } catch (Exception var8) {
            var8.printStackTrace();
            return false;
        }

        return false;
    }
}
