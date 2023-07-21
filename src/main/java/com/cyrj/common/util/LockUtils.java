package com.cyrj.common.util;

import com.cyrj.common.config.RedisUtil;

import java.util.concurrent.TimeUnit;

/**
 * 在redis库中锁定操作
 */
public class LockUtils {

    /**
     * 锁操作
     */
    public static void LockInRedis(String key, String value){
        String lock = RedisUtil.getValue(key);
        if(null == lock){
            RedisUtil.saveForValue(key,value,10, TimeUnit.MINUTES);
        }
    }

    /**
     * 解锁操作
     */
    public static void clearLockInRedis(String key){
        RedisUtil.delete(key);
    }

    /**
     * 判断是否加锁操作
     */
    public static boolean isLock(String key){
        String lock = RedisUtil.getValue(key);
        if(null == lock){
            return false;
        }else {
            return true;
        }
    }

}
