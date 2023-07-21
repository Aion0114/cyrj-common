package com.cyrj.common.enumeration;

/**
 * 锁库标志
 */
public enum LockFlag {

    LOCK("锁定",true),
    UMLOCK("未锁定",false);

    private String name;
    private Boolean code;

    LockFlag(String name, Boolean code) {
        this.name = name;
        this.code = code;
    }

    public Boolean getCode(){
        return code;
    }

    public String getName(){
        return name;
    }
}
