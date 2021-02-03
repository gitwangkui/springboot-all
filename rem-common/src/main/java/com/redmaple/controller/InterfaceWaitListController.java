package com.redmaple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.redmaple.dao.sqlserver.InterfaceWaitListMapper;
import com.redmaple.entity.InterfaceWaitList;

/**   
 * @Description: 
 * @author: uwank171 
 * @date: Feb 3, 2021 11:18:42 AM 
 *  
 */
@RestController
public class InterfaceWaitListController {
	
	@Autowired
	private InterfaceWaitListMapper interfaceWaitListMapper;
	
	@GetMapping("/getBy")
	public String getBy() {
		System.out.println("\n====== sqlserver InterfaceWaitListMapper==========");
		InterfaceWaitList interfaceWaitList = interfaceWaitListMapper.selectById(1);
		String jsonString = JSONObject.toJSONString(interfaceWaitList);
		
		return jsonString;
	}
	
	
}
