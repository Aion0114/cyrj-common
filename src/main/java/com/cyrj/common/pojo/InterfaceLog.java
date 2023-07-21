package com.cyrj.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;

import java.text.DateFormat;
import java.time.ZonedDateTime;
import java.util.Date;

public class InterfaceLog {

    private String id;

    private long elapsedTime; //接口用时


    private Date endTime; //接口调用时间

    private String methodName;//方法类名

    private String methodNameCN;//方法名称

    private String serverIp;//调用ip地址

    private String url;//调用url地址

    private String methodArg;//调用参数

    private String dbName;//调用数据库名称

    private String operator;//操作员

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethodArg() {
        return methodArg;
    }

    public void setMethodArg(String methodArg) {
        this.methodArg = methodArg;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getMethodNameCN() {
        return methodNameCN;
    }

    public void setMethodNameCN(String methodNameCN) {
        this.methodNameCN = methodNameCN;
    }
}
