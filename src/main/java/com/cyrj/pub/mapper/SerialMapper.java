package com.cyrj.pub.mapper;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import com.cyrj.common.mapper.BaseMapper;
import com.cyrj.pub.pojo.Serial;


public interface SerialMapper extends BaseMapper<Serial>{
	
	public int updateSerialNo(Map map) throws UnsupportedEncodingException, NoSuchAlgorithmException;

}