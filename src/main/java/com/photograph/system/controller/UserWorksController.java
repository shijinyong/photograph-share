package com.photograph.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.photograph.system.model.User;
import com.photograph.system.model.Works;
import com.photograph.system.result.Result;
import com.photograph.system.service.UserWorksService;
import com.photograph.system.service.WorksService;
import com.photograph.system.vo.EditUserWorks;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * @Author: shijinyong
 * @Date: 2023/2/27 14:38
 * @Description:
 */
@Controller
@RequestMapping("/user/manage")
public class UserWorksController {

    @Autowired
    private UserWorksService userWorksService;

    @Autowired
    private WorksService worksService;

    @RequestMapping("/pages/toAddWorks")
    public String toAddWorks(){
        return "user/manage/pages/works";
    }

    @RequestMapping("/pages/toManageWorks")
    public String toManageWorks(){
        return "user/manage/pages/manageWorks";
    }


    @PostMapping("/pages/selectWorksByConditionalForPage")
    @ResponseBody
    public Result selectWorksByConditionalForPage(HttpServletRequest request,
                                                  Long pageNo,
                                                  Long pageSize,
                                                  String userWorks){
        //获取当前session中的用户名
        HttpSession session = request.getSession();
        User loginAccount = (User)session.getAttribute("loginAccount");
        Long userId = loginAccount.getId();
        //  创建page对象
        Page<Works> pageParam = new Page<>(pageNo,pageSize);
        //调用service方法
        IPage<Works> pageModel = worksService.selectWorksPage(pageParam,userWorks,userId);
        //返回结果
        return Result.ok(pageModel);
    }

    @PostMapping("/pages/selectUserWorksById")
    @ResponseBody
    public Result selectUserWorksById(String id){
        //通过id查询摄影作品的信息
        Works works = worksService.getById(id);
        //返回前端摄影作品信息
        return Result.ok(works);
    }

    @PostMapping("/pages/saveEditUserWorks")
    @ResponseBody
    public Result saveEditUserWorks(EditUserWorks editUserWorks){
        //新建works对象，将前端传输的值一一装载到其中
        Works works = new Works();
        works.setId(Long.parseLong(editUserWorks.getId()));
        works.setTitle(editUserWorks.getEditUserWorksTitle());
        works.setDescription(editUserWorks.getEditUserWorksDescription());
        works.setSrc(editUserWorks.getImgSrc());
        //调用service层，返回结果
        boolean result = worksService.updateById(works);

        if(result){
            return Result.ok();
        }
        return Result.fail();
    }

    @PostMapping("/pages/deleteUserWorksByIds")
    @ResponseBody
    public Result deleteUserWorksByIds(String[] id){
        //调用service层方法，返回后台是否删除成功数据
        boolean result = worksService.removeBatchByIds(Arrays.asList(id));
        if(result){
            return Result.ok();
        }
        return Result.fail();
    }

}
