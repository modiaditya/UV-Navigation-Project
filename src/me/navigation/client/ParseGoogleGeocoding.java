package me.navigation.client;

import java.net.SocketTimeoutException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import me.navigation.server.HttpSender;
import me.navigation.shared.LatLong;

public class ParseGoogleGeocoding {

	public static String getStreetName(LatLong point) throws Exception
	{
		String endpoint = "http://maps.googleapis.com/maps/api/geocode/json";
		String parameters = "latlng="+point.getLatitude()+","+point.getLongitude()+"&sensor=false";
		return parseReverseGeoCodingApi(HttpSender.sendGetRequest(endpoint, parameters));
		
	}
	
	public static String parseReverseGeoCodingApi(String jsonLine)
	{
		JsonElement jElement = new JsonParser().parse(jsonLine);
		JsonObject jObject = jElement.getAsJsonObject();
		JsonArray resultsArray = jObject.getAsJsonArray("results");
		return resultsArray.get(0).getAsJsonObject().getAsJsonArray("address_components").get(1).getAsJsonObject().get("long_name").toString() ;
		
	}
}
