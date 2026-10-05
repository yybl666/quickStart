package com.yblpj.quickstart.Service;

import com.yblpj.quickstart.pojo.Category;
import jakarta.validation.constraints.NotEmpty;

public interface CategoryService {
    boolean findCategoryByName(@NotEmpty String categoryName);

    void add(Category category);
}
