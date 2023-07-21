package com.cyrj.pub.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class PubOddNumbers {
    @ApiModelProperty(value="")
    private Integer id;

    @ApiModelProperty(value="单据类型ID")
    private Integer billId;

    @ApiModelProperty(value="单据名称")
    private String billName;

    @ApiModelProperty(value="单号规则")
    private String oddRule;

    @ApiModelProperty(value="单号生成日期")
    private Date oddTime;

    @ApiModelProperty(value="单号分割符")
    private String oddSplit;

    @ApiModelProperty(value="前缀  放在连锁标识后面")
    private String oddPrefix;

    @ApiModelProperty(value="流水号长度,限制3-5")
    private Integer serialLength;

    @ApiModelProperty(value="日期模式 YYYYMMDD")
    private String dateMode;

    @ApiModelProperty(value="单号格式")
    private String oddFormat;

    @ApiModelProperty(value="开单数")
    private Integer oddNumber;

    @ApiModelProperty(value="当前记录号")
    private String currentNumber;

    @ApiModelProperty(value="备注")
    private String remark;

    @ApiModelProperty(value="租户ID")
    private Integer tenantId;

    @ApiModelProperty(value="创建人ID",hidden=true)
    private Integer creatorId;

    @ApiModelProperty(value="创建时间",hidden=true)
    private Date createTime;

    @ApiModelProperty(value="修改人ID",hidden=true)
    private Integer modifierId;

    @ApiModelProperty(value="修改时间",hidden=true)
    private Date modifiTime;

    @ApiModelProperty(value="删除标识",hidden=true)
    private Integer delflag;

    @ApiModelProperty(value="后缀",hidden=true)
    private String oddSuffix;

    private String interlockMark;// 连锁标识

    private String oddCode;//临时保存

    public String getOddCode() {
        return oddCode;
    }

    public void setOddCode(String oddCode) {
        this.oddCode = oddCode;
    }

    public String getInterlockMark() {
        return interlockMark;
    }

    public void setInterlockMark(String interlockMark) {
        this.interlockMark = interlockMark;
    }

    public String getOddSuffix() {
        return oddSuffix;
    }

    public void setOddSuffix(String oddSuffix) {
        this.oddSuffix = oddSuffix;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName == null ? null : billName.trim();
    }

    public String getOddRule() {
        return oddRule;
    }

    public void setOddRule(String oddRule) {
        this.oddRule = oddRule == null ? null : oddRule.trim();
    }

    public Date getOddTime() {
        return oddTime;
    }

    public void setOddTime(Date oddTime) {
        this.oddTime = oddTime;
    }

    public String getOddSplit() {
        return oddSplit;
    }

    public void setOddSplit(String oddSplit) {
        this.oddSplit = oddSplit == null ? null : oddSplit.trim();
    }

    public String getOddPrefix() {
        return oddPrefix;
    }

    public void setOddPrefix(String oddPrefix) {
        this.oddPrefix = oddPrefix == null ? null : oddPrefix.trim();
    }

    public Integer getSerialLength() {
        return serialLength;
    }

    public void setSerialLength(Integer serialLength) {
        this.serialLength = serialLength;
    }

    public String getDateMode() {
        return dateMode;
    }

    public void setDateMode(String dateMode) {
        this.dateMode = dateMode == null ? null : dateMode.trim();
    }

    public String getOddFormat() {
        return oddFormat;
    }

    public void setOddFormat(String oddFormat) {
        this.oddFormat = oddFormat == null ? null : oddFormat.trim();
    }

    public Integer getOddNumber() {
        return oddNumber;
    }

    public void setOddNumber(Integer oddNumber) {
        this.oddNumber = oddNumber;
    }

    public String getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(String currentNumber) {
        this.currentNumber = currentNumber == null ? null : currentNumber.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getModifierId() {
        return modifierId;
    }

    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }

    public Date getModifiTime() {
        return modifiTime;
    }

    public void setModifiTime(Date modifiTime) {
        this.modifiTime = modifiTime;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }
}