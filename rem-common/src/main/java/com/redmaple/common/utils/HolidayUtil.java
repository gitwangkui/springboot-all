package com.redmaple.common.utils;

import org.apache.commons.lang3.StringUtils;

/**   
 * @Description: 判断指定日期的节假日信息
 * 参考：http://timor.tech/api/holiday
 * @author: uwank171 
 * @date: Feb 19, 2021 2:58:35 PM 
 *  
 */
public class HolidayUtil {
	
	public static String baseUrl = "http://timor.tech/api/holiday";
	
	/**
	 * 
	 * @Description: 获取指定日期的节假日信息
	 * @auth：uwank171 
	 * @date: Feb 19, 2021 3:00:51 PM
	 * @param dateStr 日期字符串,为空则默认为当前日期，格式：2018-02-03  2018-2-3
	 * @return           
	 *
	 */
	public static String getHolidayInfo(String dateStr) {
		String url = baseUrl + "/info";
		if (StringUtils.isNotBlank(dateStr)) {
			url = url + "/" + dateStr;
		}
		String data = HttpClientUtil.doGetJson(url);
		return data;
	}
	
	/**
	 * 
	 * @Description: 批量查询指定日期节假日信息
	 * 	http://timor.tech/api/holiday/batch?d=$date&type=Y
	 * 	@params d: 指定日期的字符串，多个日期之间使用 & 连接。最大长度查询个数50。兼容旧的格式用逗号,隔开，但不建议。格式 ‘2018-02-23’。
		@params type: 是否返回日期类型，默认不返回。可选值：’Y’ 返回，’N’ 不返回。
		@return json: 返回以日期为key的对象。
	 * @auth：uwank171 
	 * @date: Feb 19, 2021 3:06:47 PM
	 * @param dateStr d=2021-02-02&d=2021-02-03
	 * @return           
	 *
	 */
	public static String getHolidayBatch(String dateStr) {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		String url = baseUrl + "/batch?" + dateStr;
		String data = HttpClientUtil.doGetJson(url);
		System.out.println("\n==== url: " + url);
		return data;
	}
	
	/**
	 * 
	 * @Description: 获取整年/整月包含周末的数据
	 * http://timor.tech/api/holiday/year/$date?type=Y&week=Y
	 * 如果获取指定整年的数据，请在年后面加个斜杠 /
	 *  @params $date: 指定年份或年月份，格式 ‘2019-02’ ‘2019-2’ 或者 ‘2019’。可以省略，则默认服务器当前时间的年份。
		@params type: 是否返回日期类型，默认不返回。可选值：’Y’ 返回，’N’ 不返回。
		@params week: 节假日是否包含周末，默认不包含。可选值：’Y’ 包含周末，’N’ 不包含。
		@return json: 返回指定年份或年月份的所有节假日，以日期作为key，格式：mm-dd。如果没有该年份或月份，则返回空对象。注意目前只配置了最多比当前时间往后一年的节假日。如果包含周末，则节日和周末冲突时，以节日为准。
	 * @auth：uwank171 
	 * @date: Feb 19, 2021 3:21:02 PM
	 * @param dateStr 格式:2021/  2021-02
	 * @return           
	 *
	 */
	public static String getHolidayYear(String dateStr) {
		String url = baseUrl + "/year";
		if (StringUtils.isNotBlank(dateStr)) {
			url = dateStr.contains("-")? (url + "/" + dateStr) : (url + "/" + dateStr + "/");
		}
		String data = HttpClientUtil.doGetJson(url);
		return data;
	}
	
	/**
	 * 
	 * @Description: 获取指定日期的下一个节假日（如果在放假前有调休，也会返回）
	 *  http://timor.tech/api/holiday/next/$date?type=Y&week=Y
	 *  @params $date: 指定日期的字符串，格式 ‘2018-02-23’。可以省略，则默认服务器的当前时间。
		@params type: 是否返回日期类型，默认不返回。可选值：’Y’ 返回，’N’ 不返回。
		@params week: 节假日是否包含周末，默认不包含。可选值：’Y’ 包含周末，’N’ 不包含。
		@return json: 返回指定日期的下一个最近的节假日，如果在放假之前要调休，则会一起返回调休的信息。如果包含周末，则节日和周末冲突时，以节日为优先级。 比如特殊情况：周六是调休，则holiday返回周日，workday返回周六。其它情况以此类推，holiday永远会返回下一个节日。
	 * @auth：uwank171 
	 * @date: Feb 19, 2021 3:28:47 PM
	 * @param dateStr
	 * @return           
	 *
	 */
	public static String getHolidayNext(String dateStr) {
		String url = baseUrl + "/next";
		if (StringUtils.isNotBlank(dateStr)) {
			url = url + "/" + dateStr;
		}
		String data = HttpClientUtil.doGetJson(url);
		return data;
	}
	
	/**
	 * 
	 * @Description: 获取指定日期的下一个工作日（工作日包含正常工作日、调休）不包含当天
	 * http://timor.tech/api/holiday/workday/next/$date
	 * 	@params $data: 指定日期的字符串，格式 ‘2020-01-20’。可以省略，则默认服务器的当前时间。
		@return json: 返回指定日期的下一个最近的工作日。工作日包含正常工作日、调休，不包含当天。
	 * @auth：uwank171 
	 * @date: Feb 19, 2021 3:32:33 PM
	 * @param dateStr
	 * @return           
	 *
	 */
	public static String getHolidayWorkDayNext(String dateStr) {
		String url = baseUrl + "/workday/next";
		if (StringUtils.isNotBlank(dateStr)) {
			url = url + "/" + dateStr;
		}
		String data = HttpClientUtil.doGetJson(url);
		return data;
	}
	
	
	// 测试
	public static void main(String[] args) {
		// 获取指定日期的节假日信息
		String holidayInfo = getHolidayInfo("2021-02-21");
//		
//		// 批量查询指定日期节假日信息
//		String dateStr = "d=2021-02-19&d=2021-02-20"; 
//		String data = getHolidayBatch(dateStr);
//		
//		// 获取整年/整月包含周末的数据
//		String holidayYear1 = getHolidayYear("2021");
//		String holidayYear2 = getHolidayYear("2021-02");
//		
//		// 获取指定日期的下一个节假日（如果在放假前有调休，也会返回）
//		String holidayNext = getHolidayNext("2021-02-20");
//		
//		// 获取指定日期的下一个工作日（工作日包含正常工作日、调休）不包含当天
//		String holidayWorkDayNext = getHolidayWorkDayNext("2020-02-20");
//
		System.out.println("holidayInfo: " + holidayInfo + "\n");
//		System.out.println("data: " + data + "\n");
//		System.out.println("holidayYear1: " + holidayYear1 + "\n");
//		System.out.println("holidayYear2: " + holidayYear2 + "\n");
//		System.out.println("holidayNext: " + holidayNext + "\n");
//		System.out.println("holidayWorkDayNext: " + holidayWorkDayNext);
		
	}
	
	
	
	
	
}
