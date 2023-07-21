package com.cyrj.common.enumeration;

/**
 * 窗口类型
 */
public enum WindowStatus {

    OPEN("打开", 1),
    CLOSE("关闭", 2);

    private String name;
    private Integer code;

    WindowStatus(String name, Integer code) {
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
