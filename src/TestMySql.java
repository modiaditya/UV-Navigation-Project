import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.navigation.database.DatabaseOperations;


/**
 * @author Aditya
 * TEMP Class to just test the SQL 
 */
public class TestMySql {
	
	
	
	static void normalSql(String url, String username, String password)
	{
		Connection con = null;
        Statement st = null;
        ResultSet rs = null;

		try
		{
			con = DriverManager.getConnection(url, username, password);
	        st = con.createStatement();
	        rs = st.executeQuery("select * from test");
	        //st.executeUpdate("Insert into test values ('Jay','Modi')");
	        while (rs.next()) {
	            System.out.println(rs.getString(1)+" "+rs.getString("password"));
	        }
	        
		}catch(Exception e)
		{
			
		}
		finally
		{
			try{
				
				 if (rs != null) {
	                 rs.close();
	             }
	             if (st != null) {
	                 st.close();
	             }
	             if (con != null) {
	                 con.close();
	             }
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		}
		
		
	}
	
	static void preparedStatementSelect(String url, String username, String password)
	{
		Connection con = null;
        Statement st = null;
        ResultSet rs = null;
		PreparedStatement prep;
		try{
			con = DriverManager.getConnection(url, username, password);
			String sql = "Select * from test where username like ?";
			prep  = con.prepareStatement(sql);
			prep.setString(1, "Jay");
			rs = prep.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getString(1));
			}
			rs.close();
			prep.close();
			con.close();
			
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	
    public static void main(String[] args) throws Exception{

       
        String url = "jdbc:mysql://localhost:3306/project";
        String username = "adityauv";
        String password = "uvnavigation";
        
        //TestMySql.preparedStatementSelect(url, username, password);
        DatabaseOperations db = new DatabaseOperations(url,username, password);
        db.insertIntoDatabase("/Users/Aditya/Documents/Development/uvnavigation/10-23/outputNew.txt");

    }
}