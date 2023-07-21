package com.cyrj.common.enumeration;

public enum DictionaryType {
    BRAND("品牌",3),
	SENDROUTE("配送路线",4);

    private String name;
    private int code;

    DictionaryType(String name, int code) {
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
