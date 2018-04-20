package com.qjk.controller;

import com.qjk.pojo.UserPo;
import com.qjk.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "getUser.do",method = RequestMethod.GET)
    @ResponseBody
    public List<UserPo> getUser(){
        List<UserPo> userList=iUserService.getUser();
        return userList;
    }
}
