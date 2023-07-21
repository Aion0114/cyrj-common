package com.cyrj.common.util;

import java.util.HashMap;
import java.util.Map;

import com.cyrj.common.constant.SystemKey;

public class StringValidation {

	public static String[] sqlKeyword = new String[]{"delete ","update ","drop ","alter ","insert ","create "};
	
	public static Map keywordValidation(String val) {
		Map map = new HashMap();
		for(String keyword : sqlKeyword)
		{
	    	if(val.indexOf(keyword) > -1){
	    		map.put(SystemKey.ERROR_KEY, "内容中包含了关键字:"+keyword);
	    		return map;
	    	}
		}
		map.put("ok", "ok");
		return map;
	}
}
