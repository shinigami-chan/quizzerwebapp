package com.tinesoft.quizzer.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
	
	public static Date dateFromString( String dateString, String datePattern)
	{
		SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
		
		Date date = null;
		try
		{
			date = formatter.parse(dateString);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		return date;
	}
	
	public static Date todayDate()
	{
		Calendar cal = Calendar.getInstance();
		Date today = new Date(cal.getTimeInMillis());
		
		return today;
	}
	
	public static Timestamp todayTimestamp()
	{
		Calendar cal = Calendar.getInstance();
		Timestamp today = new Timestamp(cal.getTimeInMillis());
		
		return today;
	}

}
