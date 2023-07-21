package com.cyrj.sys.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.cyrj.common.service.BaseService;
import com.cyrj.sys.pojo.QdtenantInfo;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;


/**
 * 
 * @author hzh
 * @version 创建时间：2019-03-26 13:38:52
 */
public interface QdtenantInfoService extends BaseService<QdtenantInfo> {

	List<Map<String, Object>> findListInfo(Map map);

	PageInfo<Map<String, Object>> findListPageByMaps(Map<String, Object> map,Integer pageNum,Integer pageSize);
	
	Map<String, Object> updateStatus(Map map);

	void upTenantInfo(String clientverSion,String version,String labelIdStr);

	Map findRenewData(Map map) throws ParseException;

	Map<String,Object> totalSystemTypeStatistics();

	List<Map<String,Object>> systemTypeStatistics();

	List<Map<String,Object>> provinceStatistics();

    Map<String, Object> createUserInfo(QdtenantInfo qdtenantInfo) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    void deleteTenant(QdtenantInfo qdtenantInfo);
}