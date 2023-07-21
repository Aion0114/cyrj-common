package com.cyrj.common.control;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.cyrj.common.db.DataSourceHolder;
import com.cyrj.common.enumeration.DeleteFlag;
import com.cyrj.common.util.UUIDUtil;
import com.cyrj.language.mapper.LangBasicsMapper;
import com.cyrj.language.pojo.LangBasics;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.cyrj.common.config.RedisUtil;
import com.cyrj.common.constant.SystemKey;
import com.cyrj.common.util.Response;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseController {

	@Autowired
	LangBasicsMapper langBasicsMapper;

	@Autowired
	RestHighLevelClient restHighLevelClient;


	public Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public String getUser(HttpServletRequest request) {
		String userName = request.getHeader("X-User");
		return userName;
	}


	public String getToken(HttpServletRequest request) {
		String token = request.getHeader("X-Token");
		return token;
	}
	
	public String getDomain(HttpServletRequest request) {
		String domain = request.getHeader("X-Domain");
		if(StringUtils.isEmpty(domain))
        {
        	domain = "http://test.zstb.cn";
        }
		if(domain.indexOf("#")>-1)
        {
        	domain = domain.split("#")[0];
        }
		return domain;
	}
	
	
	public String getDbName(HttpServletRequest request) {
		JSONObject valueObject = getJson(request);
		String dbName = "";
		if (valueObject != null) {
			dbName = valueObject.getString("dbName");
		}else {
			valueObject = RedisUtil.getRedis(getDomain(request));
			if(valueObject != null)
			{
				dbName = valueObject.getString("dbName");
			}
		}
		return dbName;
	}

	public String getSystemType(HttpServletRequest request) {
		JSONObject valueObject = getJson(request);
		String systemType = "";
		if (valueObject != null) {
			systemType = valueObject.getString("systemType");
		}else {
			valueObject = RedisUtil.getRedis(getDomain(request));
			if(valueObject != null)
			{
				systemType = valueObject.getString("systemType");
			}
		}
		return systemType;
	}
	
	public JSONObject getJson(HttpServletRequest request)
	{
		String username = getUser(request);
		String token = getToken(request);
		JSONObject valueObject = RedisUtil.getToken(username, token);
		return valueObject;
	}
	
	public Integer getMemberId(HttpServletRequest request) {
		JSONObject valueObject = getJson(request);
		Integer tokenTenantId = -1;
		if (valueObject != null) {
			tokenTenantId = valueObject.getInteger("memberId");
		}
		return tokenTenantId;
	}
	
	public Integer getUserId(HttpServletRequest request) {
		JSONObject valueObject = getJson(request);
		Integer tokenTenantId = -1;
		if (valueObject != null) {
			tokenTenantId = valueObject.getInteger("userId");
		}
		return tokenTenantId;
	}
	
	public Integer getKhId(HttpServletRequest request) {
		JSONObject valueObject = getJson(request);
		Integer tokenTenantId = -1;
		if (valueObject != null) {
			tokenTenantId = valueObject.getInteger("khId");
		}
		return tokenTenantId;
	}
	

	public Integer getTenantId(HttpServletRequest request) {
		JSONObject valueObject = getJson(request);
		Integer tokenTenantId = -1;
		if (valueObject != null) {
			tokenTenantId = valueObject.getInteger("tenantId");
		}else {
			valueObject = RedisUtil.getRedis(getDomain(request));
			if(valueObject != null)
			{
				tokenTenantId = valueObject.getInteger("tenantId");
			}
		}
		return tokenTenantId;
	}

	public String getTenantName(HttpServletRequest request) {
		JSONObject valueObject = getJson(request);
		String tokenTenantName = "";
		if (valueObject != null) {
			tokenTenantName = valueObject.getString("tenantName");
		}else {
			valueObject = RedisUtil.getRedis(getDomain(request));
			if(valueObject != null)
			{
				tokenTenantName = valueObject.getString("tenantName");
			}
		}
		return tokenTenantName;
	}
	
	public Integer getEmployeeId(HttpServletRequest request) {
		JSONObject valueObject = getJson(request);
		Integer tokenTenantId = -1;
		if (valueObject != null) {
			tokenTenantId = valueObject.getInteger("employeeId");
		}
		return tokenTenantId;
	}
	
	public String getEmployeeName(HttpServletRequest request) {
		JSONObject valueObject = getJson(request);
		String employeeName = "";
		if (valueObject != null) {
			employeeName = valueObject.getString("employeeName");
		}
		return employeeName;
	}


	public String getUserCustomerLabelAuth(HttpServletRequest request) {
		JSONObject valueObject = getJson(request);
		String userCustomerLabelAuth = "";
		if (valueObject != null) {
			userCustomerLabelAuth = valueObject.getString("userCustomerLabelAuth");
		}
		return userCustomerLabelAuth;
	}

	public String getUserGoodsLabelAuth(HttpServletRequest request) {
		JSONObject valueObject = getJson(request);
		String userGoodsLabelAuth = "";
		if (valueObject != null) {
			userGoodsLabelAuth = valueObject.getString("userGoodsLabelAuth");
		}
		return userGoodsLabelAuth;
	}

	public String getUserSupplierLabelAuth(HttpServletRequest request) {
		JSONObject valueObject = getJson(request);
		String userSupplierLabelAuth = "";
		if (valueObject != null) {
			userSupplierLabelAuth = valueObject.getString("userSupplierLabelAuth");
		}
		return userSupplierLabelAuth;
	}


	//查询进价权限  banSeOtherDocStatus
	public String getselXpriceStatus(HttpServletRequest request) {
		JSONObject valueObject = getJson(request);
		String selXpriceStatus = "";
		if (valueObject != null) {
			selXpriceStatus = valueObject.getString("selXpriceStatus");
		}
		return selXpriceStatus;
	}
	//查询利润权限 findPubSysOtherCredit
	public String getselProfitStatus(HttpServletRequest request) {
		JSONObject valueObject = getJson(request);
		String selProfitStatus = "";
		if (valueObject != null) {
			selProfitStatus = valueObject.getString("selProfitStatus");
		}
		return selProfitStatus;
	}
	//禁止查看他人单据权限
	public String getbanSeOtherDocStatus(HttpServletRequest request) {
		JSONObject valueObject = getJson(request);
		String selProfitStatus = "";
		if (valueObject != null) {
			selProfitStatus = valueObject.getString("banSeOtherDocStatus");
		}
		return selProfitStatus;
	}

	//禁止查看首页数据
	public String getselHomePageStatus(HttpServletRequest request) {
		JSONObject valueObject = getJson(request);
		String selProfitStatus = "";
		if (valueObject != null) {
			selProfitStatus = valueObject.getString("selHomePageStatus");
		}
		return selProfitStatus;
	}

	//禁止查看批量更新面价
	public String getselBatchFacePriStatus(HttpServletRequest request) {
		JSONObject valueObject = getJson(request);
		String selProfitStatus = "";
		if (valueObject != null) {
			selProfitStatus = valueObject.getString("selBatchFacePriStatus");
		}
		return selProfitStatus;
	}

	//查看成本
	public String getselCostPriceStatus(HttpServletRequest request) {
		JSONObject valueObject = getJson(request);
		String selProfitStatus = "";
		if (valueObject != null) {
			selProfitStatus = valueObject.getString("selCostPriceStatus");
		}
		return selProfitStatus;
	}

	//查询信用额权限
	public String getselCreditStatus(HttpServletRequest request) {
		JSONObject valueObject = getJson(request);
		String selProfitStatus = "";
		if (valueObject != null) {
			selProfitStatus = valueObject.getString("selCreditStatus");
		}
		return selProfitStatus;
	}

	//查询业务可以查询日期
	public String getDateStartAuth(HttpServletRequest request) {
		JSONObject valueObject = getJson(request);
		String dateStartAuth = "";
		if (valueObject != null) {
			dateStartAuth = valueObject.getString("dateStartAuth");
		}
		return dateStartAuth;
	}

	//查询业务可以查询天数
	public Integer getUserOtherSelDayNum(HttpServletRequest request) {
		JSONObject valueObject = getJson(request);
		Integer userOtherSelDayNum = -1;
		if (valueObject != null) {
			userOtherSelDayNum = valueObject.getInteger("userOtherSelDayNum");
		}
		return userOtherSelDayNum;
	}

	//用户单据优惠最高额度
	public BigDecimal getUserDiscountSelAmount(HttpServletRequest request) {
		JSONObject valueObject = getJson(request);
		BigDecimal userDiscountSelAmount = BigDecimal.ZERO;
		if (valueObject != null) {
			userDiscountSelAmount = valueObject.getBigDecimal("userDiscountSelAmount");
		}
		return userDiscountSelAmount;
	}

	//是否开通金蝶财务 0-未开通  1-开通
	public String getOpenJDXCYStatus(HttpServletRequest request) {
		JSONObject valueObject = getJson(request);
		String openJDXCYStatus = "";
		if (valueObject != null) {
			openJDXCYStatus = valueObject.getString("openJDXCYStatus");
		}
		return openJDXCYStatus;
	}
	
	//判断是否登录
	public boolean isLogin(HttpServletRequest request)
	{
        
        String token = request.getHeader("X-Token");
        String userName = request.getHeader("X-User");

        if(StringUtil.isEmpty(token) || StringUtil.isEmpty(userName)){
            return false;
        }

        // 检查 token 有效性
        return RedisUtil.checkToken(userName,token);
	}
	
	public Response getResult(Object result) {
		Response response = new Response();
		if(result == null)
		{
			return response.failure("没有查询到相关数据");
		}
		return response.success(result);
	}

	public Response getResult(Map result) {
		Response response = new Response();
		if(result == null)
		{
			return response.failure("没有查询到相关数据");
		}
		if (null == result.get(SystemKey.ERROR_KEY)) {
			return response.success(result);
		} else {
			return response.failure(result.get(SystemKey.ERROR_KEY).toString());
		}
	}

	public Response getResultError(String error) {
		Response response = new Response();
		return response.failure(error);
	}

	/**
	 * -1 代表中文
	 * */
	public Integer getLangId(HttpServletRequest request) {

		DataSourceHolder.setDataSource(null);
		if(getSaveStr(request, "langKey") ==null || getSaveStr(request, "langKey").equals("zh_lang")){
			DataSourceHolder.setDataSource(getDbName(request));
			return -1;
		}
		LangBasics lang = langBasicsMapper.getLangBasicsById(getSaveStr(request, "langKey") ==null? "zh_lang":getSaveStr(request, "langKey"), DeleteFlag.VALID.getCode());
		if(getDbName(request) !=null){
			DataSourceHolder.setDataSource(getDbName(request));
		}

		return lang == null ? -1 : lang.getId();
	}

	public String getLangKey(HttpServletRequest request) {
		DataSourceHolder.setDataSource(null);
		String langKey = getSaveStr(request, "langKey");
		DataSourceHolder.setDataSource(getDbName(request) == ""? null:getDbName(request));
		return langKey;
	}

	public String getSaveStr(HttpServletRequest request,String key) {
		JSONObject valueObject = getJson(request);
		String str = "";
		if (valueObject != null) {
			str = valueObject.getString(key);
		}
		return str;
	}

	/**
	 * name 需要翻译的中文
	 * */
	public static  String getLangName(String name){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String langKey=String.valueOf(request.getSession().getAttribute("lang_key"));
		if(langKey == null || langKey == ""){
			langKey = "zh_lang";
		}
		Locale localUS = new Locale(langKey);
		ResourceBundle bundle = null;
		try {
			bundle = ResourceBundle.getBundle("language/lang",localUS);
		}catch (Exception e){
			System.err.println("=====文件名错误====="+langKey);
			return name;
		}
		String lang = name;
		try {
			lang = bundle.getString(name);
		}catch (Exception e){
			System.err.println("=====未找到====="+name);
			lang = name;
		}
		return lang;
	}

	public void setOperationLog(Integer billId,String billType,String remark,String operateTypeName,HttpServletRequest request){
		Map<String, Object> jsonMap = new HashMap<>();
		//增加操作日志
		jsonMap.put("billId",billId);
		jsonMap.put("billType",billType);
		jsonMap.put("operateTypeName",operateTypeName);
		jsonMap.put("remark",remark);
		jsonMap.put("dbName",getDbName(request));
		jsonMap.put("creater",getEmployeeName(request));
		jsonMap.put("index","b2_es_operation");
		logger.warn(JSON.toJSONString(jsonMap));
	}

	/**
	 * 订单处理操作日志
	 * @param billId
	 * @param billNo
	 * @param concent
	 * @param operateTypeName
	 * @param request
	 */
	public void setMallOrderProcessingLog(Integer billId,String billNo,String concent,String operateTypeName,HttpServletRequest request){
		HandleThreadSyncMallOrderProcessing handleThreadSyncMallOrderProcessing = new HandleThreadSyncMallOrderProcessing(billId, billNo, concent, operateTypeName, request);
		handleThreadSyncMallOrderProcessing.start();
	}

	class HandleThreadSyncMallOrderProcessing extends Thread {
		private Integer billId;
		private String billNo;
		private String concent;
		private String operateTypeName;
		private HttpServletRequest request;

		public HandleThreadSyncMallOrderProcessing(Integer billId,String billNo,String concent,String operateTypeName,HttpServletRequest request) {
			this.billId = billId;
			this.billNo = billNo;
			this.concent = concent;
			this.operateTypeName = operateTypeName;
			this.request = request;
		}

		@Override
		public void run() {
			try {
				Map<String, Object> jsonMap = new HashMap<>();
				//增加操作日志
				jsonMap.put("billId",billId);
				jsonMap.put("billNo",billNo);
				jsonMap.put("operateTypeName",operateTypeName);
				jsonMap.put("concent",concent);
				jsonMap.put("dbName",getDbName(request));
				jsonMap.put("creater",getEmployeeName(request));
				jsonMap.put("createTime",new Date());
				//jsonMap.put("index","b2_es_mallorderprocessing");
				jsonMap.put("path",request.getContextPath()+request.getServletPath());

				IndexRequest indexRequest = new IndexRequest("b2_es_mallorderprocessing", "_doc", UUIDUtil.generateUpperGUID())
						.source(jsonMap);
				// 获取响应结果
				restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
			}catch(Exception e) {
				System.out.println("elasticsearch插入错误："+e.getMessage());
			}
		}
	}

	/**
	 * 插入数据操作日志
	 * @param relationId
	 * @param relationType
	 * @param concent
	 * @param operateTypeName
	 * @param request
	 */
	public void setDataOperationLog(Integer relationId,String relationType,String concent,String type,String operateTypeName,HttpServletRequest request){
		Map<String, Object> jsonMap = new HashMap<>();
		//增加操作日志
		jsonMap.put("relationId",relationId);
		jsonMap.put("relationType",relationType);
		jsonMap.put("concent",concent);
		jsonMap.put("type",type);
		jsonMap.put("operateTypeName",operateTypeName);
		jsonMap.put("dbName",getDbName(request));
		jsonMap.put("creater",getEmployeeName(request));
		jsonMap.put("createTime",new Date());
		jsonMap.put("path",request.getContextPath()+request.getServletPath());

		IndexRequest indexRequest = new IndexRequest("b2_es_data_operation", "_doc", UUIDUtil.generateUpperGUID())
				.source(jsonMap);
		// 获取响应结果
		try {
			restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
