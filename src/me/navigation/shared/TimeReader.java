package me.navigation.shared;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeReader {

	static Date time= new Date();
	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static Date getTime(String timeString) throws ParseException
	{
		timeString = timeString.split(",")[0].replace('T',' ');
		timeString = timeString.substring(0,timeString.length()-1);
		//System.out.println("Time String"+timeString);
		return dateFormat.parse(timeString);
	}
}
