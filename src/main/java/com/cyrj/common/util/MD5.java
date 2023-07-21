package com.cyrj.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;

/** 
* 功能：MD5签名
* 版本：3.3
* 修改日期：2012-08-17
* */
public class MD5 {
    public static void main(String[] args) throws Exception {
        //String str = "attach=附加信息&bank_type=ICBC_FP&charset=UTF-8&coupon_fee=0&fee_type=1&mch_id=001075562100008&nonce_str=7e158509216bb7c3aa4cf72165af043a&out_trade_no=1409543900454&pay_result=0&result_code=0&sign_type=MD5&status=0&time_end=20140901115747&total_fee=1&trade_type=pay.weixin.scancode&transaction_id=001075562100008201409010000129&version=1.0";
        //System.out.println(MD5.sign(str, "&key=e1cf0ddcf6b47b59c351565d8ad717af", "utf-8"));
    }

    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String input_charset) {
    	text = text + key;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }
    
    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param sign 签名结果
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key, String input_charset) {
    	text = text + key;
    	String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
    	if(mysign.equals(sign)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }
    
    public static String getMD5Params(Map<String,Object> map) throws Exception 
    {
    	String key = "K632F764133F45be";
        
    	List<String> keyArr = new ArrayList<String>();
    	for(String mapKey : map.keySet())
    	{
    		keyArr.add(mapKey);
    	}
    	Collections.sort(keyArr);
    	String temp = "";
        for (int i = 0; i < keyArr.size(); i++)
        {
        	temp = temp + "&" + keyArr.get(i) + "=" + map.get(keyArr.get(i)).toString();
        }
        temp = temp.substring(1) + key;
        System.out.println(temp);
        String encrypted = getMD5(temp).toUpperCase();
        map.put("sign", encrypted);
        
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("data", map);
        JSONObject obj = new JSONObject(dataMap);
    	return obj.toString();
    }
    
	public static String getMD5(String str)
	{
		// 生成一个MD5加密计算摘要}
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			//使用指定的字节数组对摘要进行最后更新，然后完成摘要计算 
            byte[] results = md.digest(str.getBytes()); 
            //将得到的字节数组变成字符串返回  
            String result = byteArrayToHexString(results); 
            return result; 
		} catch (Exception e) {
			System.out.println(e);
		}
		return "";
	}
	
	/** 
	    * 轮换字节数组为十六进制字符串 
	    * @param b 字节数组 
	    * @return 十六进制字符串 
	    */ 
	    private static String byteArrayToHexString(byte[] b){ 
	        StringBuffer resultSb = new StringBuffer(); 
	        for(int i=0;i<b.length;i++){ 
	            resultSb.append(byteToHexString(b[i])); 
	        } 
	        return resultSb.toString(); 
	    } 
	    
	    //十六进制下数字到字符的映射数组 
	    private final static String[] hexDigits = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"}; 
	    
	  //将一个字节转化成十六进制形式的字符串 
	    private static String byteToHexString(byte b){ 
	        int n = b; 
	        if(n<0) 
	        n=256+n; 
	        int d1 = n/16; 
	        int d2 = n%16; 
	        return hexDigits[d1] + hexDigits[d2]; 
	    } 
	
}

















