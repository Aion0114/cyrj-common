package com.cyrj.common.db;

/**
 * Created by czx on 2017/9/20.
 */
public class DataBaseInfo {

    private String dbName;
    private String dbAccount;
    private String dbPassword;
    private String serverIp;
    private Integer serverPort;
    
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getDbAccount() {
		return dbAccount;
	}
	public void setDbAccount(String dbAccount) {
		this.dbAccount = dbAccount;
	}
	public String getDbPassword() {
		return dbPassword;
	}
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	public Integer getServerPort() {
		return serverPort;
	}
	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}
    
}
