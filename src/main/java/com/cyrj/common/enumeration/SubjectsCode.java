package com.cyrj.common.enumeration;

public enum SubjectsCode {

	
	CASH_BANK("现金银行","001001",3),
	SHOULD_RECIPT("应收账款","001002",4),
	SHOULD_PAY("应付账款","001002",10),
	OTHER_INCOME("其它收入","004002",25);
	
	private String name;
    private String code;
    private int id;

    SubjectsCode(String name, String code,int id) {
        this.name = name;
        this.code = code;
        this.id = id;
    }

    public String getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

	public int getId() {
		return id;
	}

    
}
