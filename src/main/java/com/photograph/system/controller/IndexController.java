package com.photograph.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: shijinyong
 * @Date: 2023/2/22 17:25
 * @Description:
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/toIndex")
    public String toIndex(){
        return "index";
    }
}
