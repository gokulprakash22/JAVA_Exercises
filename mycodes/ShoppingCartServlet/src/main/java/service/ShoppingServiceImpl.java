package service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.invoice_db.CustomerMasterDAO;
import model.invoice_db.CustomerMasterDAOImpl;
import model.invoice_db.CustomerMasterDTO;
import model.invoice_db.InvoiceMasterDAO;
import model.invoice_db.InvoiceMasterDAOImpl;
import model.invoice_db.InvoiceMasterDTO;
import model.invoice_db.ItemDetailsDTO;
import model.invoice_db.ItemMasterDAO;
import model.invoice_db.ItemMasterDAOImpl;
import model.invoice_db.ItemMasterDTO;
import model.invoice_db.ItemTransactionMasterDAO;
import model.invoice_db.ItemTransactionMasterDAOImpl;
import model.invoice_db.ItemTransactionMasterDTO;
import model.invoice_db.TotalDetailsDTO;

public class ShoppingServiceImpl implements Cloneable,ShoppingService{
	Properties prop;
	public ShoppingServiceImpl(Properties prop) {
		this.prop=prop;
	}
	private static ShoppingServiceImpl shoppingServiceImpl;
	
	public static ShoppingServiceImpl getShoppingServiceImpl(Properties prop) {
		if(shoppingServiceImpl==null) {
			shoppingServiceImpl=new ShoppingServiceImpl(prop);
			return shoppingServiceImpl;
		}
		else {
			return shoppingServiceImpl.createClone();
		}
	}
	public ShoppingServiceImpl createClone() {
		try {
			return (ShoppingServiceImpl)super.clone();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Set<ItemMasterDTO> loadAllItems(String category) {
		Set<ItemMasterDTO> itemmasterdtoset=new HashSet<ItemMasterDTO>();
		ItemMasterDAO itemDao=ItemMasterDAOImpl.getItemMasterDAOImpl(prop);
		itemmasterdtoset=itemDao.getItemMasterAll(category);
		return itemmasterdtoset;
	}
	@Override
	public Set<ItemMasterDTO> buyItems(Set<ItemTransactionMasterDTO> itemtransactionmasterdtoset) {
		ItemTransactionMasterDAO itemtransactiondao=ItemTransactionMasterDAOImpl.getItemTransactionMasterDAOImpl(prop);
		Set<ItemMasterDTO> items=new HashSet<ItemMasterDTO>();
		for(ItemTransactionMasterDTO itemtransactionmasterdto:itemtransactionmasterdtoset) {
		itemtransactiondao.insertItem(itemtransactionmasterdto);
		ItemMasterDTO itemmasterdto=new ItemMasterDTO();
		ItemMasterDAO itemmasterdao=ItemMasterDAOImpl.getItemMasterDAOImpl(prop);
		itemmasterdto=itemmasterdao.getItemMaster(itemtransactionmasterdto.getItemno());
		items.add(itemmasterdto);
		}
		return items;
	}
	@Override
	public int getInvNo(int custno,String dateinstring) {
		InvoiceMasterDAO invoicemaster=InvoiceMasterDAOImpl.getInvoiceMasterDAOImpl(prop);
		InvoiceMasterDTO invmasterdto=new InvoiceMasterDTO();
		invmasterdto.setCustomerno(custno);
		invmasterdto.setInvdate(dateinstring);
		int invno=invoicemaster.insertInvoice(invmasterdto);
		return invno;
	}
	@Override
	public CustomerMasterDTO getCustomerDetails(int customerno) {
		CustomerMasterDAO customerdao=CustomerMasterDAOImpl.getCustomerMasterDAOImpl(prop);
		CustomerMasterDTO customerdto=new CustomerMasterDTO();
		customerdto=customerdao.getCustomerMaster(customerno);
		return customerdto;
	}
	@Override
	public Set<ItemDetailsDTO> getItemDetails(int invno) {
		ItemTransactionMasterDAO itemtransactionmasterdao=ItemTransactionMasterDAOImpl.getItemTransactionMasterDAOImpl(prop);
		Set<ItemDetailsDTO> itemdetails=new HashSet<ItemDetailsDTO>(); 
		itemdetails=itemtransactionmasterdao.getItemDetails(invno);
		return itemdetails;
	}
	
	@Override
	public String getInvdate(int invno) {
		InvoiceMasterDAO invoicemasterdao=InvoiceMasterDAOImpl.getInvoiceMasterDAOImpl(prop);
		InvoiceMasterDTO inv=invoicemasterdao.getInvoiceMaster(invno);
		String date = inv.getInvdate();
		return date;
	}

}