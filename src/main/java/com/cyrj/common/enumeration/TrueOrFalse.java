package com.cyrj.common.enumeration;

/**
 * true or false
 */
public enum TrueOrFalse {

    TRUE("true",true),
    FALSE("false",false);

    private String name;
    private Boolean code;

    TrueOrFalse(String name, Boolean code) {
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
