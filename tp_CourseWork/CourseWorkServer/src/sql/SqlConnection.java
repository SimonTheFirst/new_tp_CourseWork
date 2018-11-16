package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SqlConnection 
{
	 String url; //database's url
	 String user; //Login from database
	 String password; //Password from database
	 Connection connect; 
	 Statement stmt;
	 
	 public SqlConnection()
	 {
		 url = "jdbc:mysql://localhost:3306/coursework?serverTimezone=Europe/Moscow";
		 user = "root";
		 password = "root123"; 
	 }
	 
	 // Get the insert query to database
	 public void insert(ArrayList<Lane> lane) throws ClassNotFoundException, SQLException
	 {	 
		 //Query text
		 String insertQuery = "INSERT INTO coursework.lane" + 
				 "(name,occupancy,intensity,speed,distance,date)VALUES";
		 try 
		 {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(url,user,password);
			stmt = connect.createStatement();
		 } 
		 catch (SQLException e) 
		 {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }	
		 for(int i=0;i<lane.size();i++)
		 {
			 insertQuery += lane.get(i).getInsertQuery();
			 if(i==lane.size()-1)
			 {
				 insertQuery += ";";
			 }
			 else 
			 {
				 insertQuery += ",";
			 }			 
		 }
		 try 
		 {
			stmt.executeUpdate(insertQuery);
		 } 
		 catch (SQLException e) 
		 {
			e.printStackTrace();
		 }
		 stmt.close();
		 connect.close();
	 }
}
