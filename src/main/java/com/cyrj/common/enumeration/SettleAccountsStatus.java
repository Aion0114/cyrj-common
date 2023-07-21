package com.cyrj.common.enumeration;

/**
 * Created by ChuWang on 2018/8/23.
 */
public enum SettleAccountsStatus {

    RESULT_1("科目期初余额试算，资产和负债不平衡，不允许结账！",1),
    RESULT_2("当前日期已有结账会计凭证，不允许再结账！",2),
    RESULT_3("当前日期前未发现待结账的会计凭证，不需要结账！",3),
    RESULT_4("会计凭证借贷不平，不允许结账！",4);

    private String name;
    private Integer code;

    SettleAccountsStatus(String name, Integer code) {
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
