package com.cyrj.common.enumeration;

public enum UserStatus {

    DISABLE("停用", 0),
    NORMAL("正常", 1);

    private String name;
    private Integer code;

    UserStatus(String name, Integer code) {
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
