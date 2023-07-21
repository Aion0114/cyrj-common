package com.cyrj.common.enumeration;

public enum OrderStatus {
//0待确认 1待发货 3配送中 4已收货 5已退款 6已关闭
    STATUS_BECONFIRMED("待确认",0),
    STATUS_CONSIGNMENT("待发货",1),
    STATUS_DELIVERYING("配送中",3),
    STATUS_BERECEIVED("已收货",4),
    STATUS_REFUNDED("已退款",5),
    STATUS_CLOSED("已关闭",6);


    private String name;
    private int code;

    OrderStatus(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
