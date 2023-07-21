package com.cyrj.common.enumeration;

/**
 * 下发权限任务类型
 */
public enum IssuedTaskType {

    SYNCHRO_TYPE("同步数据类型",1),
    CANCEL_TYPE("注销类型",2);

    private String name;
    private Integer code;

    IssuedTaskType(String name, Integer code) {
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
