package com.cyrj.common.enumeration;

/**
 * Created by ChuWang on 2018/8/23.
 */
public enum BackSettleAccountsStatus {

    RESULT_1("选定日期未发现结账会计凭证，不允许反结账! ",1),
    RESULT_2("选定日期后发现结账会计凭证，请按顺序反结账！",2);

    private String name;
    private Integer code;

    BackSettleAccountsStatus(String name, Integer code) {
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
