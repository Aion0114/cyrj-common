package com.cyrj.common.util;

import java.math.BigDecimal;

public class ZeroUtil {
	
	public static final BigDecimal zero = new BigDecimal(0);
	
	public static final BigDecimal minus = new  BigDecimal("-1");
	
	public static BigDecimal constant = new BigDecimal(0);
	
	public static boolean isZero(BigDecimal val) {
		return val==null||zero.compareTo(val)==0;
	}
	
	/**
	 * 初始化值
	 * @param val
	 * @return
	 */
	public static BigDecimal init(BigDecimal val) {
		return isZero(val)?new BigDecimal(0):val;
	}
	
	public static BigDecimal init(Object val) {
		try {
			if (val==null) {
				return zero;
			}
			return new BigDecimal(val.toString());
		} catch (Exception e) {
			System.out.println(e);
			return zero;
		}
		
	 }
	
	public static BigDecimal initFinal(BigDecimal val) {
		return val==null?zero:val;
	}
	
	public static BigDecimal minus(Object val) {
		try {
			val = new BigDecimal(val.toString());
			return minus(val);
		} catch (Exception e) {
			System.out.println(e);
			return zero;
		}
	}
	
	public static BigDecimal minus(BigDecimal val) {
		return val==null?zero:val.multiply(minus);
	}
    
	//判断是否大于0
	public static boolean greaterThanZero(BigDecimal val)
	{
		return val.compareTo(zero) == 1 ? true : false;  
	}
}
