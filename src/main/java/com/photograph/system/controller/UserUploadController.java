package com.photograph.system.controller;

import com.photograph.system.model.User;
import com.photograph.system.result.Result;
import com.photograph.system.service.ArticleService;
import com.photograph.system.service.UserArticleService;
import com.photograph.system.service.UserWorksService;
import com.photograph.system.service.WorksService;
import com.photograph.system.service.impl.WorksServiceImpl;
import com.photograph.system.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: shijinyong
 * @Date: 2023/2/27 12:57
 * @Description:
 */
@Controller
@RequestMapping("user")
public class UserUploadController {

    @Autowired
    private WorksService worksService;

    @Autowired
    private UserWorksService userWorksService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserArticleService userArticleService;

    @RequestMapping("/upload")
    @ResponseBody
    public Result uploadImage(@RequestParam(value = "file") MultipartFile multipartFile, HttpServletResponse response){
        //调用工具类
        String imagePath = UploadUtils.upload(multipartFile);

        if(imagePath!=null){
            HashMap<String,String> map = new HashMap<>();
            map.put("src",imagePath);
            return Result.ok(map);
        }else{
            return Result.fail();

        }
    }

    @RequestMapping("/manage/pages/uploadUserWorks")
    @ResponseBody
    public Result uploadUserWorks(@RequestParam(value = "title") String title,
                                  @RequestParam(value = "description") String description,
                                  @RequestParam(value = "imgSrc") List<String> imgSrc,
                                  HttpSession session){
        boolean result = worksService.uploadUserWorks(title, description, imgSrc);
        User loginAccount = (User)session.getAttribute("loginAccount");
        Long userId = loginAccount.getId();
        List<Long> worksId = worksService.getWorksId(title, description, imgSrc);
        userWorksService.insertUserWorkId(userId,worksId);
        if(result){
            return Result.ok();
        }
        return Result.fail();
    }

    @PostMapping("/manage/pages/uploadUserArticle")
    public Result uploadUserArticle(@RequestParam(value = "title") String title,
                                    @RequestParam(value = "description") String description,
                                    @RequestParam(value = "content") String content,
                                    @RequestParam(value = "imgSrc") List<String> imgSrc,
                                    HttpSession session){
        boolean result = articleService.uploadUserArticle(title, description,content, imgSrc);
        User loginAccount = (User)session.getAttribute("loginAccount");
        Long userId = loginAccount.getId();
        List<Long> worksId = worksService.getWorksId(title, description, imgSrc);
        userWorksService.insertUserWorkId(userId,worksId);
        if(result){
            return Result.ok();
        }
        return Result.fail();

    }

}
