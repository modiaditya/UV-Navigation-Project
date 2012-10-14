package me.navigation.shared;

public class UVData {

	private LatLong point;
	private double uvIndex;
	private TimeSlot time;
	private String streetName;
	
	public LatLong getPoint() {
		return point;
	}
	public void setPoint(LatLong point) {
		this.point = point;
	}
	public TimeSlot getTime() {
		return time;
	}
	public void setTime(TimeSlot time) {
		this.time = time;
	}
	public double getUvIndex() {
		return uvIndex;
	}
	public void setUvIndex(double uvIndex) {
		this.uvIndex = uvIndex;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
}
