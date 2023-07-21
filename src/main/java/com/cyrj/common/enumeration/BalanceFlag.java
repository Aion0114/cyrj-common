package com.cyrj.common.enumeration;

/**
 * 是否结存
 */
public enum BalanceFlag {
    BALANCEING("正在结存", 1),
    BALANCED("已结存", 2),
    BACK_BALANCE("反结存", 3);

    private String name;
    private Integer code;

    BalanceFlag(String name, Integer code) {
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
