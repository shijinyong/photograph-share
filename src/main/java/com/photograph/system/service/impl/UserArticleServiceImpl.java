package com.photograph.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.photograph.system.model.UserArticle;
import com.photograph.system.service.UserArticleService;
import com.photograph.system.mapper.UserArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author user
* @description 针对表【user_article】的数据库操作Service实现
* @createDate 2023-03-11 10:45:05
*/
@Service
public class UserArticleServiceImpl extends ServiceImpl<UserArticleMapper, UserArticle>
    implements UserArticleService{

}




