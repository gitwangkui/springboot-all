package com.redmaple.common.utils;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @Description:   
 * @author: uwank171 
 * @date: 2020年10月13日 下午1:13:32 
 *
 */
public class UUIDUtil {
	
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
	
	// 加前缀
    public static String getPreUUID(String prefix) {
    	if (StringUtils.isBlank(prefix)) {
    		return UUID.randomUUID().toString().replace("-", "");
		}
        return prefix+ "_" +UUID.randomUUID().toString().replace("-", "");
    }
    
    public static void main(String[] args) {
    	System.out.println(getUUID());
	}
}
