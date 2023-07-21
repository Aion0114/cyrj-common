package com.cyrj.common.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class BillMultiUnit {


    @ApiModelProperty(value = "对应单位的价格")
    private BigDecimal specUnitPrice;

    @ApiModelProperty(value = "对应单位的数量")
    private BigDecimal specUnitQuantity;

    @ApiModelProperty(value = "转换率")
    private BigDecimal conversionRate;

    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    @ApiModelProperty(value = "件数")
    private BigDecimal itemQuantity;

    @ApiModelProperty(value = "散数")
    private BigDecimal scatteredQuantity;

    @ApiModelProperty(value = "件进价 件退价")
    private BigDecimal getIP;

    @ApiModelProperty(value = "散进价 散退价")
    private BigDecimal getSP;

    @ApiModelProperty(value = "是否基本单位 1 是")
    private Integer basicUnit;

    @ApiModelProperty(value = "小数位 默认4位")
    private Integer digits;

    public Integer getDigits() {
        return digits;
    }

    public void setDigits(Integer digits) {
        this.digits = digits;
    }

    public Integer getBasicUnit() {
        return basicUnit;
    }

    public void setBasicUnit(Integer basicUnit) {
        this.basicUnit = basicUnit;
    }

    public BigDecimal getSpecUnitPrice() {
        return specUnitPrice;
    }

    public void setSpecUnitPrice(BigDecimal specUnitPrice) {
        this.specUnitPrice = specUnitPrice;
    }

    public BigDecimal getSpecUnitQuantity() {
        return specUnitQuantity;
    }

    public void setSpecUnitQuantity(BigDecimal specUnitQuantity) {
        this.specUnitQuantity = specUnitQuantity;
    }

    public BigDecimal getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(BigDecimal conversionRate) {
        this.conversionRate = conversionRate;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(BigDecimal itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public BigDecimal getScatteredQuantity() {
        return scatteredQuantity;
    }

    public void setScatteredQuantity(BigDecimal scatteredQuantity) {
        this.scatteredQuantity = scatteredQuantity;
    }

    public BigDecimal getGetIP() {
        return getIP;
    }

    public void setGetIP(BigDecimal getIP) {
        this.getIP = getIP;
    }

    public BigDecimal getGetSP() {
        return getSP;
    }

    public void setGetSP(BigDecimal getSP) {
        this.getSP = getSP;
    }
}
