package com.yblpj.quickstart.mapper;

import com.yblpj.quickstart.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    //添加用户
    @Insert("insert into user(username,password,create_time,update_time) " +
            "values (#{username},#{password},now(),now())")
    void add(String username, String password);

    //根据用户名找出用户
    @Select("select * from user where username=#{username}")
    User findUserByName(String username);
}
