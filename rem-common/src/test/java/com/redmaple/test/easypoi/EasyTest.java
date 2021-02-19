package com.redmaple.test.easypoi;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;

/**
 * @Description:
 * @author: uwank171
 * @date: Feb 5, 2021 2:34:24 PM
 * 
 */
public class EasyTest {
	public static void main(String[] args) throws Exception {

		// 1.普通导出
		List<StudentEntity> sslist = new ArrayList<>();
		for (int i = 100; i < 110; i++) {
			int sex = i % 2 ;
			sslist.add(new StudentEntity(String.valueOf(i),"zhangsan"+i, sex, new Date(), new Date()));
		}
//		Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生", "学生"), StudentEntity.class, sslist);
//		String filePath = "C:\\Users\\uwank171\\Documents\\001GI\\2020-11-10\\学生信息.xls";
//		FileOutputStream fos = new FileOutputStream(filePath);
//		workbook.write(fos);
//		fos.close();
		
		// 2.普通合并导出，一对多
//		List<CourseEntity> courseList = new ArrayList<>();
//		courseList.add(new CourseEntity("kc111", "语文", new TeacherEntity("yw1001", "语文老师"), sslist));
//		courseList.add(new CourseEntity("sx111", "数学", new TeacherEntity("yw1002", "数学老师"), sslist));
//		
//		Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("2412312", "测试", "测试"), CourseEntity.class,	courseList);
//		String filePath = "C:\\Users\\uwank171\\Documents\\001GI\\2020-11-10\\学生信002.xls";
//		FileOutputStream fos = new FileOutputStream(filePath);
//		workbook.write(fos);
//		fos.close();
		
		// 3.map导入，自由定义导出列（合并类型）
//		List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
//		
//		//构造对象等同于@Excel
//        ExcelExportEntity excelentity = new ExcelExportEntity("姓名", "name");
//        excelentity.setNeedMerge(true);
//        entity.add(excelentity);
//        entity.add(new ExcelExportEntity("性别", "sex"));
//        excelentity = new ExcelExportEntity(null, "students");
//        
//        List<ExcelExportEntity> temp = new ArrayList<ExcelExportEntity>();
//        temp.add(new ExcelExportEntity("姓名", "name"));
//        temp.add(new ExcelExportEntity("性别", "sex"));
//        //构造List等同于@ExcelCollection 
//        excelentity.setList(temp);
//        entity.add(excelentity);
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        //把我们构造好的bean对象放到params就可以了
//        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("测试", "测试"), entity,list);
//		String filePath = "C:\\Users\\uwank171\\Documents\\001GI\\2020-11-10\\学生信003.xls";
//		FileOutputStream fos = new FileOutputStream(filePath);
//		workbook.write(fos);
//		fos.close();


		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
