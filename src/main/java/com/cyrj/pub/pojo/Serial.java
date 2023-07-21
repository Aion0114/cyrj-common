package com.cyrj.pub.pojo;

import java.util.Date;

public class Serial {

    private Integer id;

    private String type;

    private String serialPrefix;

    private Integer serialNo;

    private Integer status;

    private Date createTime;

    private Integer creator;

    private Date modifiTime;

    private Integer modifier;

    private Integer delflag;

    private Date updateNo;

    private Integer tenantId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSerialPrefix() {
        return serialPrefix;
    }

    public void setSerialPrefix(String serialPrefix) {
        this.serialPrefix = serialPrefix;
    }

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getModifiTime() {
        return modifiTime;
    }

    public void setModifiTime(Date modifiTime) {
        this.modifiTime = modifiTime;
    }

    public Integer getModifier() {
        return modifier;
    }

    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    public Date getUpdateNo() {
        return updateNo;
    }

    public void setUpdateNo(Date updateNo) {
        this.updateNo = updateNo;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
}