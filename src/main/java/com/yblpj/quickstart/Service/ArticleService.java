package com.yblpj.quickstart.Service;

import com.yblpj.quickstart.pojo.Article;
import com.yblpj.quickstart.pojo.PageBean;

public interface ArticleService {
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
