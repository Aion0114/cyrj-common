package com.cyrj.common.enumeration;

/**
 * 库存进出标志
 */
public enum InOrOutFlag {

    IN("进库存",1),
    OUT("出库存",2),
    IN_AND_OUT("进出库存", 3),
    NO_IN_OR_OUT("平库存", 4),
    COST_ADJUST("库存成本调整", 5);

    private String name;
    private Integer code;

    InOrOutFlag(String name, Integer code) {
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
