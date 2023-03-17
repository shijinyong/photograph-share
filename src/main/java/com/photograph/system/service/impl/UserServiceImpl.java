package com.photograph.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.photograph.system.model.User;
import com.photograph.system.service.UserService;
import com.photograph.system.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author user
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-02-21 16:07:00
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public User selectAccount(String loginName, String loginPassword) {
        return baseMapper.selectAccount(loginName,loginPassword);
    }

    @Override
    public boolean getVerifyUserName(String registerName) {
        //获取条件查询
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name",registerName);
        User user = baseMapper.selectOne(queryWrapper);
        if(user!=null){
            return true;
        }
        //为空则为新用户
        return false;
    }

    @Override
    public boolean getVerifyUserName(String registerName, String verifyName) {
       if(registerName.equals(verifyName)){
           //如果修改后用户名和登录用户名相同，则也可视为名字没有用过
           return false;
       }else{
           //获取条件查询
           QueryWrapper queryWrapper = new QueryWrapper();
           queryWrapper.eq("name",registerName);
           User user = baseMapper.selectOne(queryWrapper);
           if(user!=null){
               return true;
           }
           //为空则为新用户
           return false;
       }
    }
}




