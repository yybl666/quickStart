package com.yblpj.quickstart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class redisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void redisTest()
    {
        stringRedisTemplate.opsForValue().set("name123","Amy");
        System.out.println(stringRedisTemplate.opsForValue().get("name123"));
    }
}
