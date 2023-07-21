package com.cyrj.common.enumeration;

public enum DirectionType {
	
	  LEND("借",0),
	  LOAN("贷",1);
	
	  private String value;
	  
	  private int num;

	  DirectionType(String value,int num) {
		  this.value= value;
		  this.num = num;
	  }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	  
	  
}
