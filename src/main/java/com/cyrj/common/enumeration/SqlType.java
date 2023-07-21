package com.cyrj.common.enumeration;

//sql脚本类型
public enum SqlType {

    CREATEDATABASE("创库","CreateDataBase"),
    CREATETABLE("创表","CreateTable"),
    CREATEPROCEDURE("创存储过程","CreateProcedure"),
    DATAINIT("初始化数据","DataInit");
    private String name;
    private String code;

    SqlType(String name, String code) {
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
