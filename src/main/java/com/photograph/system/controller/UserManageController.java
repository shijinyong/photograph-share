package com.photograph.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: shijinyong
 * @Date: 2023/2/25 11:26
 * @Description:
 */
@Controller
public class UserManageController {

    @GetMapping("/user/manage/index")
    public String toUser(){
        return "user/manage/index";
    }

    @GetMapping("/user/manage/manageIndex")
    public String toManager(){
        return "user/manage/manageIndex";
    }

    @GetMapping("/user/manage/userLoginOut")
    public String userLoginOut(HttpServletRequest request){
        //删除session中用户信息，退出登录
        HttpSession session = request.getSession();
        session.removeAttribute("loginAccount");
        return "index";
    }

}
