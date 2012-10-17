package me.navigation.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;

import me.navigation.shared.GPSData;
import me.navigation.shared.LatLong;
import me.navigation.shared.TimeReader;
import me.navigation.shared.SensorUVData;
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
	public static HashMap<Integer, SensorUVData> getUVData(String filename) throws Exception
	{
		HashMap<Integer, SensorUVData> h = new HashMap<Integer, SensorUVData>();
		String uvRange = SensorUVData.getUVRange(filename);
		
		SensorUVData data ;
		
		File f = new File(filename);
		FileInputStream fStream = new FileInputStream(f);
		DataInputStream in = new DataInputStream(fStream);
		BufferedReader buff = new BufferedReader(new InputStreamReader(in));
		String line;
		Integer seconds;
		double uvIndex;
		String[] lineArr;
		while((line=buff.readLine())!=null)
		{ 	data=  new SensorUVData();
			lineArr = line.split(" ");
			seconds = (int)Double.parseDouble(lineArr[0]);
			uvIndex = Double.parseDouble(lineArr[1]);
			//logic to set uvIndex
			if(uvRange.equals("uva1"))
				data.setUva1(uvIndex);
			else if(uvRange.equals("uva2"))
				data.setUva2(uvIndex);
			else if(uvRange.equals("uvb1"))
				data.setUvb1(uvIndex);
			else if(uvRange.equals("uvb2"))
				data.setUvb2(uvIndex);
			h.put(seconds, data);
		}
		
		return h;
	}
}
