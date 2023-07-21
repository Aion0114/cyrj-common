package com.cyrj.sys.pojo;

import io.swagger.annotations.ApiModelProperty;

public class Development {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "应用编号")
    private Integer clientId;

    @ApiModelProperty(value = "应用名称")
    private String appName;

    @ApiModelProperty(value = "应用类型")
    private Integer appType;

    @ApiModelProperty(value = "绑定服务id，逗号分隔")
    private String bindService;

    @ApiModelProperty(value = "请求检验方式")
    private String checkMode;

    @ApiModelProperty(value = "IP白名单")
    private String iptable;

    @ApiModelProperty(value = "访问应用")
    private String accessToken;

    @ApiModelProperty(value = "第三方登录验证值")
    private String encryptVal;

    @ApiModelProperty(value = "是否删除 0-已删除 1-未删除")
    private Integer delflag;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getCheckMode() {
        return checkMode;
    }

    public void setCheckMode(String checkMode) {
        this.checkMode = checkMode;
    }

    public String getIptable() {
        return iptable;
    }

    public void setIptable(String iptable) {
        this.iptable = iptable;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getEncryptVal() {
        return encryptVal;
    }

    public void setEncryptVal(String encryptVal) {
        this.encryptVal = encryptVal;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    public String getBindService() {
        return bindService;
    }

    public void setBindService(String bindService) {
        this.bindService = bindService;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
}
