package com.photograph.system.model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName works
 */
@TableName(value ="works")
@Data
public class Works implements Serializable {
    /**
     * 作品id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 作品类别
     */
    private String description;

    /**
     * 作品描述
     */
    private String title;

    /**
     * 作品创建时间
     */
    private Date createTime;

    /**
     * 作品修改时间
     */
    private Date updateTime;

    /**
     * 作品路径
     */
    private String src;

    /**
     * 删除标记（0:可用 1:已删除）
     */

    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}