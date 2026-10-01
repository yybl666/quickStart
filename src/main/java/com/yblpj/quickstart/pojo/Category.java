package com.yblpj.quickstart.pojo;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 分类表实体类
 */
@Data
public class Category {

    /**
     * ID (主键)
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类别名
     */
    private String categoryAlias;

    /**
     * 创建人ID (外键关联 User.id)
     */
    private Integer createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}