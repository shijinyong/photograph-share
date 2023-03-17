package com.photograph.system.service;

import com.photograph.system.model.UserWorks;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author user
* @description 针对表【user_works】的数据库操作Service
* @createDate 2023-02-28 13:52:44
*/
public interface UserWorksService extends IService<UserWorks> {

    boolean insertUserWorkId(Long userId, List<Long> worksId);

}
