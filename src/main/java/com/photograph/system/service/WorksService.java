package com.photograph.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.photograph.system.model.Works;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author user
* @description 针对表【works】的数据库操作Service
* @createDate 2023-02-27 16:42:06
*/
public interface WorksService extends IService<Works> {

    boolean uploadUserWorks(String title, String description, List<String> imgSrc);

    List<Long> getWorksId(String title, String description, List<String> imgSrc);

    IPage<Works> selectWorksPage(Page<Works> pageParam, String userWorks, Long userId);
}
