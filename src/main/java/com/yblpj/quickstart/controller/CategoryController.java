package com.yblpj.quickstart.controller;

import com.yblpj.quickstart.Service.CategoryService;
import com.yblpj.quickstart.pojo.Category;
import com.yblpj.quickstart.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public Result add(@RequestBody @Validated Category category) {
        if(categoryService.findCategoryByName(category.getCategoryName())){
            return Result.error("分类名已经存在");
        }
        categoryService.add(category);
        return Result.success();
    }
}
