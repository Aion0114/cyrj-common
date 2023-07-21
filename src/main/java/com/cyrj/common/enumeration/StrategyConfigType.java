package com.cyrj.common.enumeration;

/**
 * 策略配置类型
 */
public enum StrategyConfigType {

    CHOICE_CONFIG("选择策略配置",1),
    DISPLAY_CONFIG("显示配置",2);

    private String name;

    private Integer code;

    StrategyConfigType(String name, Integer code) {
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
