package com.photograph.system.controller;

import com.photograph.system.model.User;
import com.photograph.system.result.Result;
import com.photograph.system.result.ResultEnum;
import com.photograph.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: shijinyong
 * @Date: 2023/2/25 15:21
 * @Description:
 */
@Controller
@RequestMapping("user/manage")
public class UserProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/pages/profile")
    public String profileIndex(){
        return "user/manage/pages/profile";
    }

    @GetMapping("/pages/toProfile")
    public String toProfile(){
        return "user/manage/pages/profile";
    }

    @GetMapping("/pages/toUpdatePassword")
    public String toUpdatePassword(){
        return "user/manage/pages/updatePassword";
    }

    @GetMapping("/pages/getUserAccount")
    @ResponseBody
    public Result getUserAccount(HttpServletRequest request){
        HttpSession session = request.getSession();
        User loginAccount = (User)session.getAttribute("loginAccount");
        User userAccount = userService.getById(loginAccount.getId());
        return Result.ok(userAccount);
    }

    @PostMapping("/pages/updateUserAccount")
    @ResponseBody
    public Result updateUserAccount(HttpServletRequest request, HttpServletResponse response, User user) {
        HttpSession session = request.getSession();
        User loginAccount = (User) session.getAttribute("loginAccount");
        Long userId = loginAccount.getId();
        user.setId(userId);
        //调用service,查询是否有重复的用户名
        boolean userResult = userService.getVerifyUserName(user.getName(),loginAccount.getName());
        if (!userResult) {
            boolean result = userService.updateById(user);
            if (result) {
                //通过id查询用户更新后的信息
                User userAccount = userService.getById(userId);
                //将更新后的用户信息存放session中
                session.setAttribute("loginAccount", userAccount);
                //更新用户信息后，删除之前记住用户姓名和密码的cookie
                Cookie c1 = new Cookie("loginName","1");
                c1.setMaxAge(0);
                Cookie c2 = new Cookie("loginPassword","1");
                c2.setMaxAge(0);
                response.addCookie(c1);
                response.addCookie(c2);
                return Result.ok();
            }
            return Result.fail();
        }else{
            //
            return Result.fail(ResultEnum.USERNAME_REPEAT);
        }
    }

    @PostMapping("/pages/updateUserPassword")
    @ResponseBody
    public Result updateUserPassword(HttpServletRequest request, HttpServletResponse response,User user){
        HttpSession session = request.getSession();
        User loginAccount = (User) session.getAttribute("loginAccount");
        Long userId = loginAccount.getId();
        user.setId(userId);
        boolean result = userService.updateById(user);
        if(result){
            //通过id查询用户更新后的信息
            User userAccount = userService.getById(userId);
            //将更新后的用户信息存放session中
            session.setAttribute("loginAccount", userAccount);
            //更新用户信息后，删除之前记住用户姓名和密码的cookie
            Cookie c1 = new Cookie("loginName","1");
            c1.setMaxAge(0);
            Cookie c2 = new Cookie("loginPassword","1");
            c2.setMaxAge(0);
            response.addCookie(c1);
            response.addCookie(c2);
            return Result.ok();
        }else{
            return Result.fail();
        }
    }


}
