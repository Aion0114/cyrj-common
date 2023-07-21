package com.cyrj.common.enumeration;

/**
 * 用户类型
 */
public enum EmployeeType {

    TENANT_MANAGER("商户", 1),
    EENANT_EMPLOYEE("商户员工", 2);

    private String name;
    private Integer code;

    EmployeeType(String name, Integer code) {
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
