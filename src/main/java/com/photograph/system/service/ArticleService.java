package com.photograph.system.service;

import com.photograph.system.model.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author user
* @description 针对表【article】的数据库操作Service
* @createDate 2023-03-09 14:45:50
*/
public interface ArticleService extends IService<Article> {

    boolean uploadUserArticle(String title, String description, String content, List<String> imgSrc);
}
