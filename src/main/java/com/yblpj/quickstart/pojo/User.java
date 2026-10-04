package com.yblpj.quickstart.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户表实体类
 */
@Data // Lombok注解：自动生成 getter, setter, toString, equals, hashCode
public class User {

    /**
     * ID (主键)
     */
    @NotNull
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 昵称
     */
    @NotEmpty
    private String nickname;

    /**
     * 邮箱
     */
    @NotEmpty
    @Email
    private String email;

    /**
     * 头像
     */
    private String userPic;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}