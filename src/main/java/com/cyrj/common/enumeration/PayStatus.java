package com.cyrj.common.enumeration;

/**
 * 结款状态
 */
public enum PayStatus {

    NOT_PAY("未结款", 0),
    PART_PAY("部分结款", 1),
    FINISH_PAY("已结款", 2);

    private String name;
    private Integer code;

    PayStatus(String name, Integer code) {
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
