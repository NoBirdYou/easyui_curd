package com.qjk.vo;

import com.qjk.pojo.BasicDevicePo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class BasicDeviceVo {

    private Integer id;//主键

    private String serialNo;//设备序列号

    private String mainboard;//主板

    private String cpu;//处理器

    private String graphics;//显卡

    private String power;//电源

    public BasicDeviceVo() {
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

    public String getMainboard() {
        return mainboard;
    }

    public void setMainboard(String mainboard) {
        this.mainboard = mainboard;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGraphics() {
        return graphics;
    }

    public void setGraphics(String graphics) {
        this.graphics = graphics;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public static BasicDeviceVo toVo(BasicDevicePo basicDevicePo){
        BasicDeviceVo basicDeviceVo=new BasicDeviceVo();
        basicDeviceVo.setSerialNo(basicDevicePo.getSerialNo());
        basicDeviceVo.setCpu(basicDevicePo.getCpu());
        basicDeviceVo.setGraphics(basicDevicePo.getGraphics());
        basicDeviceVo.setId(basicDevicePo.getId());
        basicDeviceVo.setMainboard(basicDevicePo.getMainboard());
        basicDeviceVo.setPower(basicDevicePo.getPower());
        return basicDeviceVo;
    }
}
