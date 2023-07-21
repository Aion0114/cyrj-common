package com.cyrj.common.enumeration;

//采购单据类型
public enum PurchaseType {

    PURCHASE_ORDER_BILL("采购订货单","1"),
    PURCHASE_IN_BILL("采购入仓单","2"),
    PURCHASE_RETURN_BILL("采购退货单","3"),
    PURCHASE_PAY_BILL("采购付款单","4");
    private String name;
    private String code;

    PurchaseType(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getCode(){
        return code;
    }

    public String getName(){
        return name;
    }
}
