import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import me.navigation.client.SensorReading;
import me.navigation.shared.GPSData;
import me.navigation.shared.TimeReader;


public class GPSLoggerReader {

	public static void main(String args[]) throws Exception
	{
		
//		HashMap<Integer,GPSData> h = new HashMap<Integer, GPSData>();
//		h=SensorReading.getGPSData("/Users/Aditya/Downloads/20121014134105/20121014134105.txt");
//		Iterator<Entry<Integer, GPSData>> iter = h.entrySet().iterator();
//		while(iter.hasNext())
//		{	
//			Entry<Integer, GPSData> pairs = iter.next();
//			System.out.println(pairs.getKey()+" "+ " "+pairs.getValue().getTime()+" "+pairs.getValue().getPosition().getLatitude());
//		}
//		
//		System.out.println(h.containsKey(4));
		System.out.println(SensorReading.getUVData("/Users/Aditya/Documents/Development/uvnavigation/readingsAll_uva1.dat"));
	}
}
