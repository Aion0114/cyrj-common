package com.cyrj.common.enumeration;

public enum ACStatusFlag {

    WAIT_FOR_EXAM("待审核",1),
    HAD_EXAM("已审核",2);

    private String name;
    private int code;

    ACStatusFlag(String name, int code) {
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
