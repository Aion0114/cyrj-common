package com.cyrj.common.enumeration;

public enum YesOrNo {

    NO("否", 0),
    YES("是", 1);

    private String name;
    private Integer code;

    YesOrNo(String name, Integer code) {
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
