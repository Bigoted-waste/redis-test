package com.cola;

import com.cola.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class RedisSpringbootApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;


    @Test
    void contextLoads() {

        //redisTemplate 操作不同的数据类型，api和哦我们的指令是一样的
        //opsForValue   操作字符串 类似string
        //opsForList    操作list  类似list
        //opsForSet
        //opsForHash
        //opsForZSet
        //opsForGeo
        //opsForHyperLogLog

        //除了基本的操作，我们常用的方法都可以直接通过redisTemplate操作，比如事物，和基本的CRUD


        // redisTemplate
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.flushDb();

        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("mykey","cola");

    }

    @Test
    void test() throws JsonProcessingException {
        User cola = new User("cola", 3);
//        String jsonUser = new ObjectMapper().writeValueAsString(cola);
        ValueOperations valueOperations = redisTemplate.opsForValue();

        valueOperations.set("user",cola);
        System.out.println(valueOperations.get("user"));

    }

}
