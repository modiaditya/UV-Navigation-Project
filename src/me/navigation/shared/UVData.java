package me.navigation.shared;

import java.util.Date;

public class UVData {

	private UVRange uvRange;
	private LatLong point;
	private double uvIndex;
	private Date time;
	private String streetName;
	
	public LatLong getPoint() {
		return point;
	}
	public void setPoint(LatLong point) {
		this.point = point;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
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
	public UVRange getUvRange() {
		return uvRange;
	}
	public void setUvRange(UVRange uvRange) {
		this.uvRange = uvRange;
	}
	
}
