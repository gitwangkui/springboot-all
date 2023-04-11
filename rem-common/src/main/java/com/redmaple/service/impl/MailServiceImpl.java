package com.redmaple.service.impl;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.redmaple.common.utils.ProxyUtil;
import com.redmaple.entity.MailBean;
import com.redmaple.service.MailService;

/**   
 * @Description: 
 * @author: uwank171 
 * @date: 2020年11月4日 上午9:47:31 
 *  
 */
@Service
public class MailServiceImpl implements MailService {
	
	private Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
	private static String EMAIL_SENT_SUCCESS = "email sent success";
	private static String EMAIL_SENT_FAILED = "email sent failed";

    private String MAIL_SENDER; //邮件发送者
	@Autowired
    private JavaMailSender javaMailSender;
	@Autowired
    private TemplateEngine templateEngine;
	
	@Override
	public boolean sendSimpleMail(MailBean mailBean) {
		boolean result = true;
		try {
//			ProxyUtil.proxyNet(); //网络代理
            SimpleMailMessage mailMessage= new SimpleMailMessage();
            mailMessage.setFrom(mailBean.getSender());
            mailMessage.setTo(mailBean.getArrayRecipient());
            mailMessage.setSubject(mailBean.getSubject());
            mailMessage.setText(mailBean.getContent());
            javaMailSender.send(mailMessage);
            logger.info("\n---> {}", EMAIL_SENT_SUCCESS);
        } catch (Exception e) {
        	result = false;
            logger.info("\n---> {}", EMAIL_SENT_FAILED);
            e.printStackTrace();
        }
		return result;
	}

	@Override
	public boolean sendHTMLMail(MailBean mailBean) {
		MimeMessage mimeMailMessage = null;
		boolean result = true;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            //true 表示需要创建一个multipart message
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(mailBean.getSender());
            mimeMessageHelper.setTo(mailBean.getArrayRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            //邮件抄送
            //mimeMessageHelper.addCc("抄送人");
            mimeMessageHelper.setText(mailBean.getContent(), true);
            javaMailSender.send(mimeMailMessage);
            logger.info("\n---> {}", EMAIL_SENT_SUCCESS);
        } catch (Exception e) {
        	result = false;
        	logger.info("\n---> {}", EMAIL_SENT_FAILED);
        	e.printStackTrace();
        }
        return result;
	}

	@Override
	public void sendAttachmentMail(MailBean mailBean) {
		 MimeMessage mimeMailMessage = null;
	        try {
	            mimeMailMessage = javaMailSender.createMimeMessage();
	            //true 表示需要创建一个multipart message
	            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
	            mimeMessageHelper.setFrom(MAIL_SENDER);
	            mimeMessageHelper.setTo(mailBean.getRecipient());
	            mimeMessageHelper.setSubject(mailBean.getSubject());
	            mimeMessageHelper.setText(mailBean.getContent());
	            //文件路径 目前写死在代码中，之后可以当参数传过来，或者在MailBean中添加属性absolutePath
	            String absolutePath = "D:\\Program Files\\test.jpg";
	            FileSystemResource file = new FileSystemResource(new File(absolutePath));
	            //FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/email.png"));
	            String fileName = absolutePath.substring(absolutePath.lastIndexOf(File.separator));
	            //添加附件,第一个参数表示添加到 Email 中附件的名称，第二个参数是图片资源
	            mimeMessageHelper.addAttachment(fileName, file);
	            //多个附件
	            //mimeMessageHelper.addAttachment(fileName1, file1);

	            javaMailSender.send(mimeMailMessage);
	            logger.info("\n邮件发送成功");
	        } catch (Exception e) {
	            logger.error("邮件发送失败", e.getMessage());
	        }

	}

	@Override
	public void sendInlineMail(MailBean mailBean) {
		MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(MAIL_SENDER);
            mimeMessageHelper.setTo(mailBean.getRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            mimeMessageHelper.setText(mailBean.getContent(), true);
            //文件路径
            String absolutePath = "D:\\Program Files\\email.png";
            FileSystemResource file = new FileSystemResource(new File(absolutePath));
            //FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/email.png"))
            //添加多个图片可以使用多条 <img src='cid:" + rscId + "' > 和 mimeMessageHelper.addInline(rscId, res) 来实现
            mimeMessageHelper.addInline("picture", file);

            javaMailSender.send(mimeMailMessage);
            logger.info("\n邮件发送成功");
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }

	}

	@Override
	public void sendTemplate2Mail(MailBean mailBean) {
	   String RECIPINET = "1805797191@qq.com";
      //注意：Context 类是在org.thymeleaf.context.Context包下的。
      Context context = new Context();
      //html中填充动态属性值
      context.setVariable("username", "码农用户");
      context.setVariable("url", "https://www.aliyun.com/?utm_content=se_1000301881");
      //注意：process第一个参数名称要和templates下的模板名称一致。要不然会报错
      //org.thymeleaf.exceptions.TemplateInputException: Error resolving template [email]
      String emailContent = templateEngine.process("email", context);

      mailBean.setRecipient(RECIPINET);
      mailBean.setSubject("主题：这是模板邮件");
      mailBean.setContent(emailContent);
      this.sendHTMLMail(mailBean);
		
	}

}
