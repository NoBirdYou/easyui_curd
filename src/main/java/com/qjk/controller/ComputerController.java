package com.qjk.controller;

import com.qjk.common.ServerResponse;
import com.qjk.pojo.BasicDevicePo;
import com.qjk.service.IComputerService;
import com.qjk.vo.BasicDeviceVo;
import com.qjk.vo.ComputerAddDataVo;
import com.qjk.vo.ComputerUpdateDataVo;
import com.qjk.vo.ExtendDeviceVo;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/computer")
public class ComputerController {

    @Autowired
    public IComputerService iComputerService;

    /**
     * 电脑-查询数据
     * @return
     */
    @RequestMapping(value = "searchData.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse searchData(){
        return iComputerService.searchData();
    }

    @RequestMapping(value = "deleteData.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteData(Integer id){
        return iComputerService.deleteData(id);
    }

    @RequestMapping(value = "addData.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addData(@RequestBody ComputerAddDataVo addDataVo){

        return iComputerService.addData(addDataVo);
    }

    @RequestMapping(value = "getData.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getData(@RequestParam("id") Integer id){
        return iComputerService.getData(id);
    }

    @RequestMapping(value = "updateData.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateData(@RequestBody ComputerUpdateDataVo updateDataVo){
        return iComputerService.updateData(updateDataVo);
    }
}
