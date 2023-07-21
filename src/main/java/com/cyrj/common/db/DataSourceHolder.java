package com.cyrj.common.db;

public class DataSourceHolder {

	 //线程本地环境
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	//设置数据源
	public static synchronized void setDataSource(String dbname) {
		contextHolder.set(dbname);
	}

	//获取数据源
	public static String getDataSource() {
		return contextHolder.get();
	}
	
	//清除数据源
	public static void clearDataSource() {
		contextHolder.remove();
	}

}
