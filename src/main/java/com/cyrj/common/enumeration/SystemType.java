package com.cyrj.common.enumeration;

//系统平台类型
public enum SystemType {

    C8("C8","C8"),
    B2("B2","B2"),
    qc("qc","qc");
    private String name;
    private String code;

    SystemType(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getCode(){
        return code;
    }

    public String getName(){
        return name;
    }
}
