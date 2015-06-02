package com.cloudwick.maven.databaseAccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
//		InputStream inputStream = App.class.getClassLoader().getResourceAsStream("/db.properties");
//		Properties props = new Properties();
////		props.load(inputStream);
//		File file = new File("/src/main/resources/db.properties");
//		FileInputStream fileInput = new FileInputStream(file);
//		Properties properties = new Properties();
//		ClassLoader loader = Thread.currentThread().getContextClassLoader();     
//		InputStream stream = loader.getResourceAsStream("/src/main/resources/db.properties");
//		 File file = new File("C:\\Users\\KINNU\\Documents\\Cloudwick\\HadoopDev\\SourceCode\\HadoopDevDemo\\src\\main\\resources\\database.properties");
//         FileInputStream fileInput = new FileInputStream(file);
         
//		Properties properties = new Properties();
//		properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("properties/exceptions.properties")));
	   
//	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
//	   static final String DB_URL = "jdbc:mysql://localhost/mysqldb";
//
//	   //  Database credentials
//	   static final String USER = "root";
//	   static final String PASS = "root";
	   
	   public static void main(String[] args) {
		   
		   Properties properties = new Properties();
		   File file = new File("C:\\Users\\shrey\\Desktop\\db.properties");
		   FileInputStream fileInput;
		try {
			fileInput = new FileInputStream(file);
			properties.load(fileInput);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           
	   final String JDBC_DRIVER = properties.getProperty("driver");  
	   final String DB_URL = properties.getProperty("url");

	   //  Database credentials
	   final String USER = properties.getProperty("username");
	   final String PASS = properties.getProperty("password");
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName(JDBC_DRIVER);

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT userid, fname, lname FROM userdetails";
	      ResultSet rs = stmt.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         int id  = rs.getInt("userid");
	         String first = rs.getString("fname");
	         String last = rs.getString("lname");

	         //Display values
	         System.out.print("ID: " + id);
	         System.out.print(", First: " + first);
	         System.out.println(", Last: " + last);
	      }
	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	}
}
