package com.cyrj.common.enumeration;

public enum CodeType {

    REGISTER_TYPE("注册验证码",0),
    GET_BACK_TYPE("找回用户密码验证码", 1),
    CHANGE_PHONE_TYPE("更换手机号验证码", 2);

    private String name;
    private Integer code;

    CodeType(String name, Integer code) {
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
