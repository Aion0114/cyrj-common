package com.cyrj.common.enumeration;

public enum RefundStatus {

    //（0：未退款，1：已退款，2：退款中，4：退款待审核，5：退款审核不通过）
    UNREFUNDED("未退款",0),
    REFUNDED("已退款",1),
    REFUNDING("退款中",2),
    REFUND_AUDIT("退款待审核",4),
    REFUND_NOT_APPROVED("退款待审核",5);

    private String name;
    private int status;

    RefundStatus(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public int getStatus(){
        return status;
    }

    public String getName(){
            return name;
        }

}
