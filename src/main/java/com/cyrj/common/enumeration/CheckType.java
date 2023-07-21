package com.cyrj.common.enumeration;

/**
 * 审核类型
 */
public enum CheckType{

    CHECK_TYPE("审核",1),
    BACK_CHECK_TYPE("反审",2),
    
    NOT_CHECK("未审核",1),
    CHECKED("已审核",2);

    private String name;
    private Integer code;

    CheckType(String name, Integer code) {
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
