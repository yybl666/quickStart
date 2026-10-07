package com.yblpj.quickstart.controller;

import com.yblpj.quickstart.Service.ArticleService;
import com.yblpj.quickstart.pojo.Article;
import com.yblpj.quickstart.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody Article article) {
        articleService.add(article);
        return Result.success();
    }
}
