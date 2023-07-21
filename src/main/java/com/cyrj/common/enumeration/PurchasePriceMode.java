package com.cyrj.common.enumeration;

/**
 * 采购价格方式 1-最新进价  2-面价  3-配送价
 */
public enum PurchasePriceMode {

    NEWST_PURCHASE_PRICE("最新进价","1"),
    FACTORY_PRICE("面价","2"),
    DISTRIBUTION_PRICE("配送价","3");

    private String name;
    private String code;

    PurchasePriceMode(String name, String code) {
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
