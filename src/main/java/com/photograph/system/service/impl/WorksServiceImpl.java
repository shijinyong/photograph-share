package com.photograph.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.photograph.system.model.Works;
import com.photograph.system.service.WorksService;
import com.photograph.system.mapper.WorksMapper;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author user
* @description 针对表【works】的数据库操作Service实现
* @createDate 2023-02-27 16:42:06
*/
@Service
public class WorksServiceImpl extends ServiceImpl<WorksMapper, Works>
    implements WorksService{

    @Override
    public boolean uploadUserWorks(String title, String description, List<String> imgSrc) {
        //创建Works对象
        Works works = new Works();
        //设置内容
        works.setTitle(title);
        works.setDescription(description);
        //将List集合取出拼成字符串
        String imgSrcs = imgSrc.stream().map(String::valueOf).collect(Collectors.joining(","));
        works.setSrc(imgSrcs);
        //调用mapper方法
        int result = baseMapper.insert(works);
        if(result>0) {
            return true;
        }
        return false;
    }

    @Override
    public IPage<Works> selectWorksPage(Page<Works> pageParam, String userWorks, Long userId) {
        return baseMapper.selectWorksPage(pageParam,userWorks,userId);
    }

    @Override
    public List<Long> getWorksId(String title, String description, List<String> imgSrc) {
        //将List集合取出拼成字符串
        String imgSrcs = imgSrc.stream().map(String::valueOf).collect(Collectors.joining(","));
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("title",title);
        queryWrapper.eq("description",description);
        queryWrapper.eq("src",imgSrcs);
        List<Works> list = baseMapper.selectList(queryWrapper);
        List<Long> idList = new ArrayList<>();
        list.forEach(s->{
            idList.add(s.getId());
        });
        return idList;
    }
}




