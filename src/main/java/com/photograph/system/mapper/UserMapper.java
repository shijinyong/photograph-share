package com.photograph.system.mapper;

import com.photograph.system.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* @author user
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-02-21 16:07:00
* @Entity com.photograph.system.model.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {

    User selectAccount(@Param("loginName") String loginName,@Param("loginPassword") String loginPassword);
}




