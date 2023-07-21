package com.cyrj.common.enumeration;

public enum VerificationCodeType {

    REGISTER("注册验证码", 0),
    FINDPWD("找回用户密码验证码", 1),
    MODIFYPHONE("更换手机号验证码", 2);

    private String name;
    private Integer code;

    VerificationCodeType(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public Integer getCode(){
        return code;
    }

    public String getName(){
        return name;
    }
}
