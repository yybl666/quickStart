package com.yblpj.quickstart.mapper;

import com.yblpj.quickstart.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("select * from category where category_name=#{categoryName}")
    Category findCategoryByName(String categoryName);

    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time)" +
            "values(#{categoryName},#{categoryAlias},#{createUser},now(),now())")
    void add(Category category);

    @Select("select * from category where create_user=#{id}")
    List<Category> list(Integer id);

    @Select("SELECT * FROM category where id=#{id}")
    Category findById(Integer id);
}
