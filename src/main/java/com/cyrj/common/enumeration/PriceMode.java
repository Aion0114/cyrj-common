package com.cyrj.common.enumeration;

/**
 * 价格方式 1-售价1   2-售价2    3-售价3    4-售价4    5-售价5    6-配送价   7-零售价   8-会员价     9-面价 10-最新进价 
 */
public enum PriceMode {

    SALE_PRICE_1("售价1","1"),
    SALE_PRICE_2("售价2","2"),
    SALE_PRICE_3("售价3","3"),
    SALE_PRICE_4("售价4","4"),
    SALE_PRICE_5("售价5","5"),
    DISTRIBUTION_PRICE("配送价","6"),
    RETAIL_PRICE("零售价","7"),
    VIP_PRICE("会员价","8"),
    NEWEST_PRICE("最新进价","10"),
    FACTORY_PRICE("面价","9"),
    COST_PRICE("成本价","11");

    private String name;
    private String code;

    PriceMode(String name, String code) {
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
