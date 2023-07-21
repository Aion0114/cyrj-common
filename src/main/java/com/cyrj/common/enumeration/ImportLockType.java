package com.cyrj.common.enumeration;

/**
 * 上传文件类型
 */
public enum ImportLockType {

    COMMODITY_FILE("商品档案","server-import-commodityFile-"),
    OPENING_SHEET("期初单","server-import-openingSheet-"),
    HISTORICAL_SELLING_PRICE("历史售价","server-import-historicalSellingPrice-"),
    POS_LADDER_PRICE_FILE("pos收银价格阶梯","pos-import-ladderPricr-"),
    CUSTOMER_FILE("客户档案","server-import-customerFile-");

    private String name;
    private String code;

    ImportLockType(String name, String code) {
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