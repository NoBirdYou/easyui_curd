package com.qjk.vo;

import java.util.List;

public class ComputerUpdateDataVo {

    private Integer id;//ID

    private String serialNo;//设备序列号

    private String mainboard;//主板

    private String cpu;//处理器

    private String graphics;//显卡

    private String power;//电源

    private List<ExtendDeviceVo> extendDevices;

    public ComputerUpdateDataVo() {
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

    public List<ExtendDeviceVo> getExtendDevices() {
        return extendDevices;
    }

    public void setExtendDevices(List<ExtendDeviceVo> extendDevices) {
        this.extendDevices = extendDevices;
    }

    @Override
    public String toString() {
        return "ComputerAddDataVo{" +
                "serial_no=" + serialNo +
                ", mainboard='" + mainboard + '\'' +
                ", cpu='" + cpu + '\'' +
                ", graphics='" + graphics + '\'' +
                ", power='" + power + '\'' +
                ", extendDevices=" + extendDevices +
                '}';
    }
}
