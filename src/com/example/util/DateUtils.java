package com.example.util;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class DateUtils {
	/**
	 * ��ȡ��ǰʱ��
	 * 
	 * @param date
	 * @return
	 */
	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * ��ʱ����Ϊ23ʱ59����59��
	 * 
	 * @param date
	 * @return
	 */
	public static Date setFullPassDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * ��ʱ�����2Сʱ
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFallBack2Hour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 2);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * ��ʱ�侫ȷ��Сʱ
	 * 
	 * @param date
	 * @return
	 */
	public static Date getTimeHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * ��ȡ����ʱ����������
	 * 
	 * @param date
	 * @return
	 */
	public static long getDiffDays(Date startDate, Date endDate) {
		long difftime = endDate.getTime() - startDate.getTime();
		return difftime / (24L * 60L * 60L * 1000L);
	}
	

	/**
	 * �������ڻ�ȡ������ʼʱ��
	 * 
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartDateOfCurrentDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getStartYesterday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * �������ڻ�ȡ��һ����ʼʱ��
	 * 
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartDateOfNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * �������ڵ�ǰ����˳��һ�ܺ����ʼʱ��
	 * 
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartDateOfNextSevenDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * �������ڵ�ǰ����˳��һ�ܺ����ʼʱ��
	 * 
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartDateOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * �������ڵ�ǰ����˳��һ�º����ʼʱ��
	 * 
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartDateOfNextMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/*
	 * ��װһ��ֻ�ܵ�ʱ������
	 */
	public static List<Date> getStaticByDateDateArea(Date date) {
		List<Date> dates = new ArrayList<Date>();
		Date startdate = getStartDateOfCurrentDay(date);
		Date nextday = getStartDateOfNextDay(date);
		int step = 2;
		dates.add(startdate);
		for (int i = 1; i < 12; i++) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startdate);
			calendar.add(Calendar.HOUR_OF_DAY, i * step);
			dates.add(calendar.getTime());
		}
		dates.add(nextday);
		return dates;
	}

	/*
	 * ��װһ��֮��ʱ������
	 */
	public static List<Date> getStaticByWeekDateArea(Date date) {
		List<Date> dates = new ArrayList<Date>();
		Date startdate = getStartDateOfCurrentDay(date);
		Date nextday = getStartDateOfNextSevenDay(date);
		dates.add(startdate);
		for (int i = 1; i < 7; i++) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startdate);
			calendar.add(Calendar.DAY_OF_MONTH, i);
			dates.add(calendar.getTime());
		}
		dates.add(nextday);
		return dates;
	}

	/*
	 * ��װһ��֮��ʱ������List<String>
	 */
	public static List<String> getStaticByWeekLabel(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
		List<String> dates = new ArrayList<String>();
		Date startdate = getStartDateOfCurrentDay(date);
		Date nextday = getStartDateOfNextSevenDay(date);
		dates.add(dateFormat.format(startdate));
		for (int i = 1; i < 7; i++) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startdate);
			calendar.add(Calendar.DAY_OF_MONTH, i);
			dates.add(dateFormat.format(calendar.getTime()));
		}
		return dates;
	}

	/*
	 * ��װһ��֮��ʱ������
	 */
	public static List<Date> getStaticByMonthDateArea(Date date) {
		List<Date> dates = new ArrayList<Date>();
		Date startdate = getStartDateOfMonth(date);
		Date nextday = getStartDateOfNextMonth(date);
		long daydiff = getDiffDays(startdate, nextday);
		dates.add(startdate);
		for (int i = 1; i < daydiff; i++) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startdate);
			calendar.add(Calendar.DAY_OF_MONTH, i);
			dates.add(calendar.getTime());
		}
		dates.add(nextday);
		return dates;
	}
	
	/*
	 *��װһ��ʱ��֮�ڵ�ʱ�������죩 
	 */
	public static List<Date> getStaticBySE(Date startDate,Date endDate)
	{
		List<Date> dates = new ArrayList<Date>();
		
		long daydiff = getDiffDays(startDate, endDate);
		dates.add(startDate);
		for (int i = 1; i < daydiff; i++) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			calendar.add(Calendar.DAY_OF_MONTH, i);
			dates.add(calendar.getTime());
		}
		dates.add(endDate);
		return dates;
	}

	/*
	 * ��װһ��֮��ʱ������
	 */
	public static List<String> getStaticByMonthLabel(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
		List<String> dates = new ArrayList<String>();
		Date startdate = getStartDateOfMonth(date);
		Date nextday = getStartDateOfNextMonth(date);
		long daydiff = getDiffDays(startdate, nextday);
		dates.add(dateFormat.format(startdate));
		for (int i = 1; i < daydiff; i++) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startdate);
			calendar.add(Calendar.DAY_OF_MONTH, i);
			dates.add(dateFormat.format(calendar.getTime()));
		}
		return dates;
	}

	public static String formatDate(String format, Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

}
