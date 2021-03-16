package jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

public class JDBCDemo1 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/ey","root","hello@123");
		System.out.println(con);
		DatabaseMetaData dbmd=con.getMetaData();  
		  
		System.out.println("Driver Name: "+dbmd.getDriverName());  
		System.out.println("Driver Version: "+dbmd.getDriverVersion());  
		System.out.println("UserName: "+dbmd.getUserName());  
		System.out.println("Database Product Name: "+dbmd.getDatabaseProductName());  
		System.out.println("Database Product Version: "+dbmd.getDatabaseProductVersion());  
		con.close();  
	}
}