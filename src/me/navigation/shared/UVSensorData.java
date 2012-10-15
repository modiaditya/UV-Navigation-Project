package me.navigation.shared;

public class UVSensorData {
	
	private UVRange uvRange;
	private double uvIndex;
	
	public static UVRange getUVRange(String filename)
	{
		UVRange uvRange;
		filename  = filename.split("//")[filename.length()-1];
		filename = filename.substring(filename.indexOf('_'), filename.indexOf('.'));
		uvRange = UVRange.valueOf(filename);
		return uvRange;
	}
	
	public UVRange getUvRange() {
		return uvRange;
	}
	public void setUvRange(UVRange uvRange) {
		this.uvRange = uvRange;
	}
	public double getUvIndex() {
		return uvIndex;
	}
	public void setUvIndex(double uvIndex) {
		this.uvIndex = uvIndex;
	}


}
