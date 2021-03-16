package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCDemo4 {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/ey","root","hello@123");
			PreparedStatement ps=con.prepareStatement("select * from users where username = ?");
			ps.setString(1,"Mr.X");
			ResultSet rs = ps.executeQuery();
			System.out.println("userid"+" "+"username"+" "+"password"+" "+"flag");
			while(rs.next()) {
				System.out.println(rs.getInt("userid")+"\t"+rs.getString("username")+"\t"+rs.getString("password")+"\t"+rs.getInt("flag"));
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}