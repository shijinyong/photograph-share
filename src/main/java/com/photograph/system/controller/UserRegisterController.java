package com.photograph.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.photograph.system.model.User;
import com.photograph.system.result.Result;
import com.photograph.system.result.ResultEnum;
import com.photograph.system.service.UserService;
import com.photograph.system.vo.UserLogin;
import com.photograph.system.vo.UserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: shijinyong
 * @Date: 2023/2/22 14:16
 * @Description:
 */
@Controller
public class UserRegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/getVerifyUserName")
    @ResponseBody
    public Result getVerifyUserName(UserRegister userRegister){
        //调用service,查询是否有重复的用户名
        boolean result = userService.getVerifyUserName(userRegister.getRegisterName());
        if(result){
            return Result.fail(ResultEnum.USERNAME_REPEAT);
        }
        return Result.ok();
    }

    @PostMapping("/userRegister")
    @ResponseBody
    public Result userRegister(HttpServletRequest request,UserRegister userRegister){
        //判断两次输入密码是否一致
        if(userRegister.getRegisterPassword().equals(userRegister.getRegisterVerifyPassword())){
            //获取session
            HttpSession session = request.getSession();
            //取出session中验证码的值
            String verifyCode = (String) session.getAttribute("verifyCode");
            //忽略大小写
            if(userRegister.getVerify().equalsIgnoreCase(verifyCode)){
                //创建对象
                User user = new User();
                //将表单数据传入对象中
                user.setName(userRegister.getRegisterName());
                user.setPassword(userRegister.getRegisterPassword());
                boolean result = userService.save(user);
                if(result){
                    return Result.ok();
                }else{
                    return Result.fail();
                }
            }else{
                return Result.fail(ResultEnum.VERIFRCODE_ERROR);
            }
        }else{
            return Result.fail(ResultEnum.PASSWORD_DIFFERENT);
        }
    }

}
