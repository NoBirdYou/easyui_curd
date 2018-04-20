package com.qjk.dao;

import com.qjk.pojo.ExtendDevicePo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ExtendDeviceRepository extends JpaRepository<ExtendDevicePo,Integer>,JpaSpecificationExecutor<ExtendDevicePo> {

    List<ExtendDevicePo> findBySerialNo(String serialNo);
}
