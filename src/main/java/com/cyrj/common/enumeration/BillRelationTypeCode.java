package com.cyrj.common.enumeration;

/**
 * Created by ChuWang on 2018/10/24.
 */
public enum BillRelationTypeCode {

    PURCHASE_MAIN_TABLE("采购主表",1),
    SALE_MAIN_TABLE("销售主表",2),
    CHECK_MAIN_TABLE("盘点主表",3),
    COST_MAIN_TABLE("成本调价主表",4),
	IM_MACHINING_MAIN("加工单主表",5),
    IM_OTHERSTOCK_MAIN("其它出入库主表",8),
    IM_REPORT_MAIN("报损报溢主表",7),
    IM_TRANSFER_MAIN("转仓主表",6);

    private String name;
    private Integer code;

    BillRelationTypeCode(String name, Integer code) {
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
