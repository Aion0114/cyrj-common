package com.cyrj.common.enumeration;

//终端类型，1：windows，2：android，3：ios，4：linux，100：其他
public enum TerminalType {
	WINDOWS ("pc系统",1),
	ANDROID ("android系统",2),
	IOS ("ios系统",3),
	LINUX ("linux系统",4),
	OTHER ("其他",100);

    private String name;
    private int code;

    TerminalType (String name, int code) {
        this.name = name;
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public String getName(){
        return name;
    }
}
