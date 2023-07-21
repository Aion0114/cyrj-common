package com.cyrj.common.enumeration;

/**
 * 单据流水类型
 */
public enum BillSerialType {

    PURCHASE_ORDER_BILL("采购订货单","PO"),
    PURCHASE_IN_BILL("采购入仓单","PS"),
    PURCHASE_RETURN_BILL("采购退货单","PP"),
    PURCHASE_PAY_BILL("采购付款单","PR"),
    SALE_QUOTE_BILL("销售报价单","SQ"),
    SALE_OUT_BILL("销售出仓单","SS"),
    SALE_RECEVE_BILL("销售收款单","SP"),
    SALE_RETURN_BILL("销售退货单","SR"),
    SALE_ADJUST_BILL("销售调价单","SA"),
    CHECK_BILL("盘点单","IC"),
    TRANSFER_BILL("转仓单","IT"),
    MACHINING_BILL("商品加工单","IM"),
    PROMOTION_BILL("促销单", "PM"),
    COST_ADJUST_BILL("成本调价单","CA");

    private String name;

    private String code;

    BillSerialType(String name, String code) {
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
