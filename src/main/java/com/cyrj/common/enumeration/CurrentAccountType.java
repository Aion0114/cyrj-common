package com.cyrj.common.enumeration;

public enum CurrentAccountType {
	
	XSCCD("销售出仓单","应收",1),
	XSTHD("销售退货单","应收",1),
    CGJCD("采购进仓单","应付",2),
    CGTHD("采购退货单","应付",2),
    QTZC("其他支出","其他应收",3),
    QTSR("其它收入","其他应付",4),
    XSDD("销售订单","预收",5),
    XSYSD("销售预收单","预收",5),
    CGDD("采购订单","预付",6),
    CGYFD("采购预付单","预付",6),
    YSQC("期初余额","应收期初",7),
    YFQC("期初余额","应付期初",8),
    OTHER("其他","其他",9),
    QTYS_GYS("其他支出","其他应收-供应商",3);

    private String av;
    private String typeName;
    private Integer id;

    CurrentAccountType(String av,String typeName, Integer id) {
        this.av = av;
        this.typeName = typeName;
        this.id = id;
    }

    public Integer getId(){
        return id;
    }
    
    public String getTypeName() {
    	return typeName;
    }

    public String getAv(){
        return av;
    }

}
