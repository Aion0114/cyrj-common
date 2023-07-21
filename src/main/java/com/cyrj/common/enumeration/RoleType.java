package com.cyrj.common.enumeration;

/**
 * 角色类型
 */
public enum RoleType {

    SYSTEM_ROLE("系统角色", 1),
    CUSTOMIZE_ROLE("自定义角色", 2);

    private String name;
    private Integer code;

    RoleType(String name, Integer code) {
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
