package com.redmaple.common.utils;

/**   
 * @Description: 设置网络访问代理
 * @author: uwank171 
 * @date: 2020年10月15日 上午9:19:28 
 *  
 */
public class ProxyUtil {
	
	// 代理地址
	private static String proxyHost = "192.168.x.x";
	// 代理端口
	private static String proxyPort = "10086";
	
	public static void proxyNet() {
	    // http
	    System.setProperty("http.proxyHost", proxyHost);
	    System.setProperty("http.proxyPort", proxyPort);
	    // https
	    System.setProperty("https.proxyHost", proxyHost);
	    System.setProperty("https.proxyPort", proxyPort);
	    
	}
}
