package model.invoice_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class ItemTransactionMasterDAOImpl implements ItemTransactionMasterDAO,Cloneable{
	private static ItemTransactionMasterDAOImpl itemtransactionmasterdaoimpl;
	private Properties prop;
	private ItemTransactionMasterDAOImpl(Properties prop) {
		this.prop=prop;
	}
	
	synchronized public static ItemTransactionMasterDAOImpl getItemTransactionMasterDAOImpl(Properties prop) {
		
		if(itemtransactionmasterdaoimpl==null) {
			itemtransactionmasterdaoimpl=new ItemTransactionMasterDAOImpl(prop);
			return itemtransactionmasterdaoimpl;
		}
		else {
			return itemtransactionmasterdaoimpl.createClone();
		}
	}
	public ItemTransactionMasterDAOImpl createClone() {
		try {
			return (ItemTransactionMasterDAOImpl)super.clone();
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public int insertItem(ItemTransactionMasterDTO itemTransactionMasterDTO) {
		try {
			Connection connection=DBUtility.getConnection(prop);
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
			Connection connection=DBUtility.getConnection(prop);
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
			Connection connection=DBUtility.getConnection(prop);
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
			Connection connection=DBUtility.getConnection(prop);
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
			Connection connection=DBUtility.getConnection(prop);
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
			Connection connection=DBUtility.getConnection(prop);
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
	
	@Override
	public Set<ItemDetailsDTO> getItemDetails(int invno) {
		try {
			Set<ItemDetailsDTO> itemdetailsset=new HashSet<ItemDetailsDTO>();
			String query="select itemtransactionmaster.itemno,itemqty,itemdescription,itemprice,itemunit from itemtransactionmaster,itemmaster where itemmaster.itemno in(SELECT itemno from itemmaster where itemtransactionmaster.itemno=itemmaster.itemno and invno=?)";
			Connection con=DBUtility.getConnection(prop);
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, invno);
			ResultSet rs=ps.executeQuery();

			System.out.println(ps);
			while(rs.next()) {
				ItemDetailsDTO itemdetails=new ItemDetailsDTO();
				itemdetails.setItemno(rs.getInt("itemno"));
				itemdetails.setItemqty(rs.getInt("itemqty"));
				itemdetails.setItemdescription(rs.getString("itemdescription"));
				itemdetails.setItemprice(rs.getInt("itemprice"));
				itemdetails.setItemunit(rs.getString("itemunit"));
				itemdetailsset.add(itemdetails);
			}
			DBUtility.closeConnection(null);
			return itemdetailsset;
		}catch(Exception e) {
			DBUtility.closeConnection(e);
			return null;
		}
	}
}
