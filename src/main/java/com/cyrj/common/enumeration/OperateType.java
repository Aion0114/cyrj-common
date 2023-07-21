package com.cyrj.common.enumeration;

/**
 * 操作类型
 */
public enum OperateType {

    CHECK_TYPE("审核",1),
    BACK_CHECK_TYPE("反审",2),
    BALANCE_TYPE("结存",3),
    BACK_BALANCE_TYPE("反结存",4);

    private String name;
    private Integer code;

    OperateType(String name, Integer code) {
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
