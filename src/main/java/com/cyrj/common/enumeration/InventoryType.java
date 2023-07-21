package com.cyrj.common.enumeration;

/**
 * 盘点方式
 */
public enum InventoryType {

    ALL("全部盘点","1"),
    GOODS_TYPE("商品类型","2"),
    GOODS_CODE("商品编号","3"),
    SUPPLIER("供应商","4"),
    NEGATIVE_STOCK("负库存","5"),
    SALED_GOODS("已销商品","6"),
	CONTAINER_CODE("货位编号","7");

    private String name;
    private String code;

    InventoryType(String name, String code) {
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
