package com.cyrj.common.enumeration;

public enum StatusFlag {
	
	DISABLE("删除",0),
    ENABLE("正常",1);

    private String name;
    private int code;

    StatusFlag(String name, int code) {
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
