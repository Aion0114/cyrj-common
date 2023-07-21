package com.cyrj.common.enumeration;

public enum IsLockingFlag {

    LOCKING("锁",1),
    UNLOCKED("不锁",0);

    private String name;
    private int code;

    IsLockingFlag(String name, int code) {
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
