package com.photograph.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.photograph.system.model.Works;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author user
* @description 针对表【works】的数据库操作Mapper
* @createDate 2023-02-27 16:42:06
* @Entity com.photograph.system.model.Works
*/
public interface WorksMapper extends BaseMapper<Works> {

    IPage<Works> selectWorksPage(Page<Works> pageParam, String userWorks, Long userId);

}




