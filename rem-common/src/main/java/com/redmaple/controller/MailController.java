package com.redmaple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redmaple.entity.MailBean;
import com.redmaple.service.MailService;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;
    
    /**
     * 发送文本邮件
     *
     * @param mailBean
     */
    @PostMapping("/sendSimpleMail")
    public  void sendSimpleMail(@RequestBody MailBean mailBean) {
       mailService.sendSimpleMail(mailBean);
    }

    /**
     * @description: HTML格式邮件发送
     * @author: 若成风
     * @date: 2020/11/3 20:57
     * @return:
     */
    @PostMapping("/sendHTMLMail")
    public void sendHTMLMail(@RequestBody MailBean mailBean) {
    	mailService.sendHTMLMail(mailBean);
    }

    /**
     * 
     * @Description: 发送附件
     * @auth：uwank171 
     * @date: 2020年11月4日 上午9:51:37
     * @param mailBean           
     *
     */
    @PostMapping("/sendAttachmentMail")
    public void sendAttachmentMail(@RequestBody MailBean mailBean) {
    	mailService.sendAttachmentMail(mailBean);
    }

    /**
     * 
     * @Description: 静态资源
     * @auth：uwank171 
     * @date: 2020年11月4日 上午9:51:49
     * @param mailBean           
     *
     */
    @PostMapping("/sendInlineMail")
    public void sendInlineMail(@RequestBody MailBean mailBean) {
    	mailService.sendInlineMail(mailBean);
    }
    
    /**
     * 
     * @Description: 解析模板html发送
     * @auth：uwank171 
     * @date: 2020年11月4日 上午9:59:25
     * @param mailBean           
     *
     */
    @PostMapping("/sendTemplate2Mail")
    public void sendTemplate2Mail(@RequestBody MailBean mailBean) {
    	mailService.sendTemplate2Mail(mailBean);
    }
    
    
    
    
    
    
    
    
}