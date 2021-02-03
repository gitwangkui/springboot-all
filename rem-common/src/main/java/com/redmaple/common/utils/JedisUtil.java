package com.redmaple.common.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**   
 * @Description: 
 * @author: uwank171 
 * @date: Feb 3, 2021 3:36:23 PM 
 *  
 */
@Component
public class JedisUtil {
	
	private static Logger logger = LoggerFactory.getLogger(JedisUtil.class);
	
	// jedisPool的具体参数已经配置jedisConfig.java文件
	@Autowired
	private JedisPool jedisPool;
	
	public String set(String key, String value) {
		Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, value);
        } catch (Exception e) {
        	logger.error("----> jedis set error", e);
        } finally {
        	close(jedis);
        }
        return null;
	}
	
	public String set(String key, String value, int expireSeconds) {
		Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String set = jedis.set(key, value);
            jedis.expire(key, expireSeconds);
            return set;
        } catch (Exception e) {
        	logger.error("----> jedis operator error", e);
        } finally {
        	close(jedis);
        }
        return null;
	}
	
	public String get(String key) {
		Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
        	logger.error("----> jedis operator error", e);
        } finally {
        	close(jedis);
        }
        return null;
	}
	
	// ... 其余方法待补充
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/** 
	 * 释放资源
	 */
	public void close(Jedis jedis) {
		if (!ObjectUtils.isEmpty(jedis)) {
			if (jedis != null ) {
				jedis.close();
				jedis.quit();
			}
		}
	}
	
}
