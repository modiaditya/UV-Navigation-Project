package me.navigation.client;

import java.net.SocketTimeoutException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import me.navigation.server.HttpSender;


/**
 * @author Aditya
 * Sample class to parse YourNavigationData.
 * Just a proof of concept
 */
public class ParseYourNavigationData {
	
	public static String getAllCoOrdinations(String url, String parameters) throws Exception
	{
		String jsonLine = HttpSender.sendGetRequest(url, parameters);
		
		JsonElement jElement = new JsonParser().parse(jsonLine);
		JsonObject jObject = jElement.getAsJsonObject();
		JsonArray jArray = jObject.getAsJsonArray("coordinates");
		String rr = "";
		for(int i=0;i<jArray.size();i++)
		{
			rr = rr + jArray.get(i).toString().replace('[', ' ');
			rr=rr.replace(']', '\n');
			
		}
		return rr;
	}
	public static String getNoOfSegments(String url, String parameters) throws Exception
	{
		int noOfSegments;
		String jsonLine = HttpSender.sendGetRequest(url, parameters);
		
		JsonElement jElement = new JsonParser().parse(jsonLine);
		JsonObject jObject = jElement.getAsJsonObject();
		JsonArray jArray = jObject.getAsJsonArray("coordinates");
		
		
		return jArray.size()+"";
		
	}

}
