package com.photograph.system.service;

import com.photograph.system.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author user
* @description 针对表【user】的数据库操作Service
* @createDate 2023-02-21 16:07:00
*/
public interface UserService extends IService<User> {

    User selectAccount(String loginName, String loginPassword);

    boolean getVerifyUserName(String registerName);

    boolean getVerifyUserName(String registerName,String verifyName);
}
