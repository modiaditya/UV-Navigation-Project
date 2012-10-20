import me.navigation.shared.BoundingBox;
import me.navigation.shared.LatLong;


public class Main {

	public static void main(String args[])
	{
		
		
		// code for testing bounding box starts
		LatLong p1 = new LatLong(34.065359, -118.450409);
		//p1.setLatitude(34.064312);
		//p1.setLongitude(-118.451921);
		LatLong p2 = new LatLong(34.064993, -118.450356);
		//p2.setLatitude(34.064159);
		//p2.setLongitude(-118.452798);
		BoundingBox x = new BoundingBox(40);
		x.getBoundingBox(p1, p2);
		System.out.println(x.getMin().getLatitude()+","+x.getMin().getLongitude()+"\n"
				+x.getMax().getLatitude()+","+x.getMax().getLongitude());
		// code to test bounding box ends
		
	}
}
