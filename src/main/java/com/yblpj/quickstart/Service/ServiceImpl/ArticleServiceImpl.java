package com.yblpj.quickstart.Service.ServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yblpj.quickstart.Service.ArticleService;
import com.yblpj.quickstart.mapper.ArticleMapper;
import com.yblpj.quickstart.pojo.Article;
import com.yblpj.quickstart.pojo.PageBean;
import com.yblpj.quickstart.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        article.setCreateUser(id);
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //创建pageBean对象，用于存储得到的记录
        PageBean<Article> pb = new PageBean<>();
        //开启分页查询--pageHelper
        PageHelper.startPage(pageNum, pageSize);
        //查询
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> list = articleMapper.list(userId,categoryId,state);
        //存储记录
        //强转为page，获取经过pagehelper过滤的数据记录,因为helper可以直接获得page对象(page对象里有list数据，有total记录数量。)
        PageInfo<Article> page = new PageInfo<>(list);
        pb.setTotal(page.getTotal());
        pb.setItems(page.getList());
        return pb;
    }
}
