package me.navigation.database;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DatabaseOperations {

	
	Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    String url, username, password;
    
    
    public DatabaseOperations(String url, String username, String password)
    {
    	this.url=url;
    	this.username = username;
    	this.password = password;
    }
    
    
	@SuppressWarnings("deprecation")
	public int insertIntoDatabase(String filename) throws IOException, SQLException, ParseException
	{
		//database related block
		con = DriverManager.getConnection(url, username, password);
		//st = con.createStatement();
		
		String sql;
		PreparedStatement prep=null;
		String line;
		Date dt;
		String[] lineArr;
		File f = new File(filename);
		FileInputStream fStream = new FileInputStream(f);
		DataInputStream in = new DataInputStream(fStream);
		BufferedReader buff = new BufferedReader(new InputStreamReader(in));
		while((line=buff.readLine())!=null)
		{
			lineArr = line.split(",");
			
			dt = new Date(lineArr[0]);
			//Date d = new java.sql.Date(dt.getTime());
//			java.text.SimpleDateFormat sdf = 
//			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			
//			dt = sdf.parse(lineArr[0]);
//			String currentTime = sdf.format(dt);
			String currentTime= new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dt);
			
			
			//sql="Insert into uvReadings(Time,Latitude, Longitude) values('"+currentTime+"',"+(float)Double.parseDouble(lineArr[1])+","+(float)Double.parseDouble(lineArr[2])+")";
			sql="INSERT INTO uvReadings(Time,Latitude, Longitude, UVA1,UVA2,UVB1,UVB2) VALUES (?,?,?,?,?,?,?)";
			prep =con.prepareStatement(sql);
			
			
			//prep.setString(1,"testStreet");
			prep.setString(1, currentTime);
			prep.setFloat(2, new Float(lineArr[1]));
			prep.setFloat(3, new Float(lineArr[2]));
			prep.setFloat(4, new Float(lineArr[3]));
			prep.setFloat(5, new Float(lineArr[4]));
			prep.setFloat(6, new Float(lineArr[5]));
			prep.setFloat(7, new Float(lineArr[6]));
			//st.executeUpdate(sql);
			prep.executeUpdate();
		}
		prep.close();
		//st.close();
		con.close();
//		try{
//			
//			
//			prep  = con.prepareStatement(sql);
//			prep.setString(1, "Jay");
//			rs = prep.executeQuery();
//			while(rs.next())
//			{
//				System.out.println(rs.getString(1));
//			}
//			rs.close();
//			prep.close();
//			con.close();
//		}catch(Exception e)
//		{
//			System.out.println(e.getMessage());
//		}
//		
		return 0;
	}
}
