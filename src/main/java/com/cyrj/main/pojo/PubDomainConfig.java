package com.cyrj.main.pojo;


import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class PubDomainConfig {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "创建人id", hidden = true)
    private Integer createrId;

    @ApiModelProperty(value = "创建人姓名", hidden = true)
    private String creater;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

    @ApiModelProperty(value = "修改人id", hidden = true)
    private Integer modifyId;

    @ApiModelProperty(value = "修改人姓名", hidden = true)
    private String modify;

    @ApiModelProperty(value = "修改人时间", hidden = true)
    private Date modifyTime;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "删除标识", hidden = true)
    private Integer delflag;

    @ApiModelProperty(value = "域名")
    private String domainName;

    @ApiModelProperty(value = "是否微信地址")
    private Integer isWx;

    @ApiModelProperty(value = "是否微信地址")
    private String isWx2;

    @ApiModelProperty(value = "客户名称")
    private String companyName;

    @ApiModelProperty(value = "数据库用户名")
    private String dbName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getModifyId() {
        return modifyId;
    }

    public void setModifyId(Integer modifyId) {
        this.modifyId = modifyId;
    }

    public String getModify() {
        return modify;
    }

    public void setModify(String modify) {
        this.modify = modify == null ? null : modify.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName == null ? null : domainName.trim();
    }

    public Integer getIsWx() {
        return isWx;
    }

    public void setIsWx(Integer isWx) {
        this.isWx = isWx;
    }

    public String getIsWx2() {
        return isWx2;
    }

    public void setIsWx2(String isWx2) {
        this.isWx2 = isWx2;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}