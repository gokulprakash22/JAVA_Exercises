package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCDemo2 {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/ey","root","hello@123");
			Statement stmt=con.createStatement();
			String query="insert into users values(1,'Gokul','hellO@123',0)";
			boolean var=stmt.execute(query);
			System.out.println(var);
			String query2="insert into users values(2,'Mr.X','hellO@321',0)";
			boolean var2=stmt.execute(query2);
			System.out.println(var2);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}