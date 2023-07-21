package com.cyrj.common.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by czx on 2017/9/29.
 */
public class SMSUtil {

	private static String url = "http://smssh1.253.com/msg/send/json";// 应用地址
	private static String account = "N1410231";// 账号
	private static String password = "U9gvd26oP";// 密码

	public static boolean sendCodeMsg(String phone,String code) {
		if(isPhone(phone)){
			return chinaSendCodeMsg(phone,code);
		}else{
			return abroadSendCodeMsg(phone,code);
		}
	}
	
	 public static boolean sendMsg(String phone,String sendMsg) {
		if(isPhone(phone)){
			return chinaSendMsg(phone,sendMsg);
		}else{
			return abroadSendMsg(phone,sendMsg);
		}
	 }
	
	 public static boolean sendCodeKhRelease(String phone,String code) {
		 if(isPhone(phone)){
			return chinaSendCodeKhRelease(phone,code);
		 }else{
			return abroadSendCodeKhRelease(phone,code);
		}
	 }
////////////////////////////////////判断手机号码是否是国内手机号/////////////////////////////////
	 public static boolean isPhone(String phone){
		 if(phone!=null && phone!=""){
			 if(phone.startsWith("+")){
				 System.out.println("国外"+phone);
				 return false;
			 }else{
				 System.out.println("国内"+phone);
				 return true;
			 }
		 }
		return false;
	 }
	 
///////////////////////////////////////国内短信///////////////////////////////////////////////

	public static  boolean send(String params) {
		try {
			String result = HttpUtil.sendPost(url, params);
			JSONObject jsonObject = JSON.parseObject(result);
			String code1 = jsonObject.get("code").toString();
			if("0".equals(code1)){
				return  true;
			}else {
				System.out.println("错误:"+code1);
				return false;
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println(e);
			return false;
		}

	}

	public static  boolean chinaSendMsg(String phone,String sendMsg) {
		String msg = sendMsg;// 短信内容
		// 组装请求参数
		JSONObject map = new JSONObject();
		map.put("account", account);
		map.put("password", password);
		map.put("msg",msg);
		map.put("phone", phone);
		String params = map.toString();
		return send(params);
	}
	 
    public static  boolean chinaSendCodeMsg(String phone,String code) {
        String msg = "尊敬的用户：您的验证码是"+code+"，该验证码5分钟内有效，感谢您的支持与使用。";// 短信内容
		return chinaSendMsg(phone,msg);
    }
    
    public static  boolean chinaSendCodeKhRelease(String phone,String code) {
        String msg = "尊敬的用户：已为您开通商城下单"+code+",登录商城或关注微信商城,即可下单";// 短信内容
		return chinaSendMsg(phone,msg);
    }
    
    
    /////////////////////////////////////国际短信///////////////////////////////////////////
    
    public  static boolean abroadSendCodeMsg(String phone, String code) {
    	phone = phone.replace("+", "");
		String url = "http://intapi.253.com/send/json";
		String account = "I6721311";
		String password = "4UqsE5jLN6370d";
		String msg = "【悦商云】尊敬的用户：您的验证码是"+code+"，该验证码5分钟内有效，感谢您的支持与使用。";
		// 组装请求参数
		JSONObject map = new JSONObject();
		map.put("account", account);
		map.put("password", password);
		map.put("msg", msg);
		// 手机号码，格式(区号+手机号码)，例如：8615800000000，其中86为中国的区号，区号前不使用00开头,15800000000为接收短信的真实手机号码。5-20位。必填
		map.put("mobile", phone);
		String params = map.toString();
		try {
			String result = HttpUtil.sendPost(url, params);
			JSONObject jsonObject = JSON.parseObject(result);
			String code1 = jsonObject.get("code").toString();
			if("0".equals(code1)){
                return  true;
            }else {
                return false;
            }
		} catch (UnsupportedEncodingException e) {
			System.out.println(e);
			return false;
		}
	}

	public static  boolean abroadSendMsg(String phone, String sendMsg) {
		phone = phone.replace("+", "");
		String url = "http://intapi.253.com/send/json";
		String account = "I6721311";
		String password = "4UqsE5jLN6370d";
		String msg = "【悦商云】"+sendMsg;
		// 组装请求参数
		JSONObject map = new JSONObject();
		map.put("account", account);
		map.put("password", password);
		map.put("msg", msg);
		// 手机号码，格式(区号+手机号码)，例如：8615800000000，其中86为中国的区号，区号前不使用00开头,15800000000为接收短信的真实手机号码。5-20位。必填
		map.put("mobile", phone);
		String params = map.toString();
		System.out.println("请求参数为:" + params);
		try {
			String result = HttpUtil.sendPost(url, params);
			
			System.out.println("返回参数为:" + result);
			
			JSONObject jsonObject = JSON.parseObject(result);
			String code1 = jsonObject.get("code").toString();
			if("0".equals(code1)){
                return  true;
            }else {
                return false;
            }
		} catch (UnsupportedEncodingException e) {
			System.out.println(e);
			return false;
		}
	}

	public static  boolean abroadSendCodeKhRelease(String phone, String code) {
		phone = phone.replace("+", "");
		String url = "http://intapi.253.com/send/json";
		String account = "I6721311";
		String password = "4UqsE5jLN6370d";
		String msg = "【悦商云】尊敬的用户：已为您开通商城下单"+code+",登录商城或关注微信商城,即可下单";// 短信内容
		// 组装请求参数
		JSONObject map = new JSONObject();
		map.put("account", account);
		map.put("password", password);
		map.put("msg", msg);
		// 手机号码，格式(区号+手机号码)，例如：8615800000000，其中86为中国的区号，区号前不使用00开头,15800000000为接收短信的真实手机号码。5-20位。必填
		map.put("mobile", phone);
		String params = map.toString();
		System.out.println("请求参数为:" + params);
		try {
			String result = HttpUtil.sendPost(url, params);
			
			System.out.println("返回参数为:" + result);
			
			JSONObject jsonObject = JSON.parseObject(result);
			String code1 = jsonObject.get("code").toString();
			String error = jsonObject.get("error").toString();
			System.out.println(code1);
			System.out.println(error);
			if("0".equals(code1)){
                return  true;
            }else {
                return false;
            }
		} catch (UnsupportedEncodingException e) {
			System.out.println(e);
			return false;
		}
	}
	public static void main(String[] args) {
		chinaSendCodeMsg("18125327867","1234");
	}
}
