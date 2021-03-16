package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


public class JDBCDemo3 {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/ey","root","hello@123");
			Statement stmt=con.createStatement();
			String query="select * from users";
			boolean var=stmt.execute(query);
			System.out.println(var);
		
			ResultSet rs=stmt.executeQuery(query);
			System.out.println("userid"+" "+"username"+" "+"password"+" "+"flag");
			while(rs.next()) {
				System.out.println(rs.getInt("userid")+"\t"+rs.getString("username")+"\t"+rs.getString("password")+"\t"+rs.getInt("flag"));
			}
			
			rs=stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int noOfColumns = rsmd.getColumnCount();
			for(int i=1; i<=noOfColumns;i++) {
				System.out.print(rsmd.getColumnName(i)+" ");
			}
			System.out.println();
			while(rs.next()) {
				for(int i=1; i<=noOfColumns;i++) {
					System.out.print(rs.getString(i)+"\t");
				}
				System.out.println();
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}