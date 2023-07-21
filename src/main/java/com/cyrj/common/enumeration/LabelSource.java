package com.cyrj.common.enumeration;

/**
 * 标签来源
 */
public enum LabelSource {

    SYSTEM("系统", 0),
    CUSTOMIZE("自定义", 1);

    private String name;
    private Integer code;

    LabelSource(String name, Integer code) {
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
