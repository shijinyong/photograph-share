package com.photograph.system.controller;

import com.photograph.system.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: shijinyong
 * @Date: 2023/3/9 14:10
 * @Description:
 */
@Controller
@RequestMapping("/user/manage")
public class UserArticleController {

    @GetMapping("/pages/toAddArticle")
    public String toAddArticle(){
        return "user/manage/pages/article";
    }

    @GetMapping("/pages/toManageArticle")
    public String toManageArticle(){
        return "user/manage/pages/manageArticle";
    }



}
