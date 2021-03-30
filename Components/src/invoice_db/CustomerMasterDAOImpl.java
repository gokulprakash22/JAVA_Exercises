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
		ResultSet rs;
        int ans=0;
		try {
			String query="insert into customermaster (CustomerName,CustomerAddress,CustomerEmail,CustomerPhone,CustomerPassword,Customerflag) values(?,?,?,?,?,0)";
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        ps.setString(1, customerMasterDTO.getCustomername());
	        ps.setString(2, customerMasterDTO.getCustomeraddress());
	        ps.setString(3, customerMasterDTO.getCustomeremail());
	        ps.setString(4, customerMasterDTO.getCustomerphone());
	        ps.setString(5, customerMasterDTO.getCustomerpassword());
	        ps.execute();
	        rs =ps.getGeneratedKeys();
	        if (rs.next()) {
	        	ans=rs.getInt(1);
			}
	        connection.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
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
		String query="update customermaster set CustomerName=?,CustomerAddress=?,CustomerPhone=?,CustomerEmail=?,CustomerPassword=?,CustomerFlag=? where CustomerNo=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, customerMasterDTO.getCustomername());
			ps.setString(2, customerMasterDTO.getCustomeraddress());
			ps.setString(3, customerMasterDTO.getCustomerphone());
			ps.setString(4, customerMasterDTO.getCustomeremail());
			ps.setString(5, customerMasterDTO.getCustomerpassword());
			ps.setInt(6, customerMasterDTO.getCustomerflag());
			ps.setInt(7, customerMasterDTO.getCustomerno());
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
				customerobj.setCustomerpassword(rs.getString("CustomerPassword"));
				customerobj.setCustomerflag(rs.getInt("CustomerFlag"));
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
			customerobj.setCustomerpassword(rs.getString("CustomerPassword"));
			customerobj.setCustomerflag(rs.getInt("CustomerFlag"));
			customerdetails.add(customerobj);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerdetails;
	}
}
