package com.yblpj.quickstart.Service.ServiceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yblpj.quickstart.Service.CategoryService;
import com.yblpj.quickstart.mapper.CategoryMapper;
import com.yblpj.quickstart.pojo.Category;
import com.yblpj.quickstart.pojo.Result;
import com.yblpj.quickstart.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ObjectMapper mapper;//序列化

    private static final String CACHE_KEY_PREFIX = "bigEvent:category:list:";
    private static final Duration CACHE_TTL = Duration.ofMinutes(30);

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
        //先改库再删缓存
        stringRedisTemplate.delete(CACHE_KEY_PREFIX + userId);
    }

    @Override
    public List<Category> list() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        String key = CACHE_KEY_PREFIX + id;
        //先查缓存
        String json = stringRedisTemplate.opsForValue().get(key);
        //判断是否为空，如果redis不为空就直接返回缓存内容
        if(json != null){
            try {
                return mapper.readValue(json,new TypeReference<List<Category>>(){});
            } catch (JsonProcessingException e) {
                log.error("文章分类缓存反序列出错，key={}",key,e);
                //反序列取出redis时出现异常，说明这个json是坏数据，直接删除
                stringRedisTemplate.delete(key);
            }
        }
        //redis为空，返回db的数据
        List<Category> list = categoryMapper.list(id);
        //并且存储到redis中(先将值序列化再存入)
        try {
            //抖动(300秒抖动)
            Duration ttl = CACHE_TTL.plusSeconds(ThreadLocalRandom.current().nextInt(300));
            stringRedisTemplate.opsForValue().set(key,mapper.writeValueAsString(list),ttl);
        } catch (JsonProcessingException e) {
            log.error("文章分类序列化出现异常,key={}",key,e);
        }

        return list;
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.findById(id);
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        stringRedisTemplate.delete(CACHE_KEY_PREFIX + id);
    }
}
