package com.redmaple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redmaple.config.JedisConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**   
 * @Description: 测试 jedis 功能
 * @author: uwank171 
 * @date: 2021年3月23日 上午10:29:47 
 *  
 */
@RestController
@RequestMapping("/jedis")
public class JedisController {
	
	@Autowired
	private JedisConfig jedisConfig;
	
	@GetMapping("/test")
	public String TestJedis() {
		Jedis jedis = jedisConfig.getJedis();
		jedis.set("jedis_007", "jedis_007");
		String string = jedis.get("jedis_007");
		System.out.println(string);
		jedis.close();
		return "success";
	}
}
