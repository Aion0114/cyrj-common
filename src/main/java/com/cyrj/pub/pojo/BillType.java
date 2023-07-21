package com.cyrj.pub.pojo;

import java.util.Date;

public class BillType {
    private Integer id;

    private String code;

    private String prefix;

    private String name;

    private String model;

    private Integer inOrOut;

    private Integer type;

    private String mainTable;

    private String detailTable;

    private Integer tenantId;

    private String remark;

    private Integer status;

    private Date createTime;

    private Integer creator;

    private Date modifiTime;

    private Integer modifier;

    private Integer delflag;

    private Date updateNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix == null ? null : prefix.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public Integer getInOrOut() {
        return inOrOut;
    }

    public void setInOrOut(Integer inOrOut) {
        this.inOrOut = inOrOut;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMainTable() {
        return mainTable;
    }

    public void setMainTable(String mainTable) {
        this.mainTable = mainTable == null ? null : mainTable.trim();
    }

    public String getDetailTable() {
        return detailTable;
    }

    public void setDetailTable(String detailTable) {
        this.detailTable = detailTable == null ? null : detailTable.trim();
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
}