package com.cyrj.sys.pojo;

import com.cyrj.common.enumeration.DeleteFlag;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class TenantInfoHistory {

    private Integer id;

    private Integer tenantId;

    @ApiModelProperty(value="数据库名称")
    private String dbName;

    @ApiModelProperty(value="别名")
    private String alias;

    @ApiModelProperty(value="服务器名字")
    private String serverIp;

    @ApiModelProperty(value="服务器端口")
    private String serverPort;

    @ApiModelProperty(value="管理员账号")
    private String managerUser;

    @ApiModelProperty(value="管理员密码")
    private String managerPassword;

    @ApiModelProperty(value="账号名称")
    private String accountName;

    @ApiModelProperty(value="是否显示")
    private Integer  display;

    @ApiModelProperty(value="结账截止日期")
    private Date settlementDate;

    @ApiModelProperty(value="服务器状态")
    private Integer status;

    @ApiModelProperty(value="数据库账号")
    private String dbAccount;

    @ApiModelProperty(value = "数据库密码")
    private String dbPassword;

    @ApiModelProperty(value = "新账号")
    private String newAccount;

    @ApiModelProperty(value = "所属年份")
    private Integer years;

    private Integer  delflag;

    private Integer creator;

    private Integer modifier;

    private Date createTime;

    private Date modifiTime;

    private Date updateNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getManagerUser() {
        return managerUser;
    }

    public void setManagerUser(String managerUser) {
        this.managerUser = managerUser;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getModifier() {
        return modifier;
    }

    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiTime() {
        return modifiTime;
    }

    public void setModifiTime(Date modifiTime) {
        this.modifiTime = modifiTime;
    }

    public Date getUpdateNo() {
        return updateNo;
    }

    public void setUpdateNo(Date updateNo) {
        this.updateNo = updateNo;
    }

    public String getNewAccount() {
        return newAccount;
    }

    public void setNewAccount(String newAccount) {
        this.newAccount = newAccount;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public TenantInfoHistory() {
        super();
    }

    public TenantInfoHistory(String dbName) {
        this.delflag = DeleteFlag.VALID.getCode();
        this.dbName = dbName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

}
