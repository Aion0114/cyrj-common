package com.cyrj.common.enumeration;

/**
 * 上传文件类型
 */
public enum ImageAnnexType {

    SCRIPT_TYPE("脚本类型",0),
    APPLICATION_TYPE("应用类型",1),
    ADVERTISING_TYPE("广告类型",2),
    TEMPLATE_TYPE("模板打印类型",3),
    FEEDBACK_IMAGE_TYPE("用户反馈类型-图片",4),
    FEEDBACK_ANNEX_TYPE("用户反馈类型-附件",5);

    private String name;
    private Integer code;

    ImageAnnexType(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public Integer getCode(){
        return code;
    }

    public String getName(){
        return name;
    }
}
