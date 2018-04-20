package com.qjk.vo;

import java.math.BigDecimal;

public class ExtendDeviceVo {

    private Integer id;
    private String serialNo;
    private String deviceCategory;
    private String deviceName;
    private BigDecimal devicePrice;
    private Integer deviceNum;

    public ExtendDeviceVo() {
    }

    public ExtendDeviceVo(Integer id, String serialNo, String deviceCategory, String deviceName, BigDecimal devicePrice, Integer deviceNum) {
        this.id = id;
        this.serialNo = serialNo;
        this.deviceCategory = deviceCategory;
        this.deviceName = deviceName;
        this.devicePrice = devicePrice;
        this.deviceNum = deviceNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getDeviceCategory() {
        return deviceCategory;
    }

    public void setDeviceCategory(String deviceCategory) {
        this.deviceCategory = deviceCategory;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public BigDecimal getDevicePrice() {
        return devicePrice;
    }

    public void setDevicePrice(BigDecimal devicePrice) {
        this.devicePrice = devicePrice;
    }

    public Integer getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(Integer deviceNum) {
        this.deviceNum = deviceNum;
    }

    @Override
    public String toString() {
        return "ExtendDeviceVo{" +
                "id=" + id +
                ", serialNo='" + serialNo + '\'' +
                ", deviceCategory='" + deviceCategory + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", devicePrice='" + devicePrice + '\'' +
                ", deviceNum='" + deviceNum + '\'' +
                '}';
    }


}
