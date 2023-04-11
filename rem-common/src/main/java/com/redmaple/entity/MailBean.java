package com.redmaple.entity;

import java.io.Serializable;
/**
 * 
 * @Description: 发送邮件
 * @author: uwank171 
 * @date: 2020年11月4日 上午9:44:32 
 *
 */
public class MailBean implements Serializable {
    private static final long serialVersionUID = -2116367492649751914L;

    private String sender;//邮件发送人
    
    private String recipient;//邮件接收人
    
    private String [] arrayRecipient;//邮件接收人-数组

    private String subject; //邮件主题

    private String content; //邮件内容


	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

	public String [] getArrayRecipient() {
		return arrayRecipient;
	}

	public void setArrayRecipient(String [] arrayRecipient) {
		this.arrayRecipient = arrayRecipient;
	}

	public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}