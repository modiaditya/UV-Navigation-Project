import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;


public class SensorDataParser {

	File file = new File("/Users/Aditya/Documents/Development/uvnavigation/readingsAll_uva1.dat");
	//System.out.println(f.canRead());
	FileReader read ;
	FileInputStream fStream = new FileInputStream(file);
	DataInputStream in = new DataInputStream(fStream);
	BufferedReader buff = new BufferedReader(new InputStreamReader(in));
	
	
}
