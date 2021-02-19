package com.redmaple.test.easypoi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

@ExcelTarget("teacherEntity")
public class TeacherEntity implements java.io.Serializable {
    private String id;
    /** name */
    @Excel(name = "课程老师", orderNum = "1", needMerge = true, isImportField = "true_major,true_absent")
    private String name;
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TeacherEntity(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
    
//    
}