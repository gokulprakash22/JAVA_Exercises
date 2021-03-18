package invoice_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
public class InvoiceMasterDAOImpl implements InvoiceMasterDAO {
	private Connection connection;
	public InvoiceMasterDAOImpl(Connection connection) {
		this.connection=connection;
	}
	@Override
	public int insertInvoice(InvoiceMasterDTO invMasterDTO) {
		ResultSet rs;
        int ans=0;
		try {
			String query="insert into invoicemaster (invdate,customerno) values(?,?)";
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//			ps.setInt(1, invMasterDTO.getInvno());
			ps.setDate(1, java.sql.Date.valueOf(invMasterDTO.getInvdate()));
	        ps.setInt(2, invMasterDTO.getCustomerno());
//	        System.out.println(ps);
	        ps.execute();
	        rs =ps.getGeneratedKeys();
	        if (rs.next()) {
//				System.out.println("Auto Generated Primary Key " + rs.getInt(1));
	        	ans=rs.getInt(1);
			}
	        connection.commit();
			}catch(Exception e) {e.printStackTrace();}
			return ans;
		}

	@Override
	public int deleteInvoice(int invno) {
		InvoiceMasterDTO invoiceobj=new InvoiceMasterDTO();
		String query="delete from invoicemaster where invno=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,invno);
			ps.execute();
			connection.commit();
		}catch(Exception e) {e.printStackTrace();}
		return 0;
	}
	@Override
	public int updateInvoice(InvoiceMasterDTO invMasterDTO) {
		InvoiceMasterDTO invoiceobj=new InvoiceMasterDTO();
		String query="update invoicemaster set invdate=?,customerno=? where invno=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, invMasterDTO.getInvdate());
			ps.setInt(2, invMasterDTO.getCustomerno());
			ps.setInt(3, invMasterDTO.getInvno());
			System.out.println(ps);
			ps.executeUpdate();
			connection.commit();
		}catch(Exception e) {e.printStackTrace();}
		return 0;
	}
	@Override
	public InvoiceMasterDTO getInvoiceMaster(int invno) {
		InvoiceMasterDTO invoiceobj=new InvoiceMasterDTO();
		String query="select * from invoicemaster where invno=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,invno);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				invoiceobj.setCustomerno(rs.getInt("customerno"));
				invoiceobj.setInvdate(String.valueOf(rs.getDate("invdate")));
				invoiceobj.setInvno(rs.getInt("invno"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return invoiceobj;
	}
	@Override
	public Set<InvoiceMasterDTO> getInvoiceMasterAll() {
		Set<InvoiceMasterDTO> invoicedetails=new HashSet<InvoiceMasterDTO>();
		Statement stmt;
		try {
			InvoiceMasterDTO invoiceobj;
			stmt = connection.createStatement();
		String query="select * from invoicemaster";
		ResultSet rs=stmt.executeQuery(query);
		while(rs.next()) {
			invoiceobj=new InvoiceMasterDTO();
			invoiceobj.setCustomerno(rs.getInt("customerno"));
			invoiceobj.setInvdate(String.valueOf(rs.getDate("invdate")));
			invoiceobj.setInvno(rs.getInt("invno"));
			invoicedetails.add(invoiceobj);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return invoicedetails;
	}
}