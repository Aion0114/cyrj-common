package com.cyrj.common.util;

import java.util.Random;
import java.util.UUID;

/**
 * Created by czx on 2017/9/28.
 */
public class UUIDUtil {
	
    public static String getNumUUID(int n){
       String temp="";
        Random random = new Random();
        for(int i=0;i<n;i++){
           int num = random.nextInt(10);
            temp+=num;
        }
        return temp;
    }

    /**
     * 产生UUID
     */
    public static final String generateGUID()
    {
        UUID uuid= UUID.randomUUID();
        return uuid.toString();
    }
    
    /**
     * 产生UUID
     */
    public static final String generateUpperGUID()
    {
    	return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }
    
    public static void main(String[] args){
        for(int i=0;i<10;i++){
            System.out.println(UUIDUtil.generateUpperGUID());
        }

    }

}
