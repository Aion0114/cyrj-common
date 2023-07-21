package com.cyrj.common.enumeration;

/**
 * 支付类型   1-微信支付  2-支付宝   3-会员卡
 */
public enum PayPlatform  {

	WECHAT ("微信支付","1"),
	ALIPAY ("支付宝","2"),
	MEMBER ("会员卡","3");

    private String name;
    private String code;

    PayPlatform (String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getCode(){
        return code;
    }

    public String getName(){
        return name;
    }
}