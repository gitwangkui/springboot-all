/**   
 * @Description: 
 * @author: uwank171   
 * @date: 2020年11月4日 上午9:45:21 
 *
 */
package com.redmaple.service;

import com.redmaple.entity.MailBean;

/**   
 * @Description: 
 * @author: uwank171 
 * @date: 2020年11月4日 上午9:45:21 
 *  
 */
public interface MailService {
	
	/**
	 * 
	 * @Description: 发送文本邮件
	 * @auth：uwank171 
	 * @date: 2020年11月4日 上午9:46:33
	 * @param mailBean           
	 *
	 */
    boolean sendSimpleMail(MailBean mailBean);

    /**
     * @description: HTML格式邮件发送
     * @author: 若成风
     * @date: 2020/11/3 20:57
     * @return:
     */
    boolean sendHTMLMail(MailBean mailBean);

    /**
     * 
     * @Description: 发送附件邮件
     * @auth：uwank171 
     * @date: 2020年11月4日 上午9:46:41
     * @param mailBean           
     *
     */
    void sendAttachmentMail(MailBean mailBean);

    /**
     * 
     * @Description: 发送静态资源
     * @auth：uwank171 
     * @date: 2020年11月4日 上午9:46:54
     * @param mailBean           
     *
     */
    void sendInlineMail(MailBean mailBean);
    
    /**
     * 
     * @Description: 解析模板html发送
     * @auth：uwank171 
     * @date: 2020年11月4日 上午9:56:35
     * @param mailBean           
     *
     */
    void sendTemplate2Mail(MailBean mailBean); 
}
