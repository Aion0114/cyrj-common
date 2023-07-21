package com.cyrj.common.enumeration;

public enum BillStatus {

    WAIT_CHECK("待审核", 1),
    PASS_CHECK("审核通过", 2);

    private String name;
    private Integer code;

    BillStatus(String name, Integer code) {
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
