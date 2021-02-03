package com.redmaple.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**   
 * @Description: 配置jedisPool连接池
 * @author: uwank171 
 * @date: Feb 3, 2021 3:20:26 PM 
 *  
 */
@Configuration
public class JedisConfig {
	
	@Value("${spring.redis.host}")
    private String host;
	
	@Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;
    
    @Value("${spring.redis.jedis.pool.max-wait}")
    private int maxWaitMillis;
    
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

	@Bean
    public JedisPool getJedisPool() {
        System.out.println("JedisPool注入开始...");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        JedisPool jedisPool;
        if (StringUtils.isBlank(password)) {
        	jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
		} else {
			jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
		}
        System.out.println("JedisPool注入成功...");
        return jedisPool;
	}

}
