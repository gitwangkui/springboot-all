package com.redmaple.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description: 日期工具类
 * @author: uwank171 
 * @date: 2020年10月13日 下午1:08:20 
 *
 */
public class DateUtils {

    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final String Y_M_D = "yyyy-MM-dd";
    public static final String YXMD = "yyyy/MM/dd";
    public static final String DEFAULT = "yyyyMMdd";
    public static final String Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";
    public static final String YXMXDHMS = "yyyy/MM/dd HH:mm:ss";

    /**
     * 系统当前时间：年-月-日
     * @return
     */
    public static String now() {
        return LocalDate.now().toString();
    }
    
    /**
     * 系统当前时间：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String nowTime() {
    	LocalDateTime now = LocalDateTime.now();
		String nowTime = now.format(DateTimeFormatter.ofPattern(Y_M_D_H_M_S));
    	return nowTime;
    }
    
    /**
     * 系统当前时间：yyyy/MM/dd HH:mm:ss
     * @return
     */
    public static String nowXTime() {
    	LocalDateTime now = LocalDateTime.now();
		String nowTime = now.format(DateTimeFormatter.ofPattern(YXMXDHMS));
    	return nowTime;
    }
    
    /**
     * 系统当前北京时间：yyyy/MM/dd HH:mm:ss  
     * 时区：Asia/Shanghai
     * @return
     */
    public static String nowZoneXTime() {
    	// 设置北京时区
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YXMXDHMS);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String nowTime = simpleDateFormat.format(new Date());
    	return nowTime;
    }

    /**
     * 系统当前时间：年-月-日
     * @return
     */
    public static String currentMonthFirstDay() {
        return LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).toString();
    }
    
    // 获取当天凌晨 2020-11-21 00:00:00
    public static String toDayTime() {
    	LocalDateTime now = LocalDateTime.now();
		String nowTime = now.format(DateTimeFormatter.ofPattern(Y_M_D)) + " 00:00:00";
        return nowTime;
    }
    // 获取明天凌晨 2020-11-22 00:00:00
    public static String tomorrowTime() {
    	LocalDateTime now = LocalDateTime.now();
		String nowTime = now.plusDays(1).format(DateTimeFormatter.ofPattern(Y_M_D)) + " 00:00:00";
        return nowTime;
    }
    
    /**
     * String 类型转localDate
     *
     * @param date
     * @return
     */
    public static LocalDate stringToLocald(String date, long l) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(Y_M_D));
        return localDate.plusMonths(l);
    }
    /**
     * Date转换为格式化时间
     *
     * @param date    date
     * @param pattern 格式
     * @return
     */
    public static String format(Date date, String pattern) {
        return format(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()), pattern);
    }

    public static String format(String date, String pattern, String toPattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return format(sdf.parse(date), toPattern);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Date转换为格式化时间
     *
     * @param date -> String (Y_M_D)
     * @return
     */
    public static String format(Date date) {
        return format(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()), Y_M_D);
    }


    /**
     * @param date
     * @param pattern
     * @return
     */
    public static LocalDateTime toLocalTime(Date date, String pattern) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
    /**
     * localDateTime转换为格式化时间
     *
     * @param localDateTime localDateTime
     * @param pattern       格式
     * @return
     */
    public static String format(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

    /**
     * 当前时间类 localDate年-月-日
     * @return
     */
    public static LocalDate nowDate() {
        return LocalDate.now();
    }

    /**
     * 转data类型
     *
     * @param str
     * @return
     */
    public static Date stringToDate(String str) {
        try {
            if (ObjectUtils.isEmpty(str)) {
                return null;
            }
            if (str.indexOf("/") > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat(YXMD);
                return sdf.parse(str);
            } else if (str.indexOf("-") > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat(Y_M_D);
                return sdf.parse(str);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT);
                return sdf.parse(str);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isDate(String date) {
        try {
            LocalDate.parse(date, new DateTimeFormatterBuilder()
                    .appendPattern(Y_M_D)
                    .parseStrict().toFormatter());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据时间戳获取日期时间
     * @param timeStamp
     * @param format  "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String getDateTimeByTimeStamp(Long timeStamp,String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

        Date date = new Date(timeStamp);

        String dateTime = simpleDateFormat.format(date);
        return dateTime;
    }

    public static List<String> minusMonths(String date, int months, String pattern) {
        List<String> list = new ArrayList<>();
        String[] strs = date.split("-");
        LocalDate localDate = LocalDate.of(Integer.parseInt(strs[0]),Integer.parseInt(strs[1]),1);
        list.add(localDate.format(DateTimeFormatter.ofPattern(pattern)));
        for (int i = 1; i < months; i++) {
            localDate = localDate.minusMonths(1);
            list.add(localDate.format(DateTimeFormatter.ofPattern(pattern)));
        }
        return list;
    }

    public static List<String> minusYears(String date, int years, String pattern) {
        List<String> list = new ArrayList<>();
        String[] strs = date.split("-");
        LocalDate localDate = LocalDate.of(Integer.parseInt(strs[0]),1,1);
        list.add(localDate.format(DateTimeFormatter.ofPattern(pattern)));
        for (int i = 1; i < years; i++) {
            localDate = localDate.minusYears(1);
            list.add(localDate.format(DateTimeFormatter.ofPattern(pattern)));
        }
        return list;
    }

    public static String lastMonths(String date) {
        String[] strs = date.split("-");
        LocalDate localDate = LocalDate.of(Integer.parseInt(strs[0]),Integer.parseInt(strs[1]),1);
        localDate = localDate.minusMonths(1);
        return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
    }
    
}