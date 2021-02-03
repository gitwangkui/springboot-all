package com.redmaple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

/**   
 * @Description: 
 * @author: uwank171 
 * @date: Feb 3, 2021 8:48:38 AM 
 *  
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})	//配置多数据源应排除掉默认数据源启动
@EnableConfigurationProperties
@EnableScheduling
public class RedMapleApp {
	public static void main(String[] args) {
		SpringApplication.run(RedMapleApp.class, args);
	}
}
