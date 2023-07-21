package com.cyrj.common.util;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.lang3.StringUtils;




/**
 * Created by ChuWang on 2018/8/2.
 */
public class EncryptUtil {

    private final byte[] DESIV = new byte[] { 0x12, 0x34, 0x56, 120, (byte) 0x90, (byte) 0xab, (byte) 0xcd, (byte) 0xef };// 向量
    private AlgorithmParameterSpec iv = null;// 加密算法的参数接口
    private Key key = null;
    private static String charset = "utf-8";
    private static String encryptKey = "9ba45bfd500642328ec03ad8ef1b6e84"; //加密key
    private static char[] codeArr = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W','X', 'Y', 'Z'}; //随机码数组
    private static int codeCount = 16; //随机码数量

    /**
     * 初始化
     * @param deSkey	密钥
     * @throws Exception
     */
    public EncryptUtil(String deSkey, String charset) throws Exception {
        if (StringUtils.isNotBlank(charset)) {
            this.charset = charset;
        }
        DESKeySpec keySpec = new DESKeySpec(deSkey.getBytes(this.charset));// 设置密钥参数
        iv = new IvParameterSpec(DESIV);// 设置向量
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
        key = keyFactory.generateSecret(keySpec);// 得到密钥对象
    }

    /**
     * 加密
     * @author ershuai
     * @date 2017年4月19日 上午9:40:53
     * @param data
     * @return
     * @throws Exception
     */
    public String encode(String data) throws Exception {
        Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
        enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
        byte[] pasByte = enCipher.doFinal(data.getBytes(charset));
        return Base64.getEncoder().encodeToString(pasByte);
    }

    /**
     * 解密
     * @author ershuai
     * @date 2017年4月19日 上午9:41:01
     * @param data
     * @return
     * @throws Exception
     */
    public String decode(String data) throws Exception {
        Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        deCipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] pasByte = deCipher.doFinal(Base64.getDecoder().decode(data));
        return new String(pasByte, charset);
    }
    
    public static void main(String[] args) {
//    	String randomCode = createRandomCode();
//        String encodeStr = "y13800138000|" + randomCode;
//        System.out.println(encryptString(encodeStr));
//
//        System.out.println(EncryptUtil.decryptionString(encryptString(encodeStr)).split("\\|")[0]);

        System.out.println(EncryptUtil.decryptionString("+v8RvyfKuu/yJ8JDaicl1GV22KSQtfaMwj2GJaEhAcs=").split("\\|")[0]);
	}

    public static String encryptTenantString(int tenantId){
    	String tenantCode = "+";
    	while(tenantCode.indexOf("+")> -1 || tenantCode.indexOf("/")> -1)
        {
        	 String randomCode = createRandomCode();
             String encodeStr = String.valueOf(tenantId) + "|" + randomCode;
             tenantCode = encryptString(encodeStr);
        }
        return tenantCode;
    }
    
    
    public static String encryptTenantString(String tenantId){
    	String tenantCode = "+";
    	while(tenantCode.indexOf("+")> -1 || tenantCode.indexOf("/")> -1)
        {
             String encodeStr = String.valueOf(tenantId);
             tenantCode = encryptString(encodeStr);
        }
        return tenantCode;
    }

    public static String encryptString(String encodeStr){
        String resultStr = "";
        try {
            EncryptUtil des = new EncryptUtil(encryptKey, charset);
            resultStr = des.encode(encodeStr);
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultStr;
    }

    public static String decryptionString(String encodeStr){
        String resultStr = "";
        try {
            EncryptUtil des = new EncryptUtil(EncryptUtil.encryptKey, EncryptUtil.charset);
            resultStr = des.decode(encodeStr);
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultStr;
    }

    public static String createRandomCode(){
        Random random = new Random();
        StringBuffer randomCode = new StringBuffer();
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeArr[random.nextInt(codeArr.length)]);
            randomCode.append(strRand);
        }
        return randomCode.toString();
    }
}
