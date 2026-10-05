package com.yblpj.quickstart.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private Integer id;

    /**
     * 分类名称
     */
    @NotEmpty
    private String categoryName;

    /**
     * 分类别名
     */
    @NotEmpty
    private String categoryAlias;

    /**
     * 创建人ID (外键关联 User.id)
     */
    private Integer createUser;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}