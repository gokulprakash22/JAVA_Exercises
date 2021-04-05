package model.invoice_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class ItemMasterDAOImpl implements ItemMasterDAO,Cloneable{
	private static ItemMasterDAOImpl itemmasterdaoimpl;
	private Properties prop;
	private ItemMasterDAOImpl(Properties prop) {
		this.prop=prop;
	}
	
	synchronized public static ItemMasterDAOImpl getItemMasterDAOImpl(Properties prop) {
		
		if(itemmasterdaoimpl==null) {
			itemmasterdaoimpl=new ItemMasterDAOImpl(prop);
			return itemmasterdaoimpl;
		}
		else {
			return itemmasterdaoimpl.createClone();
		}
	}
	public ItemMasterDAOImpl createClone() {
		try {
			return (ItemMasterDAOImpl)super.clone();
		}catch(Exception e) {
			return null;
		}
	}
	
	@Override
	public int insertItemDetails(ItemMasterDTO itemMasterDTO) {
		ResultSet rs;
        int ans=0;
		try {
			Connection connection=DBUtility.getConnection(prop);
			String query="insert into itemmaster (itemdescription,itemprice,itemunit,itemcategory,itemimage) values(?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        ps.setString(1, itemMasterDTO.getItemdescription());
	        ps.setInt(2, itemMasterDTO.getItemprice());
	        ps.setString(3, itemMasterDTO.getItemunit());
	        ps.setString(4, itemMasterDTO.getItemcategory());
	        ps.setString(5, itemMasterDTO.getItemimage());
	        ps.execute();
	        rs =ps.getGeneratedKeys();
	        if (rs.next()) {
	        	ans=rs.getInt(1);
			}
	        connection.commit();
			}catch(Exception e) {e.printStackTrace();}
			return ans;
	}

	@Override
	public int deleteItemDetails(int itemno) {
		ItemMasterDTO itemobj=new ItemMasterDTO();
		String query="delete from itemmaster where itemno=?";
		try {
			Connection connection=DBUtility.getConnection(prop);
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,itemno);
			ps.execute();
			connection.commit();
		}catch(Exception e) {}
		return 0;
	}

	@Override
	public int updateItemDetails(ItemMasterDTO itemMasterDTO) {
		String query="update itemmaster set itemdescription=?,itemprice=?,itemunit=?,itemcategory=?,itemimage=? where itemno=?";
		try {
			Connection connection=DBUtility.getConnection(prop);
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, itemMasterDTO.getItemdescription());
			ps.setInt(2, itemMasterDTO.getItemprice());
			ps.setString(3, itemMasterDTO.getItemunit());
			ps.setString(4, itemMasterDTO.getItemcategory());
			ps.setString(5, itemMasterDTO.getItemimage());
			ps.setInt(6, itemMasterDTO.getItemno());
			System.out.println(ps);
			ps.executeUpdate();
			connection.commit();
		}catch(Exception e) {e.printStackTrace();}
		return 0;
	}

	@Override
	public ItemMasterDTO getItemMaster(int itemno) {
		ItemMasterDTO itemobj=new ItemMasterDTO();
		String query="select * from itemmaster where itemno=?";
		try {
			Connection connection=DBUtility.getConnection(prop);
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,itemno);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				itemobj.setItemno(rs.getInt("itemno"));
				itemobj.setItemdescription(rs.getString("itemdescription"));
				itemobj.setItemprice(rs.getInt("itemprice"));
				itemobj.setItemunit(rs.getString("itemunit"));
				itemobj.setItemcategory(rs.getString("itemcategory"));
				itemobj.setItemimage(rs.getString("itemimage"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itemobj;
	
	}

	@Override
	public Set<ItemMasterDTO> getItemMasterAll() {
		Set<ItemMasterDTO>itemdetails=new HashSet<ItemMasterDTO>();
		Statement stmt;
		try {
			Connection connection=DBUtility.getConnection(prop);
			ItemMasterDTO itemobj;
			stmt = connection.createStatement();
		String query="select * from itemmaster";
		ResultSet rs=stmt.executeQuery(query);
		while(rs.next()) {
			itemobj=new ItemMasterDTO();
			itemobj.setItemno(rs.getInt("itemno"));
			itemobj.setItemdescription(rs.getString("itemdescription"));
			itemobj.setItemprice(rs.getInt("itemprice"));
			itemobj.setItemunit(rs.getString("itemunit"));
			itemobj.setItemcategory(rs.getString("itemcategory"));
			itemobj.setItemimage(rs.getString("itemimage"));
			itemdetails.add(itemobj);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itemdetails;
	}
	
	@Override
	public Set<ItemMasterDTO> getItemMasterAll(String category) {
		Set<ItemMasterDTO>itemdetails=new HashSet<ItemMasterDTO>();

		Statement stmt;
		try {
			Connection con=DBUtility.getConnection(prop);
			ItemMasterDTO itemobj;
			stmt = con.createStatement();
		String query="select * from itemmaster where itemcategory=?";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1, category);
		ResultSet rs=ps.executeQuery();
//		System.out.println(rs);
		while(rs.next()) {
			itemobj=new ItemMasterDTO();
			itemobj.setItemno(rs.getInt("itemno"));
			itemobj.setItemdescription(rs.getString("itemdescription"));
			itemobj.setItemprice(rs.getInt("itemprice"));
			itemobj.setItemunit(rs.getString("itemunit"));
			itemobj.setItemcategory(rs.getString("itemcategory"));
			itemobj.setItemimage(rs.getString("itemimage"));
			itemdetails.add(itemobj);
		}
			DBUtility.closeConnection(null);
			return itemdetails;
		}catch(Exception e) {
			DBUtility.closeConnection(e);
			e.printStackTrace();
			return null;
		}
	
	}
}
