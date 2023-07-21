package com.cyrj.common.enumeration;

public enum DisableStatus {

    DISABLE("废除", 0),
    ENABLE("正常", 1);

    private String name;
    private Integer code;

    DisableStatus(String name, Integer code) {
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
