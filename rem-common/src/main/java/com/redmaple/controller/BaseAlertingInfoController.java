package com.redmaple.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.redmaple.common.utils.JedisUtil;
import com.redmaple.dao.es.BaseAlertingInfoEs;
import com.redmaple.dao.master.BaseAlertingInfoMapper;
import com.redmaple.entity.BaseAlertingInfo;
import com.redmaple.entity.es.BaseAlertingInfoEsDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**   
 * @Description: 
 * @author: uwank171 
 * @date: Feb 3, 2021 12:55:28 PM 
 *  
 */
@RestController
@Api(value = "BaseAlertingInfo功能")
public class BaseAlertingInfoController {
	
	@Autowired
	private BaseAlertingInfoMapper baseAlertingInfoMapper;
	@Autowired
	private BaseAlertingInfoEs baseAlertingInfoEs; 
	
	@ApiOperation(value = "查询BaseAlertingInfo信息", notes = "这是详细解释。。。")
	@GetMapping("/getBy2")
	public String getBy2() {
		System.out.println("\n======= mysql BaseAlertingInfoMapper  =========");
		BaseAlertingInfo baseAlertingInfo = baseAlertingInfoMapper.selectById(2);
		String jsonString = JSONObject.toJSONString(baseAlertingInfo);
		
		return jsonString;
	}
	
	@GetMapping("/saveEs")
	@ApiOperation(value = "测试保存ES信息", notes = "这是详细解释。。。")
	public String saveEs() {
		System.out.println("\n===== saveEs   ==================");
		BaseAlertingInfo baseAlertingInfo = baseAlertingInfoMapper.selectById(2);
		BaseAlertingInfoEsDto es = new BaseAlertingInfoEsDto();
		BeanUtils.copyProperties(baseAlertingInfo, es);
		es.setId("alertingInfo"+System.currentTimeMillis());
		baseAlertingInfoEs.save(es);
		System.out.println("=====  save es sucess! =====");
		return "=====  save es sucess! =====";
	}
	
	
	@Autowired
	private JedisUtil jedisUtil;
	@GetMapping("/redis")
	public String redis() {
		jedisUtil.set("1001", "1001");
		jedisUtil.set("1002", "1002", 15);
		
		String aa1 = jedisUtil.get("1001");
		String aa2 = jedisUtil.get("1002");
		
		return "\n====aa1:"+ aa1 + " ======aa2:" + aa2;
	}
	
}
