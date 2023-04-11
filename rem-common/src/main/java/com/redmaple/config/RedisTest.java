package com.redmaple.config;

import redis.clients.jedis.Jedis;

public class RedisTest {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("172.28.13.140");
        // 如果 Redis 服务设置来密码，需要下面这行，没有就不需要
        jedis.auth("China1234"); 
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        
        jedis.set("jedis1007", "jedis1007-1007");
        String value = jedis.get("jedis1007");
        System.out.println(value);
        
        
        
        
    }
}