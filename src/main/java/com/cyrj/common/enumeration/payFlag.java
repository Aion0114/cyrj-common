package com.cyrj.common.enumeration;

/**
 * 财务往来结算明细表中是否已结的枚举类
 * @author Administrator
 *
 */
public enum payFlag {

	payed("已结",2),
	paysome("部分已结",1),
    notPay("未结",0);

    private String name;
    private int code;

    payFlag(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public String getName(){
        return name;
    }
}
