package com.cyrj.common.enumeration;

public enum IsDisplayFlag {
	Display("显示",1),
    unDisplay("不显示",0);

    private String name;
    private int code;

    IsDisplayFlag(String name, int code) {
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
