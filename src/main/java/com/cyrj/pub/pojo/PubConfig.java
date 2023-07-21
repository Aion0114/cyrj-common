package com.cyrj.pub.pojo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class PubConfig {
    @ApiModelProperty(value="")
    private Integer id;

    @ApiModelProperty(value="0-删除  1-正常",hidden=true)
    private Integer delflag;

    @ApiModelProperty(value="创建人id",hidden=true)
    private Integer createrId;

    @ApiModelProperty(value="创建人姓名",hidden=true)
    private String creater;

    @ApiModelProperty(value="创建时间",hidden=true)
    private Date createTime;

    @ApiModelProperty(value="修改人id",hidden=true)
    private Integer modifyId;

    @ApiModelProperty(value="修改人姓名",hidden=true)
    private String modify;

    @ApiModelProperty(value="修改人时间",hidden=true)
    private Date modifyTime;

    @ApiModelProperty(value="参数名称")
    private String configName;

    @ApiModelProperty(value="参数键名")
    private String configKey;

    @ApiModelProperty(value="参数键值")
    private String configValue;

    @ApiModelProperty(value="系统内置（Y是 N否）")
    private String configType;

    @ApiModelProperty(value="备注")
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
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

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName == null ? null : configName.trim();
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey == null ? null : configKey.trim();
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue == null ? null : configValue.trim();
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType == null ? null : configType.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}