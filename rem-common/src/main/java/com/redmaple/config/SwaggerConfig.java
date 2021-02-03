package com.redmaple.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @Description: 
 * @author: uwank171 
 * @date: Feb 3, 2021 1:24:08 PM 
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 初始化创建Swagger Api
     */
    @Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				// 详细信息定制
				.apiInfo(apiInfo())
				.select()
				// 指定当前包路径
				.apis(RequestHandlerSelectors.basePackage("com.redmaple.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	/**
	 * 添加摘要信息
	 */
	private ApiInfo apiInfo() {
		// 用ApiInfoBuilder进行定制
		return new ApiInfoBuilder()
				.title("【springboot-all】")
				.description("springboot-all接口文档")
				.version("版本号: 1.0")
				.build();
	}
}
