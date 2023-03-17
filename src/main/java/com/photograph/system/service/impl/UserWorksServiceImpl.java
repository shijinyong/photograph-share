package com.photograph.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.photograph.system.model.UserWorks;
import com.photograph.system.service.UserWorksService;
import com.photograph.system.mapper.UserWorksMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author user
* @description 针对表【user_works】的数据库操作Service实现
* @createDate 2023-02-28 13:52:44
*/
@Service
public class UserWorksServiceImpl extends ServiceImpl<UserWorksMapper, UserWorks>
    implements UserWorksService{

    @Override
    public boolean insertUserWorkId(Long userId, List<Long> worksId) {


        for(int i = 0;i<worksId.size();i++){
            UserWorks userWorks = new UserWorks();
            userWorks.setUserId(userId);
            userWorks.setWorksId(worksId.get(i));
            baseMapper.insert(userWorks);
        }
        return true;
    }
}




