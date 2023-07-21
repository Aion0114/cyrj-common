package com.cyrj.common.service.impl;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.cyrj.common.config.RedisUtil;
import com.cyrj.common.pojo.BillMultiUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cyrj.common.constant.SystemKey;
import com.cyrj.common.constant.ValidationMessge;
import com.cyrj.common.enumeration.DeleteFlag;
import com.cyrj.common.mapper.BaseMapper;
import com.cyrj.common.service.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class BaseServiceImpl<T> implements BaseService<T> {

	public Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	BaseMapper<T> baseMapper;

	@Override
	public T getById(T t) {
		try {
			Class c = t.getClass();
			if (existsField(c, "delflag")) {
				Field field = c.getDeclaredField("delflag");
				field.setAccessible(true);
				field.set(t, DeleteFlag.VALID.getCode());
			}
		} catch (NoSuchFieldException | IllegalAccessException e) {
			System.out.println(e);
		}
		try {
			return baseMapper.getById(t);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return null;
	}

	@Override
	public T getByObj(T t) {
		try {
			Class c = t.getClass();
			if (existsField(c, "delflag")) {
				Field field = c.getDeclaredField("delflag");
				field.setAccessible(true);
				field.set(t, DeleteFlag.VALID.getCode());
			}
		} catch (NoSuchFieldException | IllegalAccessException e) {
			System.out.println(e);
		}
		try {
			return baseMapper.getByObj(t);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return null;
	}

	@Override
	public Map getMapById(T t) {
		Map resultMap = new HashMap();
		try {
			T obj = getById(t);
			if(obj == null)
			{
				resultMap.put(SystemKey.ERROR_KEY, ValidationMessge.QUERY_ERROR);
			}else
			{
				resultMap.put("obj",obj);
			}
		}catch(Exception ex){
			resultMap.put(SystemKey.ERROR_KEY,ValidationMessge.QUERY_ERROR);
		}
		return resultMap;
	}

	@Override
	public T getById(int id) {
		Map<String,Object> map = new HashMap<>();
		map.put("id",id);
		map.put("delflag", DeleteFlag.VALID.getCode());
		try {
			return baseMapper.getMapByObj(map);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return null;
	}

	@Override
	public T getMapByObj(Map<String,Object> map) {
		if(!map.containsKey("delflag"))
		{
			map.put("delflag", DeleteFlag.VALID.getCode());
		}
		try {
			return baseMapper.getMapByObj(map);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return null;
	}


	@Override
	public Map<String,Object> getMapByMap(Map<String,Object> map) {
		if(!map.containsKey("delflag"))
		{
			map.put("delflag", DeleteFlag.VALID.getCode());
		}
		try {
			return baseMapper.getMapByMap(map);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}

		return null;
	}



	@Override
	public List<Map<String, Object>> findListByMap(Map<String, Object> map) {
		List<Map<String, Object>> list = new ArrayList<>();
		if(!map.containsKey("delflag"))
		{
			map.put("delflag", DeleteFlag.VALID.getCode());
		}
		try {
			list = baseMapper.findListByMap(map);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return list;
	}

	@Override
	public List<T> findListObjByMap(Map<String, Object> map) {
		List<T> list = new ArrayList<>();
		if(!map.containsKey("delflag"))
		{
			map.put("delflag", DeleteFlag.VALID.getCode());
		}
		try {
			list =baseMapper.findListObjByMap(map);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return list;
	}


	@Override
	public PageInfo<T> findListPageObjByMap(Map<String, Object> map,Integer pageNum,Integer pageSize) {
		if(!map.containsKey("delflag"))
		{
			map.put("delflag", DeleteFlag.VALID.getCode());
		}
		PageHelper.startPage(pageNum, pageSize);
		try {
			List<T> list = baseMapper.findListObjByMap(map);
			return new PageInfo<>(list);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}

		return null;
	}

	@Override
	public PageInfo<Map<String, Object>> findListPageByMap(Map<String, Object> map,Integer pageNum,Integer pageSize) {
		if(!map.containsKey("delflag"))
		{
			map.put("delflag", DeleteFlag.VALID.getCode());
		}
		PageInfo<Map<String, Object>> page = null;
		PageHelper.startPage(pageNum, pageSize);
		try {
			List<Map<String, Object>> list = baseMapper.findListByMap(map);
			page = new PageInfo<>(list);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return page;
	}

	/**
	 * 处理高亮结果
	 * @date: 2018/12/18 10:48
	 */
//	private List<Map<String,Object>> getHitList(SearchHits hits){
//		List<Map<String,Object>> list = new ArrayList<>();
//		for(SearchHit searchHit : hits){
//			list.add(searchHit.getSourceAsMap());
//		}
//		return list;
//	}
	
	@Override
    public Map save(T t) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		Map<String,Object> resultMap = new HashMap<>();
		int result = 0;
    	try {
			Class c = t.getClass();
			if (existsField(c, "delflag")) {
				Field field = c.getDeclaredField("delflag");
				field.setAccessible(true);
				field.set(t, DeleteFlag.VALID.getCode());
			}
			if (existsField(c, "createTime")) {
				Field field = c.getDeclaredField("createTime");
				field.setAccessible(true);
				field.set(t, new Date());
			}
			if (existsField(c, "modifyTime")) {
				Field field = c.getDeclaredField("modifyTime");
				field.setAccessible(true);
				field.set(t, new Date());
			}
			Field field = c.getDeclaredField("id");
			field.setAccessible(true);
			Object id = field.get(t);
			if(id == null || Integer.parseInt(id.toString()) <= 0)
	        {
	    		result = baseMapper.add(t);
	        }else{
	        	result = baseMapper.update(t);
	        }
		} catch (NoSuchFieldException | IllegalAccessException e) {
			System.out.println(e);
		}
    	resultMap.put("obj", t);
        return resultMap;
    }


	@Override
    public Map<String, Object> add(T t) throws UnsupportedEncodingException, NoSuchAlgorithmException {
    	Map<String,Object> resultMap = new HashMap<>();
    	try {
			Class c = t.getClass();
			if (existsField(c, "delflag")) {
				Field field = c.getDeclaredField("delflag");
				field.setAccessible(true);
				field.set(t, DeleteFlag.VALID.getCode());
			}
			if (existsField(c, "createTime")) {
				Field field = c.getDeclaredField("createTime");
				field.setAccessible(true);
				field.set(t, new Date());
			}
			if (existsField(c, "modifyTime")) {
				Field field = c.getDeclaredField("modifyTime");
				field.setAccessible(true);
				field.set(t, new Date());
			}
			
		} catch (NoSuchFieldException | IllegalAccessException e) {
			System.out.println(e);
		}
		int result = baseMapper.add(t);
		if(result <= 0 )
    	{
    		resultMap.put("error","保存错误");
    	}
    	resultMap.put("obj", t);
        return resultMap;
    }

    @Override
    public Map<String, Object> update(T t) throws UnsupportedEncodingException, NoSuchAlgorithmException {
    	try {
			Class c = t.getClass();
			if (existsField(c, "delflag")) {
				Field field = c.getDeclaredField("delflag");
				field.setAccessible(true);
				field.set(t, DeleteFlag.VALID.getCode());
			}
			if (existsField(c, "modifyTime")) {
				Field field = c.getDeclaredField("modifyTime");
				field.setAccessible(true);
				field.set(t, new Date());
			}
		} catch (NoSuchFieldException | IllegalAccessException e) {
			System.out.println(e);
		}
		int result = baseMapper.update(t);
    	Map<String,Object> resultMap = new HashMap<>();
    	resultMap.put("obj", t);
    	if(result <= 0 )
    	{
    		resultMap.put("error","保存错误");
    	}
        return resultMap;
    }

    @Override
    public Map<String, Object> delete(T t) {
    	Map<String, Object> resultMap = new HashMap<>();
    	try {
			Class c = t.getClass();
			if (existsField(c, "delflag")) {
				Field field = c.getDeclaredField("delflag");
				field.setAccessible(true);
				field.set(t, DeleteFlag.DELETE.getCode());
			}
			if (existsField(c, "modifyTime")) {
				Field field = c.getDeclaredField("modifyTime");
				field.setAccessible(true);
				field.set(t, new Date());
			}
		} catch (NoSuchFieldException | IllegalAccessException e) {
			System.out.println(e);
		}
		int result = baseMapper.delete(t);
    	if(result <= 0 )
    	{
    		resultMap.put("error","删除错误");
    	}
        return resultMap;
    }
    
    @Override
    public Map<String, Object> delete(Map<String, Object> map) {
    	Map<String, Object> resultMap = new HashMap<>();
    	if(!map.containsKey("delflag"))
    	{
    		map.put("delflag", DeleteFlag.DELETE.getCode());
    	}
    	if(!map.containsKey("modifyTime"))
    	{
    		map.put("modifyTime", new Date());
    	}
    	int result = baseMapper.delete(map);
    	if(result <= 0 )
    	{
    		resultMap.put("error","删除错误");
    	}
        return resultMap;
    }
    
    private static boolean existsField(Class clz, String fieldName){
        try{
            return clz.getDeclaredField(fieldName)!=null;
        }
        catch(Exception ex){
//			System.out.println(ex.getMessage());
        }
        if(clz!=Object.class){
            return existsField(clz.getSuperclass(),fieldName);
        }
        return false;
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

	/**
	 * 多单位换算
	 *  `conversionRate`  '转换率',
	 *   `specUnitPrice`  '对应单位的价格',
	 *   `specUnitQuantity`  '对应单位的数量',
	 *   basicUnit 1是基本单位  基本单位时，转换率要为商品档案的包装规格
	 * @return
	 * 	 quantity  数量
	 *   itemQuantity 件数
	 *   scatteredQuantity 散数
	 *   getIP 件进价 件退价
	 *   getSP 散进价 散退价
	 * */
	@Override
	public BillMultiUnit convertMultiUnit(BillMultiUnit multiUnit) {
		if(multiUnit !=null && multiUnit.getConversionRate() !=null && multiUnit.getSpecUnitPrice() !=null && multiUnit.getSpecUnitQuantity() !=null){
			Integer basicUnit = multiUnit.getBasicUnit() ==null? 1: multiUnit.getBasicUnit();
			Integer digits = multiUnit.getDigits() ==null?4:multiUnit.getDigits();

			if(basicUnit ==1){ // 基本单位
				multiUnit.setQuantity(multiUnit.getSpecUnitQuantity());
				BigDecimal quantity = multiUnit.getSpecUnitQuantity();
				// 计算件数
				BigDecimal itemQuantity = quantity.divide(multiUnit.getConversionRate(), 0, BigDecimal.ROUND_DOWN);
				if (null != itemQuantity && (itemQuantity.compareTo(new BigDecimal(0)) != 0)) {
					multiUnit.setItemQuantity(itemQuantity);
				}else{
					multiUnit.setItemQuantity(BigDecimal.ZERO);
				}
				// 计算散数
				BigDecimal scatteredQuantity = quantity.divideAndRemainder(multiUnit.getConversionRate())[1];
				if (null != scatteredQuantity && (scatteredQuantity.compareTo(new BigDecimal(0)) != 0)) {
					multiUnit.setScatteredQuantity(scatteredQuantity);

				}else{
					multiUnit.setScatteredQuantity(BigDecimal.ZERO);
				}
				multiUnit.setGetSP(multiUnit.getSpecUnitPrice());
				multiUnit.setGetIP(multiUnit.getSpecUnitPrice().multiply(multiUnit.getConversionRate()));

			}else{ // 大单位
				BigDecimal quantity = multiUnit.getSpecUnitQuantity().multiply(multiUnit.getConversionRate());

				multiUnit.setQuantity(quantity);

				BigDecimal itemQuantity = multiUnit.getSpecUnitQuantity().setScale( 0, BigDecimal.ROUND_DOWN);
				multiUnit.setItemQuantity(itemQuantity);

				BigDecimal scatteredQuantity = quantity.divideAndRemainder(multiUnit.getConversionRate())[1];
				if (null != scatteredQuantity && (scatteredQuantity.compareTo(new BigDecimal(0)) != 0)) {
					multiUnit.setScatteredQuantity(scatteredQuantity);

				}else{
					multiUnit.setScatteredQuantity(BigDecimal.ZERO);
				}

				multiUnit.setGetSP(multiUnit.getSpecUnitPrice().divide(multiUnit.getConversionRate(),digits,BigDecimal.ROUND_HALF_UP));
				multiUnit.setGetIP(multiUnit.getSpecUnitPrice());
			}
		}
		return multiUnit;
	}




	//查询进价权限  banSeOtherDocStatus
	public String getselXpriceStatus(String userName,String token) {
		JSONObject valueObject = getJson(userName,token);
		String selXpriceStatus = "";
		if (valueObject != null) {
			selXpriceStatus = valueObject.getString("selXpriceStatus");
		}
		return selXpriceStatus;
	}

	//查询利润权限
	public String getselProfitStatus(String userName,String token) {
		JSONObject valueObject = getJson(userName,token);
		String selProfitStatus = "";
		if (valueObject != null) {
			selProfitStatus = valueObject.getString("selProfitStatus");
		}
		return selProfitStatus;
	}
	//禁止查看他人单据权限
	public String getbanSeOtherDocStatus(String userName,String token) {
		JSONObject valueObject = getJson(userName,token);
		String selProfitStatus = "";
		if (valueObject != null) {
			selProfitStatus = valueObject.getString("banSeOtherDocStatus");
		}
		return selProfitStatus;
	}

	//查看成本
	public String getselCostPriceStatus(String userName,String token) {
		JSONObject valueObject = getJson(userName,token);
		String selProfitStatus = "";
		if (valueObject != null) {
			selProfitStatus = valueObject.getString("selCostPriceStatus");
		}
		return selProfitStatus;
	}

	//查询业务可以查询日期
	public String getDateStartAuth(String userName,String token) {
		JSONObject valueObject = getJson(userName,token);
		String dateStartAuth = "";
		if (valueObject != null) {
			dateStartAuth = valueObject.getString("dateStartAuth");
		}
		return dateStartAuth;
	}

	//查询业务可以查询天数
	public Integer getUserOtherSelDayNum(String userName,String token) {
		JSONObject valueObject = getJson(userName,token);
		Integer userOtherSelDayNum = -1;
		if (valueObject != null) {
			userOtherSelDayNum = valueObject.getInteger("userOtherSelDayNum");
		}
		return userOtherSelDayNum;
	}

	public JSONObject getJson(String userName,String token)
	{
		JSONObject valueObject = RedisUtil.getToken(userName, token);
		return valueObject;
	}
}
