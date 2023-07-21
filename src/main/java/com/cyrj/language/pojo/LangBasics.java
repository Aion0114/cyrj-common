package com.cyrj.language.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class LangBasics {
    @ApiModelProperty(value="")
    private Integer id;

    @ApiModelProperty(value="语言标识 如：zh_lang、es_lang、vi_lang")
    private String langKey;

    @ApiModelProperty(value="语言名称 如：中文、英文、越南文")
    private String langName;

    @ApiModelProperty(value="建立时间")
    private Date createTime;

    @ApiModelProperty(value="建立操作员id")
    private Integer creatorId;

    @ApiModelProperty(value="修改时间",hidden=true)
    private Date modifiTime;

    @ApiModelProperty(value="修改操作员id",hidden=true)
    private Integer modifierId;

    @ApiModelProperty(value="0-删除  1-正常",hidden=true)
    private Integer delflag;

    @ApiModelProperty(value="租户id")
    private Integer tenantId;

    @ApiModelProperty(value="是否默认语言(0:否，1:是)")
    private Integer isDefault;

    private String isDefaultName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey == null ? null : langKey.trim();
    }

    public String getLangName() {
        return langName;
    }

    public void setLangName(String langName) {
        this.langName = langName == null ? null : langName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Date getModifiTime() {
        return modifiTime;
    }

    public void setModifiTime(Date modifiTime) {
        this.modifiTime = modifiTime;
    }

    public Integer getModifierId() {
        return modifierId;
    }

    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public String getIsDefaultName() {
        return isDefaultName;
    }

    public void setIsDefaultName(String isDefaultName) {
        this.isDefaultName = isDefaultName;
    }
}