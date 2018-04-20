package com.qjk.dao;

import com.qjk.pojo.BasicDevicePo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasicDeviceRepository extends JpaRepository<BasicDevicePo,Integer> {
}
