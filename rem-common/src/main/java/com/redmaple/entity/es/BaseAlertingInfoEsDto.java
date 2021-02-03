package com.redmaple.entity.es;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @Description: 
 * @author: uwank171 
 * @date: Feb 3, 2021 9:23:14 AM 
 *
 */
@ApiModel(value = "BaseAlertingInfoEsDto")
@Document(indexName = "es-alertinginfo")
public class BaseAlertingInfoEsDto {
	// 主键id --> @Id
	// 其余字段 --> @Field
	@Id
	@ApiModelProperty(value = "主键")
	private String id;

	@ApiModelProperty(value = "系统名称")
	private String systemName;
	
	@ApiModelProperty(value = "类型名称：如itsm-cn，itsm-eu")
	private String typeName;

	@ApiModelProperty(value = "编码")
	private String code;

	@ApiModelProperty(value = "提示信息")
	private String alertingInfo;
	
	@ApiModelProperty(value = "睡眠时间(毫秒)")
	private Integer sleepTime;
	
	@ApiModelProperty(value = "超时时间(毫秒)")
	private Integer timeout;

	@ApiModelProperty(value = "发送人gid")
	private String senderName;
	
	@ApiModelProperty(value = "接收人gid，多个利用英文分号隔开")
	private String recipentName;
	
	@ApiModelProperty(value = "发送消息类型：0-短信，邮件，电话  1-短信	2-邮件	3-电话  4-短信电话")
	private String sendMessageType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAlertingInfo() {
		return alertingInfo;
	}

	public void setAlertingInfo(String alertingInfo) {
		this.alertingInfo = alertingInfo;
	}

	public Integer getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(Integer sleepTime) {
		this.sleepTime = sleepTime;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getRecipentName() {
		return recipentName;
	}

	public void setRecipentName(String recipentName) {
		this.recipentName = recipentName;
	}

	public String getSendMessageType() {
		return sendMessageType;
	}

	public void setSendMessageType(String sendMessageType) {
		this.sendMessageType = sendMessageType;
	}
	
}
