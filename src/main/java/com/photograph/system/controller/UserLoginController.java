package com.photograph.system.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.photograph.system.model.User;
import com.photograph.system.vo.UserLogin;
import com.photograph.system.result.Result;
import com.photograph.system.result.ResultEnum;
import com.photograph.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author: shijinyong
 * @Date: 2023/2/20 9:49
 * @Description:
 */
@Controller
public class UserLoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private DefaultKaptcha defaultKaptcha;


    @GetMapping("/toUserLogin")
    public String toUserLogin(){
        return "user/login";
    }

    @GetMapping("/toUserRegister")
    public String toUserRegister(){
        return "user/register";
    }

    @GetMapping("/getVerifyCode")
    public void getKaptcha(HttpServletResponse response, HttpServletRequest request){

        HttpSession session = request.getSession();
        session.removeAttribute("verifyCode");
        //生成文本
        String text = defaultKaptcha.createText();
        //生成验证码
        BufferedImage image = defaultKaptcha.createImage(text);
        //将验证码存入session中
        session.setAttribute("verifyCode",text.toLowerCase());
        //将图片传输至前台
        response.setHeader("Cache-Control","no-store,no-cache");
        response.setContentType("image/png");
        try {
            ServletOutputStream os = response.getOutputStream();
            ImageIO.write(image,"png",os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/userLogin")
    @ResponseBody
    public Result userLogin(UserLogin userLogin,HttpServletRequest request,HttpServletResponse response){
        //获取session
        HttpSession session = request.getSession();
        //取出session中验证码的值
        String verifyCode = (String) session.getAttribute("verifyCode");
        //忽略大小写
        if(userLogin.getVerify().equalsIgnoreCase(verifyCode)){
            //验证码一致，调用service方法
            User user = userService.selectAccount(userLogin.getLoginName(),userLogin.getLoginPassword());
            //存在用户，返回用户信息
            if(user != null){
                if("true".equals(userLogin.getIsRemPassword())){
                    //将用户信息保存在作用域中
                    session.setAttribute("loginAccount",user);
                    //将用户和密码保存在cookie中
                    Cookie c1 = new Cookie("loginName",user.getName());
                    //设置cookie最大有效时间7天
                    c1.setMaxAge(7*24*60*60);
                    Cookie c2 = new Cookie("loginPassword",user.getPassword());
                    c2.setMaxAge(7*24*60*60);
                    //往外写出cookie
                    response.addCookie(c1);
                    response.addCookie(c2);
                    return Result.ok();
                }else{
                    //将用户信息保存在作用域中
                    session.setAttribute("loginAccount",user);
                    //把没有过期的cookie删除
                    Cookie c1 = new Cookie("loginName","1");
                    c1.setMaxAge(0);
                    Cookie c2 = new Cookie("loginPassword","1");
                    c2.setMaxAge(0);
                    response.addCookie(c1);
                    response.addCookie(c2);
                    return Result.ok();
                }
            }else{
                //不存在用户，返回错误信息
                return Result.fail(ResultEnum.ACCOUNT_ERROR);
            }
        }else{
            //验证码不一致，返回错误信息
            return Result.fail(ResultEnum.VERIFRCODE_ERROR);
        }

    }
}
