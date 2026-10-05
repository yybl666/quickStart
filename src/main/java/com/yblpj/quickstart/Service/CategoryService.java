package com.yblpj.quickstart.Service;

import com.yblpj.quickstart.pojo.Category;
import com.yblpj.quickstart.pojo.Result;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public interface CategoryService {
    boolean findCategoryByName(@NotEmpty String categoryName);

    void add(Category category);

    List<Category> list();

    Category findById(Integer id);

    void update(Category category);
}
