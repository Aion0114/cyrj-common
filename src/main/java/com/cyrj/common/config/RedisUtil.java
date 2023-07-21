package com.cyrj.common.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Component
public class RedisUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisConfiguration.class); 
	
	@Autowired
    StringRedisTemplate stringRedisTemplate;
	
	public static RedisUtil redisUtil;
		
    @PostConstruct public void init() 
    {
    	redisUtil = this; 
    	redisUtil.stringRedisTemplate = this.stringRedisTemplate;
    }
	
	/**
     * 过期时间
     */
    public static long expired = 7;

    /**
     * 时间单位
     */
    public static TimeUnit timeUnit = TimeUnit.DAYS;

	public static String createToken(Map<String, Object> param) {

		String token = UUID.randomUUID().toString();
		String valueString = JSON.toJSONString(param);
		redisUtil.stringRedisTemplate.opsForValue().set(token, valueString, expired, timeUnit);
		return token;
	}

	public static String createToken(String username, String mac, Integer tenantId, Integer userType, String dbName,
			Integer employeeId, String employeeName) {

		String token = UUID.randomUUID().toString();
		Map<String, Object> value = new HashMap<String, Object>();


		value.put("username", username);
		value.put("mac", mac);
		value.put("tenantId", tenantId);
		value.put("userType", userType);
		value.put("dbName", dbName);
		value.put("employeeId", employeeId);
		value.put("employeeName", employeeName);
		String valueString = JSONObject.toJSONString(value);

		logger.info("========start set token =================");
		redisUtil.stringRedisTemplate.opsForValue().set(token, valueString, expired, timeUnit);
		logger.info("========end set token =================");
		return token;
	}

	public static String createTokenKey(String username,String dbName, Integer tenantId, Integer employeeId, String employeeName,String langKey) {
		String token = UUID.randomUUID().toString();
		Map<String, Object> value = new HashMap<String, Object>();

		value.put("username", username);
		value.put("tenantId", tenantId);
		value.put("employeeId", employeeId);
		value.put("dbName",dbName);
		value.put("employeeName", employeeName);
		value.put("langKey", langKey);

		String valueString = JSONObject.toJSONString(value);

		logger.info("========start set token =================");
		redisUtil.stringRedisTemplate.opsForValue().set(token, valueString, expired, timeUnit);
		logger.info("========end set token =================");
		return token;
	}
	
	public static String createToken(String username,String dbName, Integer tenantId, Integer employeeId, String employeeName) {

        String token = UUID.randomUUID().toString();
        Map<String, Object> value = new HashMap<String, Object>();

        value.put("username", username);
        value.put("tenantId", tenantId);
        value.put("employeeId", employeeId);
        value.put("dbName",dbName);
        value.put("employeeName", employeeName);
        String valueString = JSONObject.toJSONString(value);

        logger.info("========start set token =================");
        redisUtil.stringRedisTemplate.opsForValue().set(token, valueString, expired, timeUnit);
        logger.info("========end set token =================");
        return token;
    }
	
    public static String createToken(String username, Integer tenantId, Integer khId,Integer memberId,String dbName) {

        String token = UUID.randomUUID().toString();
        Map<String, Object> value = new HashMap<String, Object>();

        value.put("username", username);
        value.put("tenantId", tenantId);
        value.put("khId", khId);
        value.put("dbName",dbName);
        value.put("memberId", memberId);
        String valueString = JSONObject.toJSONString(value);

        logger.info("========start set token =================");
        redisUtil.stringRedisTemplate.opsForValue().set(token, valueString, expired, timeUnit);
        logger.info("========end set token =================");
        return token;
    }
    
    public static String createOpsToken(String username,Integer userId, String trueName) {

		String token = UUID.randomUUID().toString();
		Map<String, Object> value = new HashMap<String, Object>();

		value.put("dbName", null);
		value.put("username", username);
		value.put("tenantId", -1);
		value.put("employeeId", userId);
		value.put("employeeName", trueName);
		String valueString = JSONObject.toJSONString(value);

		logger.info("========start set token =================");
		redisUtil.stringRedisTemplate.opsForValue().set(token, valueString, expired, timeUnit);
		logger.info("========end set token =================");
		return token;
	}
    
    
    public static String createAppid(String appid, String access_token,String jsapi_ticket)
    {
        Map<String, Object> value = new HashMap<String, Object>();

        value.put("appid", appid);
        value.put("access_token", access_token);
        value.put("jsapi_ticket", jsapi_ticket);
        String valueString = JSONObject.toJSONString(value);

        logger.info("========start set token =================");
        redisUtil.stringRedisTemplate.opsForValue().set(appid, valueString, 7100, TimeUnit.SECONDS);
        logger.info("========end set token =================");
        return appid;
    }

    public static String createWxMessage(String code, String unionId,String openId)
    {
        Map<String, Object> value = new HashMap<String, Object>();

        value.put("code", code);
        value.put("unionid", unionId);
        value.put("openid", openId);
        String valueString = JSONObject.toJSONString(value);

        logger.info("========start set token =================");
        redisUtil.stringRedisTemplate.opsForValue().set(code, valueString, 7100, TimeUnit.SECONDS);
        logger.info("========end set token =================");
        return code;
    }

	public static String createCorpid(String corpid, String access_token)
	{
		Map<String, Object> value = new HashMap<String, Object>();

		value.put("corpid", corpid);
		value.put("access_token", access_token);
		String valueString = JSONObject.toJSONString(value);

		logger.info("========start set token =================");
		redisUtil.stringRedisTemplate.opsForValue().set(corpid, valueString, 7200, TimeUnit.SECONDS);
		logger.info("========end set token =================");
		return corpid;
	}

	public static String createWxWorkToken(String key, String str, long timeout, TimeUnit unit) {
		Map<String, Object> value = new HashMap<String, Object>();

		value.put("tokenKey", key);
		value.put("msg", str);
		String valueString = JSONObject.toJSONString(value);

		logger.info("========start set token =================");
		redisUtil.stringRedisTemplate.opsForValue().set(key, valueString, timeout, unit);
		logger.info("========end set token =================");
		return key;
	}

	public static JSONObject getWxWorkToken(String key) {
		Boolean isTrue = true;
		if (key == null) {
			isTrue = false;
		}
		// 检查token是否过期
		long result = redisUtil.stringRedisTemplate.getExpire(key);
		if (result <= 0) {
			isTrue = false;
		}

		if (isTrue) {
			String value = redisUtil.stringRedisTemplate.opsForValue().get(key);
			JSONObject valueObject = JSONObject.parseObject(value);
			return valueObject;
		} else {
			return null;
		}
	}

	public static void createForValue(String key, Object value)
	{
		if(value != null)
		{
			redisUtil.stringRedisTemplate.opsForValue().set(key, value.toString(), expired, timeUnit);
		}
	}

	public static Object getForRedis(String redisKey) {
		if (checkRedis(redisKey)) {
			Object value = redisUtil.stringRedisTemplate.opsForValue().get(redisKey);
			return value;
		} else {
			return null;
		}
	}
	
	public static boolean checkToken(String username, String token) {
		if (token == null || username == null) {
			return false;
		}
		// 检查token是否过期
		long result = redisUtil.stringRedisTemplate.getExpire(token);
		if (result <= 0) {
			return false;
		}

		// 检查token对应用户是否匹配
		String value = redisUtil.stringRedisTemplate.opsForValue().get(token);
		JSONObject valueObject = JSONObject.parseObject(value);
		String tokenUsername = valueObject.getString("username");
		if (!username.equals(tokenUsername)) {
			return false;
		}

		// 更新token到期时间
		redisUtil.stringRedisTemplate.expire(token, expired, timeUnit);
		return true;

	}

	
	public static boolean checkAppid(String appid) {
		if (appid == null) {
			return false;
		}
		// 检查token是否过期
		long result = redisUtil.stringRedisTemplate.getExpire(appid);
		if (result <= 0) {
			return false;
		}

		return true;
	}

	public static boolean checkCorpid(String corpid) {
		if (corpid == null) {
			return false;
		}
		// 检查token是否过期
		long result = redisUtil.stringRedisTemplate.getExpire(corpid);
		if (result <= 0) {
			return false;
		}
		return true;
	}


	public static JSONObject getAppid(String appid) {
		if (checkAppid(appid)) {
			String value = redisUtil.stringRedisTemplate.opsForValue().get(appid);
			JSONObject valueObject = JSONObject.parseObject(value);
			return valueObject;
		} else {
			return null;
		}
	}

	public static JSONObject getWxUserInfo(String code) {
		if (null != code && !code.equals("")) {
			String value = redisUtil.stringRedisTemplate.opsForValue().get(code);
			JSONObject valueObject = JSONObject.parseObject(value);
			return valueObject;
		} else {
			return null;
		}
	}

	public static JSONObject getCorpid(String corpid) {
		if (checkCorpid(corpid)) {
			String value = redisUtil.stringRedisTemplate.opsForValue().get(corpid);
			JSONObject valueObject = JSONObject.parseObject(value);
			return valueObject;
		} else {
			return null;
		}
	}


	/**
	 * 注销token
	 * 
	 * @param token
	 */
	public static void cancelToken(String token) {
		redisUtil.stringRedisTemplate.delete(token);
	}


	public static JSONObject getToken(String username, String token) {
		if (checkToken(username, token)) {
			String value = redisUtil.stringRedisTemplate.opsForValue().get(token);
			JSONObject valueObject = JSONObject.parseObject(value);
			return valueObject;
		} else {
			return null;
		}
	}

	public static void createRedis(String redisKey, Map<String, Object> value) {
		String valueString = JSONObject.toJSONString(value);
		redisUtil.stringRedisTemplate.opsForValue().set(redisKey, valueString, expired, timeUnit);
	}


	public static boolean checkRedis(String redisKey) {
		if (redisKey == null) {
			return false;
		}
		// 检查是否过期
		long result = redisUtil.stringRedisTemplate.getExpire(redisKey);
		if (result <= 0) {
			return false;
		}

		// 更新token到期时间
		redisUtil.stringRedisTemplate.expire(redisKey, expired, timeUnit);
		return true;

	}

	
	public static JSONObject getRedis(String redisKey) {
		if (checkRedis(redisKey)) {
			String value = redisUtil.stringRedisTemplate.opsForValue().get(redisKey);
			JSONObject valueObject = JSONObject.parseObject(value);
			return valueObject;
		} else {
			return null;
		}
	}


	public static String getValueByToken(String param, String token) {
		// 根据参数从token中取值
		String value = redisUtil.stringRedisTemplate.opsForValue().get(token);
		JSONObject valueObject = JSONObject.parseObject(value);
		String result = valueObject.getString(param);
		return result;
	}

	/**
	 * 保存值
	 * @param key
	 * @param val
	 */
	public static void saveForValue(String key, String val, long expired, TimeUnit timeUnit) {
		redisUtil.stringRedisTemplate.opsForValue().set(key, val,expired, timeUnit);
	}

	/**
	 * 从value中获取值
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return redisUtil.stringRedisTemplate.opsForValue().get(key);
	}

	/**
	 * 删除key
	 * @param key
	 */
	public static void delete(String key) {
		redisUtil.stringRedisTemplate.delete(key);
	}

	/**
	 * 模糊删除key
	 * @param key
	 */
	public static void deleteFuzzy(String key) {
		Set<String> keys = redisUtil.stringRedisTemplate.keys(key+":" + "*");
		redisUtil.stringRedisTemplate.delete(keys);
	}

}
