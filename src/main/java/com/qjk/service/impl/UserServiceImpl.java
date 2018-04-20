package com.qjk.service.impl;

import com.qjk.dao.UserDao;
import com.qjk.pojo.UserPo;
import com.qjk.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<UserPo> getUser() {
        return userDao.findAll();
    }
}
