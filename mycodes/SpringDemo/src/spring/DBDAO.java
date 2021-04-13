package spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class DBDAO {
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	JdbcTemplate jdbc;
	
	public void firstTest() {
		jdbc = new JdbcTemplate(dataSource);
		int rows = jdbc.queryForInt("select count(*) from users");
		System.out.println("No of rows in users...."+rows);
	}
	
	public void secondTest() {
		jdbc = new JdbcTemplate(dataSource);
		
		String name = jdbc.queryForObject("select uname from users where uid=?", new Object[] {1}, String.class);
		
		UserDTO user = jdbc.queryForObject("select * from users where uid=?", new Object[] {1}, new RowMapper<UserDTO>() {
			public UserDTO mapRow(ResultSet rs, int row) throws SQLException {
				UserDTO user = new UserDTO();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPass(rs.getString(3));
				user.setFlag(rs.getInt(4));
				return user;
			}
		});
		
		System.out.println(name);
		System.out.println(user);
	}
	
	public void thirdTest() {
		jdbc = new JdbcTemplate(dataSource);

//		List<String> users = jdbc.query("select uname from users", new RowMapper<String>() {
//			
//		});
	}
}
