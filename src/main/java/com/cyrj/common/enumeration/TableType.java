package com.cyrj.common.enumeration;

/**
 * 表类型
 */
public enum TableType {

    TABLE_DAT_CONTACTS("1","DAT_CONTACTS"),
    TABLE_DAT_CUSTOMER("2","DAT_CUSTOMER"),
    TABLE_DAT_EMPLOYEE("3","DAT_EMPLOYEE"),
    TABLE_DAT_GOODS("4","DAT_GOODS"),
    TABLE_DAT_INTERCOURSE("5","DAT_INTERCOURSE"),
    TABLE_DAT_ORGANIZATION("6","DAT_ORGANIZATION"),
    TABLE_DAT_SUPPLIER("7","DAT_SUPPLIER");

    private String name;
    private String code;

    TableType(String name, String code) {
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
