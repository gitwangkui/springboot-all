package com.redmaple.dao.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.redmaple.entity.es.BaseAlertingInfoEsDto;

/**   
 * @Description: 
 * @author: uwank171 
 * @date: 2020年10月28日 下午3:40:05 
 *  
 */
public interface BaseAlertingInfoEs extends ElasticsearchRepository<BaseAlertingInfoEsDto, String>{
	
	/**
	 * 
	 * @Description: 通过id查询
	 * @auth：uwank171 
	 * @date: 2020年10月28日 下午3:44:15
	 * @param id
	 * @return  
	 *
	 */
	BaseAlertingInfoEsDto queryById(String id);

}