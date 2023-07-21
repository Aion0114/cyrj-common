package com.cyrj.common.enumeration;

/**
 * 计量方式
 */
public enum MeteringType {

    SCATTERED("散量",1),
    ITEM("件量",2);

    private String name;
    private Integer code;

    MeteringType(String name, Integer code) {
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
