package com.redmaple.test.sftp;

import java.io.File;

import com.redmaple.common.utils.SFTPUtil;

/**   
 * @Description: 
 * @author: uwank171 
 * @date: Feb 3, 2021 4:30:00 PM 
 *  
 */
public class SftpTest {
	public static void main(String[] args) throws Exception{
//		String filePath = "C:\\Users\\uwank171\\Documents\\001GI\\0001-20210114需求\\YFIHFMServiceImpl.java";
//		File file = new File(filePath);
//		String fileName = "/home/uwank/software/YFIHFMServiceImpl.java";
//		SFTPUtil.upload(file, fileName);
		
		String downloadFilePath = "/home/uwank/software/YFIHFMServiceImpl.java";
		String saveFilePath = "C:\\Users\\uwank171\\Desktop\\YFIHFMServiceImpl.java";
		SFTPUtil.download(downloadFilePath, saveFilePath);
	}
}
