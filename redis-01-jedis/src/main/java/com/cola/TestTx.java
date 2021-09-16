package com.cola;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTx {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("aliyun", 6379);

        jedis.flushDB();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello","world");
        jsonObject.put("name","cola");

        //开启事物
        Transaction multi = jedis.multi();
        String s = jsonObject.toString();

//        jedis.watch(s);   乐观锁
        try {
            multi.set("user1",s);
            multi.set("user2",s);
            int i=1/0;
            multi.exec();
        }catch (Exception e){
            multi.discard(); //放弃事物
            e.printStackTrace();
        }finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));


            jedis.close();

        }







    }
}
