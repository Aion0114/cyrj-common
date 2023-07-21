package com.cyrj.sys.pojo;

import io.swagger.annotations.ApiModelProperty;

public class DevelopmentService {

    @ApiModelProperty(value = "模块id")
    private Integer id;

    @ApiModelProperty(value = "模块名称")
    private String moduleName;

    @ApiModelProperty(value = "模块路径")
    private String modulePath;

    @ApiModelProperty(value = "删除标志")
    private Integer delflag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModulePath() {
        return modulePath;
    }

    public void setModulePath(String modulePath) {
        this.modulePath = modulePath;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }
}
