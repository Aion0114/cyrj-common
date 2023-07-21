package com.cyrj.common.enumeration;

/**
 * 加减库存标志
 */
public enum PlusMinus {

    PLUS("加库存",1),
    MINUS("减库存",2);

    private String name;
    private int code;

    PlusMinus(String name, int code) {
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
