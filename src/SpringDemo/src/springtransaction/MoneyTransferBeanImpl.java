package springtransaction;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class MoneyTransferBeanImpl implements MoneyTransferBean {
	private DataSource datasource;
	
	public DataSource getDatasource() {
		return datasource;
	}
	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}
	
	@Override
	public void credit(int accid, int amt) throws Exception {
		JdbcTemplate jdbc = new JdbcTemplate(datasource);
		Integer balance = jdbc.queryForObject("select balance from accounts where accid=?", new Object[] {accid}, Integer.class);
		int new_balance = balance+amt;
		
		NamedParameterJdbcTemplate njdbc = new NamedParameterJdbcTemplate(datasource);
		MapSqlParameterSource mps = new MapSqlParameterSource();
		mps.addValue("balance", new_balance);
		mps.addValue("accid", accid);
//		try {
//			Thread.sleep(5000);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		njdbc.update("update accounts set balance=:balance where accid=:accid", mps);
	}
	
	@Override
	public void debit(int accid, int amt) throws Exception {
		JdbcTemplate jdbc = new JdbcTemplate(datasource);
		Integer balance = jdbc.queryForObject("select balance from accounts where accid=?", new Object[] {accid}, Integer.class);
		
		if(balance < amt) {
			throw new InsufficientBalanceException("The user does not have enough balance...");
		}
		
		int new_balance = balance - amt;
		
		NamedParameterJdbcTemplate njdbc = new NamedParameterJdbcTemplate(datasource);
		MapSqlParameterSource mps = new MapSqlParameterSource();
		mps.addValue("balance", new_balance);
		mps.addValue("accid", accid);
		
		njdbc.update("update accounts set balance=:balance where accid=:accid", mps);
	}
}
