package com.cyrj.pub.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class ExceptionLog {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "异常名称")
    private String exceptionName;

    @ApiModelProperty(value = "异常所在位置")
    private String exceptionLocation;

    @ApiModelProperty(value = "请求方式")
    private String requestType;

    @ApiModelProperty(value = "请求接口")
    private String requestUrl;

    @ApiModelProperty(value = "请求地址")
    private String requestAdr;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

    @ApiModelProperty(value = "系统平台类型，如C8、B2、......")
    private String systemType;

    @ApiModelProperty(value = "异常内容")
    private String exceptionMsg;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public String getExceptionLocation() {
        return exceptionLocation;
    }

    public void setExceptionLocation(String exceptionLocation) {
        this.exceptionLocation = exceptionLocation;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestAdr() {
        return requestAdr;
    }

    public void setRequestAdr(String requestAdr) {
        this.requestAdr = requestAdr;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }
}