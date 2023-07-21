package com.cyrj.common.enumeration;

/**
 * 策略配置模块
 */
public enum StrategyConfigModule {

    AFTER_SALE("售后策略",1),
    FRONT("前台策略",2),
    SECURITY("安全策略",3),
    BACKUPS("备份策略",4),
    SALE("销售策略",5),
    PURCHASE("进货策略",6),
    BASIC_PARAMETER("基本参数",7),
    PAY_CODE("扫码支付设置",8),
    APPEARANCE("外观设置",9),
    REPORT("报表设置",10),
    LANGUAGE("语言设置",11);

    private String name;

    private Integer code;

    StrategyConfigModule(String name, Integer code) {
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
