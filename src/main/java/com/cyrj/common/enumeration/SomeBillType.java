package com.cyrj.common.enumeration;

/**
 * Created by ChuWang on 2018/8/8.
 */
public enum SomeBillType {

    AV_BILL("记账凭证",1),

    BORROW_BILL("往来借款单",8),
    REPAYMENT_BILL("往来还款单",9),

    OTHER_INCOME_BILL("其他收入单",10),
    COST_EXPEND_BILL("费用支出单",11),
    TRANSFER_OF_BANK_BILL("银行转账单",12),
    PURCHASE_ADVANCE_BILL("采购预付单",14),
    SALE_RECEIVE_BILL("销售预收单",15);

    private String name;
    private Integer code;

    SomeBillType(String name, Integer code) {
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
