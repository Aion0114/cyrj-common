package com.cyrj.common.enumeration;

/**
 * 开业数据清除类型
 */
public enum CleanType {

    CLEAN_ALL_DATA("清除全部数据","1"),
    CLEAN_BUSINESS_DATA("清除进销存数据","2"),
    CLEAN_NOCHECK_BUSINESS_DATA("清除未过账销售数据","3");

    private String name;

    private String code;

    CleanType(String name, String code) {
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
