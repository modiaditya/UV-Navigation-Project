import java.net.SocketTimeoutException;

import me.navigation.client.ParseYourNavigationData;
import me.navigation.server.API_Parser;
import me.navigation.server.HttpSender;
import me.navigation.shared.LatLong;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * @author Aditya
 * TEMP class used to test the parsers
 */
public class ParserFirstTry {

	public static String parse(String jsonLine) {
	    JsonElement jelement = new JsonParser().parse(jsonLine);
	    JsonObject  jobject = jelement.getAsJsonObject();
	    jobject = jobject.getAsJsonObject("data");
	    JsonArray jarray = jobject.getAsJsonArray("translations");
	    jobject = jarray.get(0).getAsJsonObject();
	    String result = jobject.get("translatedText").toString();
	    return result;
	}
	
	public static String parseGoogleMapsJson(String jsonLine){
		
		JsonElement jelement = new JsonParser().parse(jsonLine);
		JsonObject  jobject = jelement.getAsJsonObject();
		JsonArray jarray = jobject.getAsJsonArray("routes");
		return jarray.size()+"";
	}
	
	public static void main(String args[])
	{
		
		
		//String jsonLine = "{\"data\": { \"translations\": [ {\"translatedText\": \"Hello world\"}]}}";
		//System.out.println(ParserFirstTry.parse(jsonLine));
		boolean testingGoogleApi = false;
		boolean testingYourNavigation = true;
		if(testingGoogleApi)
		{
			String endpoint = "http://maps.googleapis.com/maps/api/directions/json";
		
			String parameters = "origin=34.066712,-118.452626&destination=34.059797,-118.447691&sensor=false&mode=walking&alternatives=true";
			try 
			{
				System.out.println(ParserFirstTry.parseGoogleMapsJson(HttpSender.sendGetRequest(endpoint, parameters)));
			
				LatLong point = new LatLong(34.065918, -118.451802);
				System.out.println(API_Parser.getStreetName(point));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		
		if(testingYourNavigation)
		{
			LatLong source = new LatLong(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
			LatLong destination = new LatLong(Double.parseDouble(args[2]), Double.parseDouble(args[3]));
			String endpoint = "http://www.yournavigation.org/api/1.0/gosmore.php";
			String parameters = "format=geojson&flat="+source.getLatitude()+"&flon="+source.getLongitude()+"&tlat="+destination.getLatitude()+"&tlon="+destination.getLongitude()+"&v=foot";
			
			try
			{
				System.out.println(ParseYourNavigationData.getAllCoOrdinations(endpoint, parameters));
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			
		}
		
	}
}
