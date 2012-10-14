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

import me.navigation.shared.TimeReader;


public class GPSLoggerReader {

	public static void main(String args[]) throws Exception
	{
		File f = new File("/Users/Aditya/Downloads/20121014134105/20121014134105.txt");
		System.out.println(f.canRead());
		FileReader read ;
		FileInputStream fStream = new FileInputStream(f);
		DataInputStream in = new DataInputStream(fStream);
		BufferedReader buff = new BufferedReader(new InputStreamReader(in));
		
		String line;
		Date time= new Date();
		long timeLong;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(dateFormat.format(time));
		String timeString ;
		buff.readLine(); // waste the first line
		Date firstTime = TimeReader.getTime(buff.readLine());
		long firstTimeLong = (long)firstTime.getTime()/1000;
		while((line= buff.readLine())!=null)
		{
			time = TimeReader.getTime(line);
			timeLong = time.getTime()/1000;
			long diff = (timeLong-firstTimeLong);
			System.out.println(diff+"");
		}
		in.close();
		
	}
}
