package com.cyrj.common.enumeration;

/**
 * 单据类型Code
 */
public enum BillTypeCode {

    PURCHASE_ORDER_BILL("采购订货单","101"),
    PURCHASE_IN_BILL("采购入仓单","102"),
    PURCHASE_RETURN_BILL("采购退货单","103"),
    SALE_PURCHASE_BILL("申购单","104"),
    SALE_QUOTE_BILL("销售报价单","201"),
    SALE_OUT_BILL("销售出仓单","202"),
    SALE_RETURN_BILL("销售退货单","203"),
    SALE_ADJUST_BILL("调价单","204"),
    SALE_PROMOTION_BILL("促销单","205"),
    SALE_ORDER_BILL("销售订单","206"),
    SALE_OTHER_BILL("其他出库单","210"),
    SALE_RECEIPTS_BILL("销售收款单","1004"),
    CHECK_BILL("盘点单","301"),
    TRANSFER_BILL("转仓单","302"),
    MACHINING_BILL("商品加工单","303"),
    COST_ADJUST_BILL("成本调价单","304"),
    BALANCE_BILL("结存单","305"),
    BEGIN_BILL("期初导入单","306"),
    PURCHASE_KEEP_ACCOUNTS_BILL("采购记账单","1001"),
    PURCHASE_PAY_BILL("采购付款单","1002"),
    BORROW_BILL("往来借款单","1005"),
    REPAYMENT_BILL("往来还款单","1006"),
	OTHERS_INCOME_BILL("其它收入单","1007"),
    COST_EXPEND_BILL("费用支出单","1008"),
    TRANSFER_OF_BANK_BILL("银行转账单","1009"),
    CHECK_UP_BILL("盘点收入单","1014"),
    CHECK_DOWN_BILL("盘点支出单","1015"),
    COST_UP_BILL("成本上调单","1016"),
    COST_DOWN_BILL("成本下调单","1017"),
    PURCHASE_YF_BILL("采购预付单","1011"),
    OTHER_INSTOCK_BILL("其它入库单", "309"),
    OTHER_OUTSTOCK_BILL("其它出库单", "310"),
    OTHER_IN_STOCK_BILL("其它入库收入单", "1025"),
    OTHER_OUT_STOCK_BILL("其它出库支出单", "1026"),
    REPORT_LOSS_BILL("报损单", "307"),
    REPORT_OVERFLOW_BILL("报溢单", "308"),
    REPORT_UP_BILL("报溢收入单", "1023"),
    REPORT_DOWN_BILL("报损支出单", "1024"),
    SALE_YS_BILL("销售预收单","1012");
	

    private String name;
    private String code;

    BillTypeCode(String name, String code) {
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
