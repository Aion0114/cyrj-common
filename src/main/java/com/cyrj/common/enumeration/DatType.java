package com.cyrj.common.enumeration;

/**
 * 基础资料类型
 */
public enum DatType {


    GOODS_TYPE("商品类型",1),
    SUPPLIER_TYPE("供应商类型",2),
    EMPLOYEE_TYPE("员工类型",3),
    CUSTOMER_TYPE("客户类型",4),
    ORGANIZATION_TYPE("机构类型",5),
    ORGANIZATION_AREA_TYPE("机构区域类型",6);

    private String name;
    private Integer code;

    DatType(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public Integer getCode(){
        return code;
    }

    public String getName(){
        return name;
    }
}
