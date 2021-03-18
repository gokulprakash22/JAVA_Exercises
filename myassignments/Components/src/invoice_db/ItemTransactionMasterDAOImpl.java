package invoice_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class ItemTransactionMasterDAOImpl implements ItemTransactionMasterDAO{
	private Connection connection;
	public ItemTransactionMasterDAOImpl(Connection connection) {
		this.connection=connection;
	}

	@Override
	public int insertItem(ItemTransactionMasterDTO itemTransactionMasterDTO) {
		try {
			String query="insert into itemtransactionmaster (invno,itemno,itemqty) values(?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setInt(1, itemTransactionMasterDTO.getInvno());
	        ps.setInt(2, itemTransactionMasterDTO.getItemno());
	        ps.setInt(3, itemTransactionMasterDTO.getItemqty());
	        ps.execute();
	        
	        connection.commit();
			}catch(Exception e) {e.printStackTrace();}
			return 0;
		}

	@Override
	public int deleteItem(int invno, int itemno) {
		ItemTransactionMasterDTO itemtransactionobj=new ItemTransactionMasterDTO();
		String query="delete from itemtransactionmaster where invno=? and itemno=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,invno);
			ps.setInt(2,itemno);
			ps.execute();
			connection.commit();
		}catch(Exception e) {}
		return 0;
	}

	@Override
	public int updateItem(ItemTransactionMasterDTO itemTransactionMasterDTO) {
		ItemTransactionMasterDTO itemtransactionrobj=new ItemTransactionMasterDTO();
		String query="update itemtransactionmaster set itemqty=? where invno=? and itemno=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,itemTransactionMasterDTO.getItemqty());
			ps.setInt(2, itemTransactionMasterDTO.getInvno());
			ps.setInt(3, itemTransactionMasterDTO.getItemno());
			System.out.println(ps);
			ps.executeUpdate();
			connection.commit();
		}catch(Exception e) {e.printStackTrace();}
		return 0;
	}

	@Override
	public ItemTransactionMasterDTO getItemTransactionMaster(int invno, int itemno) {
		ItemTransactionMasterDTO itemtransactionobj=new ItemTransactionMasterDTO();
		String query="select * from itemtransactionmaster where invno=? and itemno=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,invno);
			ps.setInt(2,itemno);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				itemtransactionobj.setInvno(rs.getInt("invno"));
				itemtransactionobj.setItemno(rs.getInt("itemno"));
				itemtransactionobj.setItemqty(rs.getInt("itemqty"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itemtransactionobj;
	}

	@Override
	public Set<ItemTransactionMasterDTO> getItemTransactionMasterAll() {
		Set<ItemTransactionMasterDTO> itemtransactiondetails=new HashSet<ItemTransactionMasterDTO>();
		Statement stmt;
		try {
			ItemTransactionMasterDTO itemtransactionobj;
			stmt = connection.createStatement();
		String query="select * from itemtransactionmaster";
		ResultSet rs=stmt.executeQuery(query);
		while(rs.next()) {
			itemtransactionobj=new ItemTransactionMasterDTO();
			itemtransactionobj.setInvno(rs.getInt("invno"));
			itemtransactionobj.setItemno(rs.getInt("itemno"));
			itemtransactionobj.setItemqty(rs.getInt("itemqty"));
			itemtransactiondetails.add(itemtransactionobj);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itemtransactiondetails;
	}
	@Override
	public Set<ItemTransactionMasterDTO> getItemTransactionMasterAllByInvno(int invoiceno) {
		Set<ItemTransactionMasterDTO> itemtransactiondetails=new HashSet<ItemTransactionMasterDTO>();
		Statement stmt;
		String query="select * from itemtransactionmaster where invno=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, invoiceno);
			ItemTransactionMasterDTO itemtransactionobj;
//			stmt = connection.createStatement();
		
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			itemtransactionobj=new ItemTransactionMasterDTO();
			itemtransactionobj.setInvno(rs.getInt("invno"));
			itemtransactionobj.setItemno(rs.getInt("itemno"));
			itemtransactionobj.setItemqty(rs.getInt("itemqty"));
			itemtransactiondetails.add(itemtransactionobj);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itemtransactiondetails;
	}
}
