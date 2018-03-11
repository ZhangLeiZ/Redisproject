package com.zl.controller;

import com.zl.bean.*;

import com.zl.common.EncryptUtils;
import com.zl.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 创建类名：UserController
 * 创建时间：2017/12/20 8:39
 *
 * @author 温宁宁
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login/{phone}/{upwd}")
    @ResponseBody
    public User Login(@PathVariable("phone") String phone,@PathVariable("upwd") String upwd){
     User user =  userService.getByPhonePwd(phone, EncryptUtils.md5(upwd));
        return user;
    }

    @RequestMapping("/user_home")
    public String user_home(){
        return "user/user_home";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }



}