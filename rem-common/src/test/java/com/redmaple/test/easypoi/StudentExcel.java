package com.redmaple.test.easypoi;

import java.io.Serializable;
import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

@ExcelTarget("学生表")	//表示可以针对不同字段做不同处理
public class StudentExcel implements Serializable {

    
	private static final long serialVersionUID = -5971356610755498863L;

	@Excel(name = "编号" )
    private Integer id;
	
    @Excel(name = "姓名", isImportField = "true_st" )
    private String one;
    
    @Excel(name = "姓别", isImportField = "true_st", replace = { "男_1", "女_0" })
    private String two;
    
    @Excel(name = "出生日期", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd",  isImportField = "true_st" )
    private Date brithday;
    
    @Excel(name = "联系电话" )
    private String tj_xm;

    
	public Integer getId() {
		return id;
	}

	public String getOne() {
		return one;
	}

	public String getTwo() {
		return two;
	}

	public Date getBrithday() {
		return brithday;
	}

	public String getTj_xm() {
		return tj_xm;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOne(String one) {
		this.one = one;
	}

	public void setTwo(String two) {
		this.two = two;
	}

	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}

	public void setTj_xm(String tj_xm) {
		this.tj_xm = tj_xm;
	}

	public StudentExcel(Integer id, String one, String two, Date brithday, String tj_xm) {
		super();
		this.id = id;
		this.one = one;
		this.two = two;
		this.brithday = brithday;
		this.tj_xm = tj_xm;
	}

}
