package com.cyrj.pub.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class PubOddLog {
    @ApiModelProperty(value="")
    private Integer id;

    @ApiModelProperty(value="单号规则表ID")
    private Integer pubOddId;

    @ApiModelProperty(value="机构id")
    private Integer organizationId;

    @ApiModelProperty(value="机构名称")
    private String organizationName;

    @ApiModelProperty(value="单号")
    private String oddNumbers;

    @ApiModelProperty(value="单据类型ID")
    private Integer billId;

    @ApiModelProperty(value="单据名称")
    private String billName;

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

    private Integer numOdd;// 单据数，临时计算

    public Integer getNumOdd() {
        return numOdd;
    }

    public void setNumOdd(Integer numOdd) {
        this.numOdd = numOdd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPubOddId() {
        return pubOddId;
    }

    public void setPubOddId(Integer pubOddId) {
        this.pubOddId = pubOddId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName == null ? null : organizationName.trim();
    }

    public String getOddNumbers() {
        return oddNumbers;
    }

    public void setOddNumbers(String oddNumbers) {
        this.oddNumbers = oddNumbers == null ? null : oddNumbers.trim();
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