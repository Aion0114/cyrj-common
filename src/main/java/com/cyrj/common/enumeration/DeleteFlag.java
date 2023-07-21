package com.cyrj.common.enumeration;

public enum DeleteFlag {

    DELETE("删除",0),
    VALID("正常",1);

    private String name;
    private int code;

    DeleteFlag(String name, int code) {
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
