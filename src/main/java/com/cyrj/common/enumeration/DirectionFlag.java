package com.cyrj.common.enumeration;

public enum DirectionFlag {
	minls("减",0),
	plus("加",1);

    private String name;
    private int code;

    DirectionFlag(String name, int code) {
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
