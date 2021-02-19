package com.redmaple.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 
 * @Description: 
 * @author: uwank171 
 * @date: 2020年11月13日 上午9:03:52 
 *
 */
@Component
public class HttpClient {
	
	private static Logger log = LoggerFactory.getLogger(HttpClient.class);

    /**
     * post request
     * @Author uwank
     * @param url url
     * @param params params
     * @return response
     */
    public static String post(String url, MultiValueMap<String, Object> params) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-Type", "application/x-www-form-urlencoded");
        headers.set("Content-Type", "application/json");
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(params, headers), String.class);
        log.info("url:{}", url);
        log.info("response status code:{}", responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    /**
     * 文件上传
     * @Author uwank
     * @param url url
     * @param params params
     * @return response json
     */
    public static String upload(String url, MultiValueMap<String, Object> params) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "multipart/form-data");
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(params, headers), String.class).getBody();
    }


    /**
     * get request
     * @Author uwank
     * @param url url
     * @param params params
     * @return response
     */
    public static String get(String url, Map<String, Object> params) {
        RestTemplate restTemplate = new RestTemplate();
        String completeUrl = addQueryParam(url, params);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(completeUrl, String.class);
        log.info("completeUrl:{}", completeUrl);
        log.info("response status code:{}", responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

//    /**
//     * 文件下载
//     * @Author uwank
//     * @param url http url
//     * @param params get query param
//     * @param targetDir file directory
//     * @return file name
//     */
//    public static String download(String url, Map<String, Object> params, String targetDir) {
//        RestTemplate restTemplate = new RestTemplate();
//        String completeUrl = addQueryParam(url, params);
//        ResponseEntity<byte[]> rsp = restTemplate.getForEntity(completeUrl, byte[].class);
//        String fileName = rsp.getHeaders().getContentDisposition().getFilename();
//        if (StringUtils.isNotEmpty(fileName)) {
//            try {
//                fileName = URLDecoder.decode(fileName, "UTF-8");
//                File dir = new File(targetDir);
//                if (! dir.exists()) {
//                    dir.mkdir();
//                }
//                File file = new File(targetDir + "/" + fileName);
//                if (file.exists()) {
//                    FileUtils.deleteQuietly(file);
//                }
//                FileOutputStream fos = new FileOutputStream(file);
//                IOUtils.write(rsp.getBody(), fos);
//                fos.close();
//                log.info("download status code:{}", rsp.getStatusCode());
//                return fileName;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }

//    /**
//     * file download
//     * @Author uwank
//     * @param url url
//     * @param filePath file path
//     */
//    public static void download(String url, String filePath) {
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<byte[]> rsp = restTemplate.getForEntity(url, byte[].class);
//        try {
//            File file = new File(filePath);
//            if (file.exists()) {
//                FileUtils.deleteQuietly(file);
//            }
//            FileOutputStream fos = new FileOutputStream(file);
//            IOUtils.write(rsp.getBody(), fos);
//            fos.close();
//            log.info("download status code:{}", rsp.getStatusCode());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 拼接url参数
     * @Author uwank
     * @param url url
     * @param params get query param
     * @return 拼接参数后的url
     */
    private static String addQueryParam(String url, Map<String, Object> params) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(url);
        if (! CollectionUtils.isEmpty(params)) {
            for (Map.Entry<String, ?> varEntry : params.entrySet()) {
                uriComponentsBuilder.queryParam(varEntry.getKey(), varEntry.getValue());
            }
        }
        return uriComponentsBuilder.build().toString();
    }
    
    
    
    
    public static void main(String[] args) {
    	String url = "http://172.28.13.134:18083/message/sendMessage";
    	HttpClient httpClient = new HttpClient();
		Map<String, Object> param = new HashMap<>();
		param.put("mesConSubject", "YF - test subject");
		param.put("mesConBody", "YF - test body! ");
		List<String> mesRecipentNameList = new ArrayList<>();
		mesRecipentNameList.add("uwank171");
		param.put("mesRecipentNameList", mesRecipentNameList);
		param.put("mesSendName", "uzhat110");
		param.put("sendMessageType", "1");
		param.put("systemName", "zabbix");
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.setAll(param);
		httpClient.post(url, params);
	}
    
    
    
}
