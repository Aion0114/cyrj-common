package com.cyrj.common.enumeration;

/**
 * 单据类型Code
 */
public enum BillTypeCodeFA {

	JZPZ_BILL("记账凭证","0000"),
	JSJZD_BILL("借式记账单","0001"),
	DSJZD_BILL("贷式记账单","0002"),
	CGJZD_BILL("采购记账单","1001"),
	CGFKD_BILL("采购付款单","1002"),
	XSJZD_BILL("销售记账单","1003"),
	XSSKD_BILL("销售收款单","1004"),
	WLJKD_BILL("往来借款单","1005"),
	WLHKD_BILL("往来还款单","1006"),
	QTSRD_BILL("其它收入单","1007"),
	FYZCD_BILL("费用支出单","1008"),
	YHZZD_BILL("银行转账单","1009"),
	KCJZD_BILL("库存记账单","1010"),
	CGYFD_BILL("采购预付单","1011"),
	XSYSD_BILL("销售预收单","1012"),
	XSJZD_BILL_2("销售记账单","1013"),
	WLJZD_BILL("往来记账单","1018"),
	WLFKD_BILL("往来付款单","1019"),
	WLSKD_BILL("往来借款单","1020"),
	CXPZ_BILL("冲销凭证","1021");

    private String name;
    private String code;

    BillTypeCodeFA(String name, String code) {
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
