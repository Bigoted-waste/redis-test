package com.cola;

import redis.clients.jedis.Jedis;

public class TestPing {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("aliyun",6379);
        System.out.println(jedis.ping());
    }
}
