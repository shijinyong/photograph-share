package com.photograph.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.photograph.system.model.Article;
import com.photograph.system.model.Works;
import com.photograph.system.service.ArticleService;
import com.photograph.system.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author user
* @description 针对表【article】的数据库操作Service实现
* @createDate 2023-03-09 14:45:50
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{
    @Override
    public boolean uploadUserArticle(String title, String description, String content, List<String> imgSrc) {
        //创建Article对象
        Article article = new Article();
        //设置内容
        article.setTitle(title);
        article.setDescription(description);
        article.setContent(content);
        //将List集合取出拼成字符串
        String imgSrcs = imgSrc.stream().map(String::valueOf).collect(Collectors.joining(","));
        article.setSrc(imgSrcs);
        //调用mapper方法
        int result = baseMapper.insert(article);
        if(result>0) {
            return true;
        }
        return false;
    }
}




