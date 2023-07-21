package com.cyrj.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import cn.hutool.http.HttpRequest;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cyrj.common.config.RedisUtil;
import org.springframework.http.MediaType;


public class WeiXinUtil {
	
	
	public static Map getWxToken(String appid,String appSecret){
        
		Map resultMap = new HashMap();
		JSONObject js = RedisUtil.getAppid(appid);
		String access_token = "";
		String jsapi_ticket = "";
		if(js == null)
		{
			JSONObject token = getTokenTool(appid, appSecret);
			if(!token.containsKey("access_token"))
			{
				resultMap.put("result", false);
				resultMap.put("error", JSON.toJSONString(token));
				return resultMap;
			}
			access_token = token.getString("access_token");
			
			JSONObject ticketJson= getTicketTool(access_token);
			if(!ticketJson.containsKey("ticket"))
			{
				resultMap.put("result", "false");
				resultMap.put("error", JSON.toJSONString(ticketJson));
				return resultMap;
			}
			jsapi_ticket = ticketJson.getString("ticket");
			RedisUtil.createAppid(appid, access_token,jsapi_ticket);
		}else{
			access_token = js.getString("access_token");
			jsapi_ticket = js.getString("jsapi_ticket");
		}
		resultMap.put("result", "true");
		resultMap.put("appid", appid);
		resultMap.put("appSecret", appSecret);
		resultMap.put("access_token", access_token);
		resultMap.put("jsapi_ticket", jsapi_ticket);
		return resultMap;
	}

	public static Map getWxUserInfo(String tokenCode, String appid,String code,String appSecret) {
		Map resultMap = new HashMap();
		JSONObject userInfo = RedisUtil.getWxUserInfo(tokenCode);
		String unionid = "";
		String openid = "";
		if (userInfo == null) {
			userInfo = getOpenIdTool(appid, code,appSecret);
			if (!userInfo.containsKey("access_token")) {
				resultMap.put("result", false);
				resultMap.put("error", JSON.toJSONString(userInfo));
				return resultMap;
			}
			tokenCode = RedisUtil.createWxMessage(code, userInfo.getString("unionid"),userInfo.getString("openid"));
			unionid = userInfo.getString("unionid");
			openid = userInfo.getString("openid");
		} else {
			unionid = userInfo.getString("unionid");
			openid = userInfo.getString("openid");
		}
		resultMap.put("result", true);
		resultMap.put("unionid", unionid);
		resultMap.put("openid", openid);
		resultMap.put("tokenCode", tokenCode);
		return resultMap;
	}

	public static JSONObject getOpenIdTool(String appId,String code,String appSecret){
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+"&secret="+appSecret+"&code="+code+"&grant_type=authorization_code";
		String result =HttpUtil.sendGet(url);
		return JSON.parseObject(result); 
	}
	
	public static JSONObject getOpenIdToolXcx(String appId, String code, String appSecret){
		String url = "https://api.weixin.qq.com/sns/jscode2session?appId="+appId+"&secret="+appSecret+"&js_code="+code+"&grant_type=authorization_code";
		String result = HttpUtil.sendGet(url);
		return JSONObject.parseObject(result);
	}

	/**
	 * 通过openid获取微信用户信息
	 * @param access_token  微信token
	 * @param openid		微信openid
	 * @return
	 */
	public static JSONObject getUserInfoUnionId(String access_token, String openid){
		String url="https://api.weixin.qq.com/sns/userinfo?access_token="+ access_token +"&openid="+ openid +"&lang=zh_CN";
		HttpMethod method = new PostMethod(url);
        HttpClient httpclient = new HttpClient();
        
        try {
			httpclient.executeMethod(method);
			String result = new String(method.getResponseBody(), "utf-8");
			JSONObject userInfo = JSON.parseObject(result, JSONObject.class);
			
			return userInfo;
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}        
		return JSON.parseObject("");
	}
	
	
	public static JSONObject getTokenTool(String appId,String appSecret){
		String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appSecret;
		String result =HttpUtil.sendGet(url);
		return JSON.parseObject(result); 
	}
	
	public static JSONObject getTicketTool(String access_token){
		String url="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";
		String result =HttpUtil.sendGet(url);
		return JSON.parseObject(result);
	}
	
	/**
	 * 通过openid获取微信用户信息
	 * @param access_token  微信token
	 * @param openid		微信openid
	 * @return
	 */
	public static JSONObject getUserInfoTool(String access_token, String openid){
		String url="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+ access_token +"&openid="+ openid +"&lang=zh_CN";
		String result = HttpUtil.sendGet(url);
		return JSON.parseObject(result);
	}

	
	/**
	 * 创建微信自定义菜单
	 * @param access_token微信token
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static JSONObject createMenu(String access_token, String menu) throws UnsupportedEncodingException{
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+ access_token;
		String result = HttpUtil.sendPost(url, menu);
		return JSON.parseObject(result);
	}
	
	
	/**
	 * 创建微信卡券
	 * @param access_token  微信token
	 * @param card  Post Json数据
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static JSONObject createCard(String access_token, String card) throws UnsupportedEncodingException{
		String url = "https://api.weixin.qq.com/card/create?access_token="+ access_token;
		String result = HttpUtil.sendPost(url, card);
		return JSON.parseObject(result);
	}
	
	/**
	 * 获取微信卡券二维码
	 * @param access_token  微信token
	 * @param card  Post Json数据
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static JSONObject createCardQrcode(String access_token, String card) throws UnsupportedEncodingException{
		String url = "https://api.weixin.qq.com/card/qrcode/create?access_token="+ access_token;
		String result = HttpUtil.sendPost(url, card);
		return JSON.parseObject(result);
	}
	
	
	/**
	 * 修改微信卡券库存
	 * @param access_token  微信token
	 * @param reduceCardStock  Post Json数据
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static JSONObject updateCardStock(String access_token, String reduceCardStock) throws UnsupportedEncodingException{
		String url = "https://api.weixin.qq.com/card/modifystock?access_token="+ access_token;
		String result = HttpUtil.sendPost(url, reduceCardStock);
		return JSON.parseObject(result);
	}
	
	/**
	 * 获取微信图片
	 * @param access_token  微信token
	 * @param media_id  Get String媒体id
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static JSONObject createWxImgUrl(String access_token, String media_id) throws UnsupportedEncodingException{
		String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token="+access_token+"&media_id="+media_id;
		String result = HttpUtil.sendGet2(url);
		return JSON.parseObject(result);
		
	}
	
	public static String createWxImgUrl2(String access_token, String media_id) throws UnsupportedEncodingException{
		String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token="+access_token+"&media_id="+media_id;
		//String result = HttpUtil.sendGet2(url);
		//return JSONObject.parseObject(result);
		return url;
	}
	
	/**
	 * 增加微信临时素材
	 * @param access_token  微信token
	 * @param card  Post Json数据
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static JSONObject createTemporaryImg(String access_token, String card) throws UnsupportedEncodingException{
		String url = "https://api.weixin.qq.com/card/qrcode/create?access_token="+ access_token;
		String result = HttpUtil.sendPost(url, card);
		return JSON.parseObject(result);
	}
	
	/**
	 * 删除微信自定义菜单
	 * @param access_token
	 * @return
	 * @throws Exception
	 */
	public static JSONObject deleteMenu(String access_token) throws Exception{
		String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+ access_token;
		String result = HttpUtil.sendGet(url);
		return JSON.parseObject(result);
	}
	
	/**
	 * 发送模板消息
	 * @param access_token
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static JSONObject sendMessage(String access_token, String message) throws Exception{
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+ access_token;
		String result = HttpUtil.sendPost(url, message);
		return JSON.parseObject(result);
	}
	
	/**
     * 加密接口
     */
    public static String SHA1(String str) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1"); // 如果是SHA加密只需要将"SHA-1"改成"SHA"即可
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexStr = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexStr.append(0);
                }
                hexStr.append(shaHex);
            }
            return hexStr.toString();

        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return null;
    }
    
    /**
     * 创建临时带参数二维码
     * 
     * @param accessToken
     * @expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     * @param sceneId 场景Id
     * @return
     */
    public static String createTempTicket(String accessToken,String scene_str) {
    	String params = "{\"expire_seconds\": 604800, \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \""+scene_str+"\" }}}";
    	
    	String url = "";
    	try {
			String data = HttpUtil.sendPost("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+accessToken, params);
			JSONObject result = JSON.parseObject(data);
			url = result.getString("url");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	return url;
     
    }

    /**
	 *
	 * 推送到企业微信群里
	 * content 内容
	 * url 地址
	 * **/
    public static void sendOutWechat(String content,String url){
		// 企业微信
		com.alibaba.fastjson.JSONObject text = new com.alibaba.fastjson.JSONObject();
		com.alibaba.fastjson.JSONObject param = new com.alibaba.fastjson.JSONObject();

		//消息内容
		text.put("content", content);
		param.put("msgtype", "text");
		param.put("text", text);
		HttpRequest request1 = cn.hutool.http.HttpUtil.
				createPost(url).
				contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).
				body(param.toJSONString());
		request1.execute();
	}
}

