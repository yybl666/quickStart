package com.yblpj.quickstart.controller;

import com.yblpj.quickstart.Service.CategoryService;
import com.yblpj.quickstart.pojo.Category;
import com.yblpj.quickstart.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result add(@RequestBody @Validated Category category) {
        if(categoryService.findCategoryByName(category.getCategoryName())){
            return Result.error("分类名已经存在");
        }
        categoryService.add(category);
        return Result.success();
    }

    @GetMapping
    public Result<List<Category>> list() {
        return Result.success(categoryService.list());
    }

    //获取分类列表的某个记录详细信息（编辑之前展示）
    @GetMapping("/detail")
    public Result<Category> detail(Integer id) {
        return Result.success(categoryService.findById(id));
    }

}
