package com.qjk.service.impl;

import com.qjk.common.ServerResponse;
import com.qjk.dao.BasicDeviceRepository;
import com.qjk.dao.ExtendDeviceRepository;
import com.qjk.pojo.BasicDevicePo;
import com.qjk.pojo.ExtendDevicePo;
import com.qjk.service.IComputerService;
import com.qjk.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service("iComputerService")
public class ComputerServiceImpl implements IComputerService{

    @Autowired
    private BasicDeviceRepository basicDeviceRepository;

    @Autowired
    private ExtendDeviceRepository extendDeviceRepository;

    @Override
    public ServerResponse<List<BasicDeviceVo>> searchData() {
        Pageable pageable=new PageRequest(1,10);
        List<BasicDevicePo> basicDevicePoList= basicDeviceRepository.findAll();
        List<BasicDeviceVo> basicDeviceVoList=new ArrayList<>();
        for (BasicDevicePo basicDevicePo:basicDevicePoList) {
            basicDeviceVoList.add(BasicDeviceVo.toVo(basicDevicePo));
        }
        return ServerResponse.createBySuccess(basicDeviceVoList);
    }

    @Override
    public ServerResponse<String> deleteData(Integer id) {
        try {
            basicDeviceRepository.delete(id);
            return ServerResponse.createBySuccessMessage("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("删除失败");
        }

    }


    @Override
    public ServerResponse<String> addData(ComputerAddDataVo computerAddDataVo) {
        BasicDevicePo basicDevicePo=new BasicDevicePo();
        basicDevicePo.setCpu(computerAddDataVo.getCpu());
        basicDevicePo.setGraphics(computerAddDataVo.getGraphics());
        basicDevicePo.setMainboard(computerAddDataVo.getMainboard());
        basicDevicePo.setPower(computerAddDataVo.getPower());
        basicDevicePo.setSerialNo(computerAddDataVo.getSerialNo());
        basicDeviceRepository.save(basicDevicePo);
        List<ExtendDeviceVo> extendDeviceVoList=computerAddDataVo.getExtendDevices();
        List<ExtendDevicePo> extendDevicePoList=new ArrayList<>();
        for (ExtendDeviceVo extendDeviceVo:extendDeviceVoList) {
            ExtendDevicePo extendDevicePo=new ExtendDevicePo();
            extendDevicePo.setSerialNo(basicDevicePo.getSerialNo());
            extendDevicePo.setDeviceCategory(extendDeviceVo.getDeviceCategory());
            extendDevicePo.setDeviceName(extendDeviceVo.getDeviceName());
            extendDevicePo.setDeviceNum(extendDeviceVo.getDeviceNum());
            extendDevicePo.setDevicePrice(extendDeviceVo.getDevicePrice());
            extendDevicePoList.add(extendDevicePo);
        }
        if(extendDevicePoList.size()>0){
            extendDeviceRepository.save(extendDevicePoList);
        }
        return ServerResponse.createBySuccessMessage("新增数据成功");
    }

    @Override
    public ServerResponse<ComputerGetDataVo> getData(final Integer id) {
        ComputerGetDataVo computerGetDataVo=new ComputerGetDataVo();
        BasicDevicePo basicDevicePo=basicDeviceRepository.findOne(id);
        if(basicDevicePo==null){
            return ServerResponse.createByErrorMessage("获取不到该数据");
        }
        computerGetDataVo.setPower(basicDevicePo.getPower());
        computerGetDataVo.setMainboard(basicDevicePo.getMainboard());
        computerGetDataVo.setGraphics(basicDevicePo.getGraphics());
        computerGetDataVo.setCpu(basicDevicePo.getCpu());
        computerGetDataVo.setSerialNo(basicDevicePo.getSerialNo());

        final String serialNo=computerGetDataVo.getSerialNo();

        List<ExtendDevicePo> extendDevicePoList=extendDeviceRepository.findAll(new Specification<ExtendDevicePo>() {
            @Override
            public Predicate toPredicate(Root<ExtendDevicePo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList=new ArrayList<>();
                if(serialNo!=null || StringUtils.isNotBlank(serialNo)){
                    predicateList.add(criteriaBuilder.equal(root.get("serialNo").as(String.class),serialNo));
                }
                Predicate[] predicates=new Predicate[predicateList.size()];
                return criteriaBuilder.and(predicateList.toArray(predicates));
            }
        });
        List<ExtendDeviceVo> extendDeviceVoList=new ArrayList<>();

        for(ExtendDevicePo extendDevicePo:extendDevicePoList){
            ExtendDeviceVo extendDeviceVo=new ExtendDeviceVo();
            extendDeviceVo.setDeviceCategory(extendDevicePo.getDeviceCategory());
            extendDeviceVo.setDeviceName(extendDevicePo.getDeviceName());
            extendDeviceVo.setDeviceNum(extendDevicePo.getDeviceNum());
            extendDeviceVo.setDevicePrice(extendDevicePo.getDevicePrice());
            extendDeviceVo.setId(extendDevicePo.getId());
            extendDeviceVo.setSerialNo(extendDevicePo.getSerialNo());
            extendDeviceVoList.add(extendDeviceVo);
        }

        computerGetDataVo.setExtendDevices(extendDeviceVoList);

        return ServerResponse.createBySuccess(computerGetDataVo);
    }



//    @Transactional
    @Override
    public ServerResponse<String> updateData(ComputerUpdateDataVo updateDataVo) {

        BasicDevicePo basicDevicePo=new BasicDevicePo();
        basicDevicePo.setId(updateDataVo.getId());
        basicDevicePo.setPower(updateDataVo.getPower());
        basicDevicePo.setMainboard(updateDataVo.getCpu());
        basicDevicePo.setGraphics(updateDataVo.getGraphics());
        basicDevicePo.setCpu(updateDataVo.getCpu());
        basicDevicePo.setSerialNo(updateDataVo.getSerialNo());

        basicDeviceRepository.save(basicDevicePo);

        List<ExtendDevicePo> deleteExtendDevicePoList=extendDeviceRepository.findBySerialNo(basicDevicePo.getSerialNo());


        extendDeviceRepository.delete(deleteExtendDevicePoList);

        List<ExtendDeviceVo> extendDeviceVoList=updateDataVo.getExtendDevices();
        List<ExtendDevicePo> extendDevicePoList=new ArrayList<>();
        for (ExtendDeviceVo extendDeviceVo:extendDeviceVoList) {
            ExtendDevicePo extendDevicePo=new ExtendDevicePo();
            extendDevicePo.setSerialNo(basicDevicePo.getSerialNo());
            extendDevicePo.setDeviceCategory(extendDeviceVo.getDeviceCategory());
            extendDevicePo.setDeviceName(extendDeviceVo.getDeviceName());
            extendDevicePo.setDeviceNum(extendDeviceVo.getDeviceNum());
            extendDevicePo.setDevicePrice(extendDeviceVo.getDevicePrice());
            extendDevicePoList.add(extendDevicePo);
        }
        extendDeviceRepository.save(extendDevicePoList);

        return ServerResponse.createBySuccessMessage("修改成功");
    }
}
