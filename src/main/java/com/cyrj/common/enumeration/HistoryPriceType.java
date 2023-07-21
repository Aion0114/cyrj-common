package com.cyrj.common.enumeration;

/**
 * 历史售价类型
 */
public enum HistoryPriceType {

    CUSTOMER_SALE_PRICE("客户售价类型","1"),
    SUPPLIER_SALE_PRICE("供应商售价类型","2");

    private String name;
    private String code;

    HistoryPriceType(String name, String code) {
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
