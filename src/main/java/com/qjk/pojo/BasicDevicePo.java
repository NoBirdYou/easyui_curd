package com.qjk.pojo;

import javax.persistence.*;

@Entity
@Table(name = "basic_device")
public class BasicDevicePo {

    @Id
    @GeneratedValue
    private Integer id;//主键

    @Column(name="serial_no")
    private String serialNo;//设备序列号

    private String mainboard;//主板

    private String cpu;//处理器

    private String graphics;//显卡

    private String power;//电源

    public BasicDevicePo() {
    }

    public BasicDevicePo(String serialNo, String mainboard, String cpu, String graphics, String power) {
        this.serialNo = serialNo;
        this.mainboard = mainboard;
        this.cpu = cpu;
        this.graphics = graphics;
        this.power = power;
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

    @Override
    public String toString() {
        return "BasicDevicePo{" +
                "id=" + id +
                ", serialNo=" + serialNo +
                ", mainboard='" + mainboard + '\'' +
                ", cpu='" + cpu + '\'' +
                ", graphics='" + graphics + '\'' +
                ", power='" + power + '\'' +
                '}';
    }
}
