package com.redmaple.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @Description: 
 * @author: uwank171 
 * @date: 2020年11月13日 上午9:03:42 
 *
 */
public class HttpClientUtil {
	
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	/**
	 * 
	 * @Description: doGet(url, null)
	 * @auth：uwank171 
	 * @date: 2020年11月13日 下午4:12:56
	 * @param url
	 * @return           
	 *
	 */
	public static String doGet(String url) {
		return doGet(url, null);
	}
	
	/**
	 * 
	 * @Description: doGet(String url, Map<String, Object> param)
	 * @auth：uwank171 
	 * @date: 2020年11月13日 下午4:13:09
	 * @param url
	 * @param param
	 * @return           
	 *
	 */
	public static String doGet(String url, Map<String, Object> param) {
		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String resultString = "";
		CloseableHttpResponse response = null;
		try {
			// 创建uri
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, (String) param.get(key));
				}
			}
			URI uri = builder.build();
			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);
			// 执行请求
			response = httpclient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("请求异常", e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("请求异常", e);
			}
		}
		return resultString;
	}

	/**
	 * 
	 * @Description: doPost(url,null,encoding)
	 * @auth：uwank171 
	 * @date: 2020年11月13日 下午4:13:40
	 * @param url
	 * @param encoding
	 * @return           
	 *
	 */
	public static String doPost(String url,String encoding) {
		return doPost(url,null,encoding);
	}
	
	/**
	 * 
	 * @Description: String doPost(String url, Map<String, Object> param, String encoding)
	 * @auth：uwank171 
	 * @date: 2020年11月13日 下午4:13:55
	 * @param url
	 * @param param
	 * @param encoding 字符编码
	 * @return           
	 *
	 */
	public static String doPost(String url, Map<String, Object> param, String encoding) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key,(String) param.get(key)));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList,"UTF-8");
				httpPost.setEntity(entity);
			}
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), encoding);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("请求异常", e);
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("请求异常", e);
			}
		}
		return resultString;
	}

	/**
	 * 
	 * @Description: doPostJson(String url, String json)
	 * @auth：uwank171 
	 * @date: 2020年11月13日 下午4:15:15
	 * @param url
	 * @param json
	 * @return           
	 *
	 */
	public static String doPostJson(String url, String json) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			// 执行http请求
			response = httpClient.execute(httpPost);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("请求异常", e);
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("请求异常", e);
			}
		}
		return resultString;
	}
	
	/**
	 * 
	 * @Description: doPostJson(String url, String json)
	 * @auth：uwank171 
	 * @date: 2020年11月13日 下午4:15:15
	 * @param url
	 * @param json
	 * @return           
	 *
	 */
	public static String doGetJson(String url) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Get请求
			HttpGet httpGet = new HttpGet(url);
			// 执行http请求
			response = httpClient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("请求异常", e);
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("请求异常", e);
			}
		}
		return resultString;
	}
	
	
	/**  
	* @Title: doGetOrdoPost  
	* @Description: get、post请求  
	* @param  targetURL 目标url
	* @param  encoding 编码格式
	* @param  getOrPost 提交方式
	* @return String   
	* @throws  
	*/ 
	public static String doGetOrdoPost(String targetURL,String encoding,String getOrPost){
		JSONObject result = new JSONObject();
		StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            URL restServiceURL = new URL(targetURL);
            // 打开url连接
            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            // 设置通用的属性
            httpConnection.setRequestProperty("accept", "application/json, text/javascript, */*; q=0.01");
            httpConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            httpConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
            httpConnection.setRequestProperty("connection", "keep-Alive");
            httpConnection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36");
            httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            httpConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            //设置超时时间
            httpConnection.setConnectTimeout(5000);
            httpConnection.setReadTimeout(15000);
            // 显式地设置为POST，默认为GET
            if("POST".equalsIgnoreCase(getOrPost)){
            	httpConnection.setRequestMethod("POST");
            	// post请求不应该使用cache
            	httpConnection.setUseCaches(false);
            }
            httpConnection.setDoOutput(true);
            httpConnection.setDoInput(true);
            httpConnection.connect();
            // 获取Urlconnection对象的输出流，调用conn.getOutputStream的时候就会设置为POST方法
            out = new PrintWriter(new OutputStreamWriter(httpConnection.getOutputStream(), "UTF-8"));
            // flush输出流的缓冲，这样参数才能发送出去
            out.flush();
            if (httpConnection.getResponseCode() != 200) {
                result.put("message", "数据获取异常，请联系系统管理员");
                result.put("result", "ERROR");
                result.put("code", null);
                result.put("object", null);
                
            }
            // 读取流里的内容，注意编码问题
            in = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), encoding));
            String line = "";
            while (null != (line = in.readLine())) {
                sb.append(line);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            result.put("message", "数据获取异常，请联系系统管理员");
            result.put("result", "ERROR");
            result.put("code", null);
            result.put("object", null);
            logger.error("请求异常", result);
        } finally {
            // 关闭流
            try {
                if (null != out) {
                    out.close();
                }
                if (null != in) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                result.put("message", "数据获取异常，请联系系统管理员");
                result.put("result", "ERROR");
                result.put("code", null);
                result.put("object", null);
                logger.error("请求异常", result);
            }
        }
		return sb.toString();
	}
	
	
}
