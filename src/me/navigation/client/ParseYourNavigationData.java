package me.navigation.client;

import java.net.SocketTimeoutException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import me.navigation.server.HttpSender;

public class ParseYourNavigationData {
	
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
