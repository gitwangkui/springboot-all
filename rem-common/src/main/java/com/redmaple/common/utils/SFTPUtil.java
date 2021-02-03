package com.redmaple.common.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.commons.io.IOUtils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.redmaple.common.sftp.SFTPConstants;

/**   
 * @Description: 
 * @author: uwank171 
 * @date: Feb 3, 2021 4:15:39 PM 
 *  
 */
public class SFTPUtil {
	
	private static Logger log = LoggerFactory.getLogger(SFTPUtil.class);
	
	public static final String SFTP_REQ_HOST = "host";
	public static final String SFTP_REQ_PORT = "port";
	public static final String SFTP_REQ_USERNAME = "username";
	public static final String SFTP_REQ_PASSWORD = "password";
	public static final int SFTP_DEFAULT_PORT = 22;
	public static final String SFTP_REQ_LOC = "location";
	
	public static String host = "172.28.13.140";
	public static String prot = "22";
	public static String username = "root";
	public static String password = "China1234";
	
	/**
	 * 
	 * @Description: 通过文件上传到服务器
	 * @auth：uwank171 
	 * @date: 2020年11月24日 下午3:54:18
	 * @param file 包含路径：/opt/software/data/StandardSolution.csv
	 * @param fileName file对象
	 * @throws Exception           
	 *
	 */
	public static void upload(File file, String fileName) {
		Map<String, String> sftpDetails = new HashMap<String, String>();
		// 设置主机ip，端口，用户名，密码
		sftpDetails.put(SFTP_REQ_HOST, host);
		sftpDetails.put(SFTP_REQ_PORT, prot);
		sftpDetails.put(SFTP_REQ_USERNAME, username);
		sftpDetails.put(SFTP_REQ_PASSWORD, password);
		try {
			SFTPChannel channel = new SFTPChannel();
			ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);
			FileInputStream fileInputStream = new FileInputStream(file);
			chSftp.put(fileInputStream, fileName);
			chSftp.quit();
			channel.closeChannel();
			if (fileInputStream != null) {
				fileInputStream.close();
			}
			log.info("\n=== 上传完成 ===");
		} catch (Exception e) {
			log.info("\n=== 上传edi数据异常 ===", e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @Description:
	 * @auth：uwank171
	 * @date: 2020年11月24日 下午3:45:03
	 * @param XMLString 文件转成字符串
	 * @param fileName  文件名称 /opt/software/data/StandardSolution.csv
	 * @throws Exception
	 *
	 */
	public static void upload(String XMLString, String fileName) throws Exception {
		Map<String, String> sftpDetails = new HashMap<String, String>();
		// 设置主机ip，端口，用户名，密码
		sftpDetails.put(SFTP_REQ_HOST, "172.28.13.140");
		sftpDetails.put(SFTP_REQ_USERNAME, "root");
		sftpDetails.put(SFTP_REQ_PASSWORD, "China1234");
		sftpDetails.put(SFTP_REQ_PORT, "22");
		try {
			SFTPChannel channel = new SFTPChannel();
			ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);
			InputStream XmlStream = new ByteArrayInputStream(XMLString.getBytes("UTF-8"));
			chSftp.put(XmlStream, fileName);
			chSftp.quit();
			channel.closeChannel();
			if (XmlStream != null) {
				XmlStream.close();
			}
			log.info("=== 上传完成 ===");
		} catch (Exception e) {
			log.info("=== 上传数据异常 ===", e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	/** 
	 * 
	 * @Description: 下载文件
	 * @auth：uwank171 
	 * @date: Feb 3, 2021 4:40:34 PM
	 * @param downloadFilePath 全文件名 /home/uwank/software/YFIHFMServiceImpl.java
	 * @param saveFilePath 要保存的全文件名称,
	 * @throws Exception           
	 *
	 */
	public static void download(String downloadFilePath, String saveFilePath) throws Exception {
		Map<String, String> sftpDetails = new HashMap<String, String>();
		// 设置主机ip，端口，用户名，密码
		sftpDetails.put(SFTP_REQ_HOST, "172.28.13.140");
		sftpDetails.put(SFTP_REQ_USERNAME, "root");
		sftpDetails.put(SFTP_REQ_PASSWORD, "China1234");
		sftpDetails.put(SFTP_REQ_PORT, "22");
		try {
			SFTPChannel channel = new SFTPChannel();
			ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);
			File saveFile = new File(saveFilePath);
			chSftp.get(downloadFilePath, new FileOutputStream(saveFile));
			chSftp.quit();
			channel.closeChannel();
			log.info("=== 下载完成 ===");
		} catch (Exception e) {
			log.info("=== 下载数据异常 ===", e.getMessage());
			e.printStackTrace();
		}
	}
	
	/** 
	 * 
	 * @Description: 下载文件
	 * @auth：uwank171 
	 * @date: Feb 3, 2021 4:40:34 PM
	 * @param downloadFilePath 全文件名 /home/uwank/software/YFIHFMServiceImpl.java
	 * @throws Exception           
	 *
	 */
	public static InputStream download(String downloadFilePath) throws Exception {
		Map<String, String> sftpDetails = new HashMap<String, String>();
		// 设置主机ip，端口，用户名，密码
		sftpDetails.put(SFTP_REQ_HOST, "172.28.13.140");
		sftpDetails.put(SFTP_REQ_USERNAME, "root");
		sftpDetails.put(SFTP_REQ_PASSWORD, "China1234");
		sftpDetails.put(SFTP_REQ_PORT, "22");
		try {
			SFTPChannel channel = new SFTPChannel();
			ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);
			InputStream inputStream = chSftp.get(downloadFilePath);
			chSftp.quit();
			channel.closeChannel();
			log.info("=== 下载完成 ===");
			return inputStream;
		} catch (Exception e) {
			log.info("=== 下载数据异常 ===", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	/** 
	 * 
	 * @Description: 下载文件
	 * @auth：uwank171 
	 * @date: Feb 3, 2021 4:40:34 PM
	 * @param downloadFilePath 全文件名 /home/uwank/software/YFIHFMServiceImpl.java
	 * @throws Exception           
	 *
	 */
	public static byte[] downloadByte(String downloadFilePath) throws Exception {
		Map<String, String> sftpDetails = new HashMap<String, String>();
		// 设置主机ip，端口，用户名，密码
		sftpDetails.put(SFTP_REQ_HOST, "172.28.13.140");
		sftpDetails.put(SFTP_REQ_USERNAME, "root");
		sftpDetails.put(SFTP_REQ_PASSWORD, "China1234");
		sftpDetails.put(SFTP_REQ_PORT, "22");
		try {
			SFTPChannel channel = new SFTPChannel();
			ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);
			InputStream inputStream = chSftp.get(downloadFilePath);
			chSftp.quit();
			channel.closeChannel();
			log.info("=== 下载完成 ===");
			return null;
//			return IOUtils.toByteArray(inputStream);
		} catch (SftpException | IOException e) {
			log.info("=== 下载数据异常 ===", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
	
static class SFTPChannel{
	
	Session session = null;
    Channel channel = null;
	
	public ChannelSftp getChannel(Map<String, String> sftpDetails, int timeout) throws JSchException {
        String ftpHost = sftpDetails.get(SFTPConstants.SFTP_REQ_HOST);
        String port = sftpDetails.get(SFTPConstants.SFTP_REQ_PORT);
        String ftpUserName = sftpDetails.get(SFTPConstants.SFTP_REQ_USERNAME);
        String ftpPassword = sftpDetails.get(SFTPConstants.SFTP_REQ_PASSWORD);

        int ftpPort = SFTPConstants.SFTP_DEFAULT_PORT;
        if (port != null && !port.equals("")) {
            ftpPort = Integer.valueOf(port);
        }

        JSch jsch = new JSch(); // 创建JSch对象
        session = jsch.getSession(ftpUserName, ftpHost, ftpPort); // 根据用户名，主机ip，端口获取一个Session对象
        log.debug("Session created.");
        if (ftpPassword != null) {
            session.setPassword(ftpPassword); // 设置密码
        }
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config); // 为Session对象设置properties
        session.setTimeout(timeout); // 设置timeout时间
        session.connect(); // 通过Session建立链接
        log.debug("Session connected.");

        log.debug("Opening Channel.");
        channel = session.openChannel("sftp"); // 打开SFTP通道
        channel.connect(); // 建立SFTP通道的连接
        log.info("Connected successfully to ftpHost = " + ftpHost + ",as ftpUserName = " + ftpUserName
                + ", returning: " + channel);
        return (ChannelSftp) channel;
    }

	// 释放资源
    public void closeChannel() throws Exception {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }
}
    
    

}
