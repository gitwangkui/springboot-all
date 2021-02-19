package com.redmaple.test.easypoi;

import java.util.List;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

@ExcelTarget("courseEntity")
 public class CourseEntity implements java.io.Serializable {
    /** 主键 */
    private String        id;
    /** 课程名称 */
    @Excel(name = "课程名称", needMerge = true, orderNum = "1", width = 25)
    private String        name;
    /** 老师主键 */
    @ExcelEntity()
    private TeacherEntity mathTeacher;

    @ExcelCollection(name = "学生", orderNum = "4")
    private List<StudentEntity> students;

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

	public TeacherEntity getMathTeacher() {
		return mathTeacher;
	}

	public void setMathTeacher(TeacherEntity mathTeacher) {
		this.mathTeacher = mathTeacher;
	}

	public List<StudentEntity> getStudents() {
		return students;
	}

	public void setStudents(List<StudentEntity> students) {
		this.students = students;
	}

	
	public CourseEntity() {
	}

	public CourseEntity(String id, String name, TeacherEntity mathTeacher, List<StudentEntity> students) {
		this.id = id;
		this.name = name;
		this.mathTeacher = mathTeacher;
		this.students = students;
	}
    
    
 }