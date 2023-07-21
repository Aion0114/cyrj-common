package com.cyrj.common.enumeration;

public enum TenantStatus {

    WAIT_ACTIVATE("待激活", 1),
    HAS_ACTIVATE("已激活", 2),
    IS_OVERDUE("已过期", 3);

    private String name;
    private Integer code;

    TenantStatus(String name, Integer code) {
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
