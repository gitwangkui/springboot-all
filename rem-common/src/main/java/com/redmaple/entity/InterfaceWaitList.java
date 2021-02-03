package com.redmaple.entity; 

import java.util.Date;

/**   
 * @Description: 
 * @author: uwank171 
 * @date: Feb 3, 2021 9:07:28 AM 
 *  
 */
public class InterfaceWaitList {
	
	private Integer id;
	private String taskid;
	private Integer status;
	private String taskwfcd;
	private Date addtime;
	private Date finishedtime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getTaskwfcd() {
		return taskwfcd;
	}
	public void setTaskwfcd(String taskwfcd) {
		this.taskwfcd = taskwfcd;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public Date getFinishedtime() {
		return finishedtime;
	}
	public void setFinishedtime(Date finishedtime) {
		this.finishedtime = finishedtime;
	}
	
	
}
