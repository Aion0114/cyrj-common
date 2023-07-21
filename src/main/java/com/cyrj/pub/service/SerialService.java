package com.cyrj.pub.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.cyrj.common.service.BaseService;
import com.cyrj.pub.pojo.Employee;
import com.cyrj.pub.pojo.Serial;


/**
 * 
 * @author hzh
 * @version 创建时间：2018-08-04 14:00:50
 */
public interface SerialService extends BaseService<Serial> {

	public String getMaxCode(String maxCode,String typeCode,int defaultNum);
	/**
	 * 生成单据编号
	 * @param billType
	 * @param tenantId
	 * @return
	 */
	public String generatorBillNo(String billType, Integer tenantId,Integer employeeId,Integer organizationId) throws UnsupportedEncodingException, NoSuchAlgorithmException;
	
	public String generatorBillNo(String billType,Integer tenantId,Integer organizationId) throws UnsupportedEncodingException, NoSuchAlgorithmException;
	
	public String generatorSchemeNo(String billType,Integer tenantId,Integer organizationId) throws UnsupportedEncodingException, NoSuchAlgorithmException;

	public String generatorDateBillNo(String billType,Integer tenantId,String date,Integer organizationId) throws UnsupportedEncodingException, NoSuchAlgorithmException;

	public String generatorDatSerialNo(String typeCode, String maxCode, int defaultNum);

	public String generatorBillNo(String billType,String surplus,Integer tenantId,Integer organizationId) throws UnsupportedEncodingException, NoSuchAlgorithmException;

	/**
	 * 六大单据根据主表创建时间生成单据编号
	 * @param billType
	 * @param tenantId
	 * @param employeeId
	 * @return
	 */
	public String generatorBillNo(String billType,  Integer tenantId,Integer employeeId,  String fillDate,Integer organizationId) throws UnsupportedEncodingException, NoSuchAlgorithmException;

}