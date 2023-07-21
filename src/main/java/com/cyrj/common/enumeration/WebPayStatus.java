package com.cyrj.common.enumeration;

public enum WebPayStatus {

    WAIT_PAY("待支付", 0),
    FINISH_PAY("已支付", 1);

    private String name;
    private Integer code;

    WebPayStatus(String name, Integer code) {
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
