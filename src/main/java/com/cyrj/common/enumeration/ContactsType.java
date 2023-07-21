package com.cyrj.common.enumeration;

public enum ContactsType {

    SUPPLIER("供应商联系人",1),
    CUSTOMER("客户联系人",2);

    private String name;
    private Integer code;

    ContactsType(String name, Integer code) {
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
