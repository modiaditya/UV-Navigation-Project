package me.navigation.shared;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeReader {

	static Date time= new Date();
	static final int TIME_ADJUST = 7; 
	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@SuppressWarnings("deprecation")
	public static Date getTime(String timeString) throws ParseException
	{
		timeString = timeString.split(",")[0].replace('T',' ');
		timeString = timeString.substring(0,timeString.length()-1);
		time = dateFormat.parse(timeString);
		time.setHours(time.getHours()-TIME_ADJUST);		
		//System.out.println("Time String"+timeString);
		return time;
	}
}
