import java.net.SocketTimeoutException;

import me.navigation.server.API_Parser;
import me.navigation.server.BoundingBox;
import me.navigation.server.HttpSender;
import me.navigation.shared.LatLong;
import me.navigation.shared.Routes;


public class Main {

	public static void main(String args[]) throws SocketTimeoutException
	{
		
		
		boolean testBoundingBox=true;
		
		if(testBoundingBox)
		{
			// code for testing bounding box starts
			LatLong p1 = new LatLong(34.059584,-118.449195);
			//p1.setLatitude(34.064312);
			//p1.setLongitude(-118.451921);
			LatLong p2 = new LatLong(34.05897,-118.44868);
			//p2.setLatitude(34.064159);
			//p2.setLongitude(-118.452798);
			BoundingBox x = new BoundingBox(20);
			x.getBoundingBox(p1, p2);
			System.out.println(x.getMin().getLatitude()+","+x.getMin().getLongitude()+"\n"
					+x.getMax().getLatitude()+","+x.getMax().getLongitude());
			// code to test bounding box ends

		}
		
		
		LatLong source = new LatLong(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
		LatLong destination = new LatLong(Double.parseDouble(args[2]), Double.parseDouble(args[3]));
		
		String endpoint = "http://maps.googleapis.com/maps/api/directions/json";
		
		String requestParameters = "sensor=false&mode=walking&alternatives=true&origin="+source.getLatitude()+","+source.getLongitude()+"&destination="+destination.getLatitude()+","+destination.getLongitude();
		String googleMapsResult=  HttpSender.sendGetRequest(endpoint, requestParameters);
		
		Routes[] allRoutes;
		
		int numberOfRoutes = API_Parser.getNumberOfRoutes(googleMapsResult);
		allRoutes = new Routes[numberOfRoutes];
		int index = 0;
		allRoutes[index] = new Routes();
		allRoutes[index].setGoogleAPIJson(API_Parser.getRouteInformation(googleMapsResult, index));
		allRoutes[index].initialize();
		System.out.println(allRoutes[0]);
		for(int i=0;i<allRoutes[index].getSteps().length;i++)
		{
			System.out.println(allRoutes[index].getSteps()[i]);
		}
		
	}
}
