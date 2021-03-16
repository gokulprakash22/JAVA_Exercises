package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Types;

public class JDBCDemo5 {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/ey","root","hello@123");
			//delimiter /
			//create procedure selectproc(IN uid INT,OUT uname VARCHAR(30)
			//begin
			//select username into uname from users where userid = uid;
			//end;
			///
			String SQL = "{call selectproc (?, ?, ?, ?)}";
			CallableStatement cstmt = con.prepareCall (SQL);
			cstmt.setInt(1, 1);
			cstmt.registerOutParameter(2, Types.VARCHAR);
			cstmt.registerOutParameter(3, Types.VARCHAR);
			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.execute();
			System.out.println("username"+" "+"password"+" "+"flag");
			System.out.println(cstmt.getString(2)+"\t"+cstmt.getString(3)+"\t"+cstmt.getInt(4));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}