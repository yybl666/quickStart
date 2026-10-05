package com.yblpj.quickstart.Service.ServiceImpl;

import com.yblpj.quickstart.Service.CategoryService;
import com.yblpj.quickstart.mapper.CategoryMapper;
import com.yblpj.quickstart.pojo.Category;
import com.yblpj.quickstart.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public boolean findCategoryByName(String categoryName) {
        if(categoryMapper.findCategoryByName(categoryName) == null){
            return false;
        }
        return true;
    }

    @Override
    public void add(Category category) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        category.setCreateUser(userId);
        categoryMapper.add(category);
    }
}
