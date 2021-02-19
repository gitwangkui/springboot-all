package com.redmaple.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redmaple.common.utils.HttpClientUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**   
 * @Description: 节假日
 * @author: uwank171 
 * @date: Feb 19, 2021 1:31:16 PM 
 *  
 */
@Api(value = "节假日")
@RestController
public class HolidayController {
		
	public static String baseUrl = "http://timor.tech/api/holiday";
	
	@ApiOperation(value = "获取指定日期的节假日信息")
	@GetMapping("/getHolidayInfo")
	public String getHolidayInfo() {
		System.out.println("\n===== getHolidayInfo");
		String url = baseUrl + "/info";
		System.out.println("\n====完整的url：" + url);
		String data = HttpClientUtil.doGetJson(url);
		System.out.println("\n====返回的结果：" + data);
		return data;
	}
}
