package me.navigation.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import me.navigation.shared.GPSData;
import me.navigation.shared.LatLong;
import me.navigation.shared.TimeReader;
import me.navigation.shared.UVData;
import me.navigation.shared.UVSensorData;

public class SensorReading {

	public static HashMap<Integer, GPSData> getGPSData(String filename) throws Exception
	{
		HashMap<Integer, GPSData> h = new HashMap<Integer, GPSData>();
		
		File f = new File(filename);
		FileInputStream fStream = new FileInputStream(f);
		DataInputStream in = new DataInputStream(fStream);
		BufferedReader buff = new BufferedReader(new InputStreamReader(in));
		
		String line;
		Date time= new Date();
		long timeLong;
		//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//System.out.println(dateFormat.format(time));
		buff.readLine(); // waste the first line
		Date firstTime = TimeReader.getTime(buff.readLine());
		long firstTimeLong = (long)firstTime.getTime()/1000;
		long diff;
		String data[];
		GPSData gpsReadings;
		while((line= buff.readLine())!=null)
		{
			
			gpsReadings = new GPSData();
			time = TimeReader.getTime(line);
			data = line.split(",");
			LatLong position = new LatLong(Double.parseDouble(data[1]),Double.parseDouble(data[2]));
			gpsReadings.setPosition(position);
			gpsReadings.setTime(time);
			timeLong = time.getTime()/1000;
			diff = (timeLong-firstTimeLong);
			h.put((int) diff,gpsReadings);
		}
		in.close();
		
		return h;
		
	}
	public static String getUVData(String filename) throws Exception
	{
		HashMap<Integer, UVData> h = new HashMap<Integer, UVData>();
		
		
		return UVSensorData.getUVRange(filename)+"";
	}
}
