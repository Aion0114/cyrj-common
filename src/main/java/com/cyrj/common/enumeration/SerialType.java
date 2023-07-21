package com.cyrj.common.enumeration;

public enum SerialType {

    STOREHOUSE("仓库编号","1"),
    STOREHOUSE_AREA("仓库区域编号","2"),
    TYPE("档案类型编号","3"),
    CUSTOMER("客户编号","4"),
    EMPLOYEE("员工编号","5"),
    SUPPLIER("供应商编号","6"),
    DICTIONARY_TYPE("字典类型编号","7"),
    DICTIONARY("字典编号","8"),
    GOODS("商品编号","9"),
    CONTACTS("联系人编号","10"),
    AUTHORGANIZATION("机构编号","11");

    private String name;
    private String code;

    SerialType(String name, String code) {
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
