package com.cyrj.common.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;

import java.text.DateFormat;
import java.util.Date;

public class ProjectException {

    private String id;

    private String message;//异常内容

    private Date exceptionTime; //异常时间

    private String url;//调用url地址

    private String methodArg;//调用参数

    private String dbName;//调用数据库名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getExceptionTime() {
        return exceptionTime;
    }

    public void setExceptionTime(Date exceptionTime) {
        this.exceptionTime = exceptionTime;
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
}
