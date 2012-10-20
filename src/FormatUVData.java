import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;


/**
 * @author Aditya
 * This Class is used to format the UV data which is retuned by UCB univeristy
 * The dataset hence produced will be used to create the curve
 */
public class FormatUVData {

	long timeStamp;
	int numbers;
	double avg;

	// Function to round off the time  
	static Date getRoundedTime(Date n)
	{
		long minutes = n.getMinutes();
		
		if(minutes<15)
			n.setMinutes(0);
		else if(minutes<45)
			n.setMinutes(30);
		else 
		{
			n.setMinutes(0);
			n.setHours(n.getHours()+1);
		}
		
		return n;
	}
	
	public static void main(String args[]) throws Exception
	{
		File f = new File("/Users/Aditya/Documents/Development/uvnavigation/uvReading/CA01201210180_ind.csv");
		FileInputStream fStream = new FileInputStream(f);
		DataInputStream in = new DataInputStream(fStream);
		BufferedReader buff = new BufferedReader(new InputStreamReader(in));
		String line;
		String[] lineArr;
		double index;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm");
		Date d;
		HashMap<Long,Double> h = new HashMap<Long,Double>();
		buff.readLine();
		while((line=buff.readLine())!=null)
		{
			
			lineArr = line.split(",");
			//System.out.println(line);
			d = sdf.parse(lineArr[2]);
			//System.out.println(d+" "+lineArr[2]);
			if(!lineArr[lineArr.length-1].trim().isEmpty())
			{
				
				//System.out.println("String is "+lineArr[lineArr.length-1].trim().isEmpty());
				index = Double.parseDouble(lineArr[lineArr.length-1]);
				d.setHours(d.getHours()-8);
				//System.out.println(d);
				//System.out.println(d.getTime()/1000+"");
				h.put(d.getTime(), index);
			}
			
		}
		buff.close();
		in.close();
		fStream.close();
		
		//System.out.println(h.size()+"");
		// now that we have all the readings, we can round them off
		
		HashMap<Long,FormatUVData> roundedTimeHashMap = new HashMap<Long,FormatUVData>();
		
		Iterator<Entry<Long, Double>> iter = h.entrySet().iterator();
		long time;
		double uv ;
		FormatUVData uvDataObject;
		while(iter.hasNext())
		{
		
			Entry<Long, Double> e = iter.next();
			time = e.getKey();
			uv = e.getValue();
			d = new Date(time);
			d = FormatUVData.getRoundedTime(d);
			time = d.getTime();
			time/=1000;
			//System.out.println(d);
			uvDataObject = new FormatUVData();

			if(!roundedTimeHashMap.containsKey(time))
			{
				
				uvDataObject.avg = uv;
				uvDataObject.numbers = 1;
				uvDataObject.timeStamp = time; 
				roundedTimeHashMap.put(time,uvDataObject);
			}
			else
			{
				FormatUVData uvDataObject1 =  new FormatUVData();
				uvDataObject1 = roundedTimeHashMap.remove(time);
				uvDataObject1.avg = (uvDataObject.avg*uvDataObject.numbers + uv)/(uvDataObject.numbers+1);
				uvDataObject1.numbers = uvDataObject.numbers +1;
				uvDataObject1.timeStamp = time;
				roundedTimeHashMap.put(time, uvDataObject1);
				
			}
		}
		
		// print the data here
		Iterator<Entry<Long, FormatUVData>> iterMain  =  roundedTimeHashMap.entrySet().iterator();
		//System.out.println("------------------------------------");
		String outputFile = "/Users/Aditya/Documents/Development/uvnavigation/uvReading/CA01201210180_ind.txt";
		FileWriter fstream = new FileWriter(outputFile,true);
		BufferedWriter out = new BufferedWriter(fstream);
		while(iterMain.hasNext())
		{
			Entry<Long, FormatUVData> e = iterMain.next();
			out.write(e.getKey()+","+e.getValue().avg+"\n");
		}
		
		out.close();
		
		
//		
//		Calendar calendar;
//		Date n = new Date();
//		calendar=new GregorianCalendar();
//		calendar.set(Calendar.SECOND, 100);
//		System.out.println(calendar);
//		
//		
//		
		
//		
//		System.out.println(n);
		
	}
}