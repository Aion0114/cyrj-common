package com.cyrj.common.enumeration;

/**
 * 盘点状态
 */
public enum InventoryStatus {

    NO_CHECK("未盘点",0),
    HAS_CHECKED("已盘点",1);

    private String name;
    private Integer code;

    InventoryStatus(String name, Integer code) {
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
