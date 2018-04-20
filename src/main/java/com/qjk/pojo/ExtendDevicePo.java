package com.qjk.pojo;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "extend_device")
public class ExtendDevicePo {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "serial_no")
    private String serialNo;

    @Column(name = "device_category")
    private String deviceCategory;

    @Column(name = "device_name")
    private String deviceName;

    @Column(name = "device_price")
    private BigDecimal devicePrice;

    @Column(name = "device_num")
    private Integer deviceNum;

    public ExtendDevicePo() {
    }

    public ExtendDevicePo(Integer id, String serialNo, String deviceCategory, String deviceName, BigDecimal devicePrice, Integer deviceNum) {
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
