package com.cyrj.common.enumeration;

/**
 * 电脑认证状态
 */
public enum AuthenticationStatus {

    INACTIVATED("未激活",0),
    ACTIVATED("已激活",1);

    private String name;
    private int code;

    AuthenticationStatus(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public String getName(){
        return name;
    }
}
