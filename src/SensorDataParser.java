import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class SensorDataParser {

	public static void main(String argsp[]) throws Exception
	{
	
		File f = new File("/Users/Aditya/Documents/Development/uvnavigation/readingsAll_uva1.dat");
		FileInputStream fStream = new FileInputStream(f);
		DataInputStream in = new DataInputStream(fStream);
		BufferedReader buff = new BufferedReader(new InputStreamReader(in));
		String line;
		String seconds, uvIndex;
		while((line=buff.readLine())!=null)
		{
			seconds = line.split(" ")[0];
			uvIndex = line.split(" ")[1];
			System.out.println((int)Double.parseDouble(seconds)+" "+uvIndex);
		}
	}
	
}
