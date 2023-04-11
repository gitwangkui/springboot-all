package com.redmaple.test.other;

import com.redmaple.common.utils.HttpClientUtil;

/**
 * 
 * @Description: 参考：http://timor.tech/api/holiday 
 * @author: uwank171 
 * @date: Feb 19, 2021 10:22:23 AM 
 *
 */
public class HolidayUtil {
	
	//public static String baseUrl = "http://timor.tech/api/holiday";
	//58.220.95.12:10792
	public static String baseUrl = "http://timor.tech/api/holiday";
	
	public static void main(String[] args) {
//		proxyNet();
		//获取指定日期的节假日信息
		getHolidayInfo(null);
		
	}
	
	/**
	 * 
	 * @Description: 
	 * @auth：uwank171 
	 * @date: Feb 19, 2021 10:14:24 AM
	 * @param date 2018-3-2  2018-03-02
	 * @return           
	 *
	 */
	public static String getHolidayInfo(String date) {
		String url = baseUrl + "/info";
		System.out.println(url);
		String data = HttpClientUtil.doGetJson(url);
		System.out.println("\n==== " + data);

//		String string = HttpClient.get(url, null);
//		System.out.println("\n====   " + string);
		
		return "\n================= 运行结束  =================";
	}
		
	
	
	public static void proxyNet() {
		// 代理地址
		String proxyHost = "10.196.58.10";
		// 代理端口
		String proxyPort = "10792";
	    // http
	    System.setProperty("http.proxyHost", proxyHost);
	    System.setProperty("http.proxyPort", proxyPort);
	    // https
	    System.setProperty("https.proxyHost", proxyHost);
	    System.setProperty("https.proxyPort", proxyPort);
	    
	}
}
