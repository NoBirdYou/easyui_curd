package com.qjk.service;

import com.qjk.common.ServerResponse;
import com.qjk.vo.BasicDeviceVo;
import com.qjk.vo.ComputerAddDataVo;
import com.qjk.vo.ComputerGetDataVo;
import com.qjk.vo.ComputerUpdateDataVo;

import java.util.List;

public interface IComputerService {

    ServerResponse<List<BasicDeviceVo>> searchData();

    ServerResponse<String> deleteData(Integer id);

    ServerResponse<String> addData(ComputerAddDataVo computerAddDataVo);

    ServerResponse<ComputerGetDataVo> getData(Integer id);

    ServerResponse< String> updateData(ComputerUpdateDataVo updateDataVo);

}
