package invoice_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class CustomerMasterDAOImpl implements CustomerMasterDAO{
	private Connection connection;
	public CustomerMasterDAOImpl(Connection connection) {
		this.connection=connection;
	}
	@Override
	public int insertCustomer(CustomerMasterDTO customerMasterDTO) {
		try {
		String query="insert into customermaster (CustomerNo,CustomerName,CustomerAddress,CustomerEmail,CustomerPhone) values(?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, customerMasterDTO.getCustomerno());
        ps.setString(2, customerMasterDTO.getCustomername());
        ps.setString(3, customerMasterDTO.getCustomeraddress());
        ps.setString(4, customerMasterDTO.getCustomeremail());
        ps.setString(5, customerMasterDTO.getCustomerphone());
        ps.execute();
        connection.commit();
		}catch(Exception e) {e.printStackTrace();}
		return 0;
	}

	@Override
	public int deleteCustomer(int custno) {
		CustomerMasterDTO customerobj=new CustomerMasterDTO();
		String query="delete from customermaster where CustomerNo=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,custno);
			ps.execute();
			connection.commit();
		}catch(Exception e) {}
		return 0;
	}

	@Override
	public int updateCustomer(CustomerMasterDTO customerMasterDTO) {
		CustomerMasterDTO customerobj=new CustomerMasterDTO();
		String query="update customermaster set CustomerName=?,CustomerAddress=?,CustomerPhone=?,CustomerEmail=? where CustomerNo=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, customerMasterDTO.getCustomername());
			ps.setString(2, customerMasterDTO.getCustomeraddress());
			ps.setString(3, customerMasterDTO.getCustomerphone());
			ps.setString(4, customerMasterDTO.getCustomeremail());
			ps.setInt(5, customerMasterDTO.getCustomerno());
			System.out.println(ps);
			ps.executeUpdate();
			connection.commit();
		}catch(Exception e) {e.printStackTrace();}
		return 0;
	}

	@Override
	public CustomerMasterDTO getCustomerMaster(int custno) {

		CustomerMasterDTO customerobj=new CustomerMasterDTO();
		String query="select * from customermaster where CustomerNo=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,custno);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				customerobj.setCustomerno(rs.getInt("CustomerNo"));
				customerobj.setCustomername(rs.getString("CustomerName"));
				customerobj.setCustomeremail(rs.getString("CustomerEmail"));
				customerobj.setCustomeraddress(rs.getString("CustomerAddress"));
				customerobj.setCustomerphone(rs.getString("CustomerPhone"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerobj;
	}

	@Override
	public Set<CustomerMasterDTO> getCustomerMasterAll() {
		Set<CustomerMasterDTO> customerdetails=new HashSet<CustomerMasterDTO>();
		Statement stmt;
		try {
			CustomerMasterDTO customerobj;
			stmt = connection.createStatement();
		String query="select * from customermaster";
		ResultSet rs=stmt.executeQuery(query);
		while(rs.next()) {
			customerobj=new CustomerMasterDTO();
			customerobj.setCustomerno(rs.getInt("CustomerNo"));
			customerobj.setCustomername(rs.getString("CustomerName"));
			customerobj.setCustomeremail(rs.getString("CustomerEmail"));
			customerobj.setCustomeraddress(rs.getString("CustomerAddress"));
			customerobj.setCustomerphone(rs.getString("CustomerPhone"));
			customerdetails.add(customerobj);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerdetails;
	}
}
