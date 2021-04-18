package com.service;

import java.util.HashMap;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.time.LocalDate;

import javax.sql.rowset.serial.SerialBlob;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.InvoiceMasterDAO;
import com.dao.ItemMasterDAO;
import com.dao.ItemTransactionMasterDAO;
import com.model.CustomerMaster;
import com.model.InvoiceMaster;
import com.model.ItemMaster;
import com.model.ItemTransactionMaster;
import com.model.TotalDetails;

@Service
@Transactional
public class ShoppingServiceImpl implements ShoppingService{
	@Autowired
	private ItemMasterDAO itemMasterDAO;
	
	@Autowired
	private InvoiceMasterDAO invoiceMasterDAO;
	
	@Autowired
	private ItemTransactionMasterDAO itemTransactionMasterDAO;
	
	
	public final ItemMasterDAO getItemMasterDAO() {
		return itemMasterDAO;
	}
	public final void setItemMasterDAO(ItemMasterDAO itemMasterDAO) {
		this.itemMasterDAO = itemMasterDAO;
	}
	public final InvoiceMasterDAO getInvoiceMasterDAO() {
		return invoiceMasterDAO;
	}
	public final void setInvoiceMasterDAO(InvoiceMasterDAO invoiceMasterDAO) {
		this.invoiceMasterDAO = invoiceMasterDAO;
	}
	public final ItemTransactionMasterDAO getItemTransactionMasterDAO() {
		return itemTransactionMasterDAO;
	}
	public final void setItemTransactionMasterDAO(ItemTransactionMasterDAO itemTransactionMasterDAO) {
		this.itemTransactionMasterDAO = itemTransactionMasterDAO;
	}
	
	@Override
	public List<ItemMaster> getItemsByCategory(String category) {
		return itemMasterDAO.getItemsByCategory(category);
	}
	@Override
	public TotalDetails createInvoice(CustomerMaster customerMaster, HashMap<Integer, Integer> hm) {
		TotalDetails totalDetails = new TotalDetails();
		InvoiceMaster invoiceMaster = new InvoiceMaster();
		invoiceMaster.setCustomerId(customerMaster.getCustomerid());
		invoiceMaster.setInvoiceDate(LocalDate.now());
		invoiceMaster = invoiceMasterDAO.createInvoice(invoiceMaster);
		totalDetails.setCustomerMaster(customerMaster);
		totalDetails.setInvid(invoiceMaster.getInvid());
		totalDetails.setInvoiceDate(invoiceMaster.getInvoiceDate());
		
		HashMap<ItemMaster,Integer> item = new HashMap<ItemMaster,Integer>();
		ItemTransactionMaster itemTransactionMaster;
		ItemMaster itemMaster;
		int itemStock;
		for (HashMap.Entry<Integer,Integer> entry : hm.entrySet()) {
			itemTransactionMaster = new ItemTransactionMaster();
			itemTransactionMaster.setInvoiceId(invoiceMaster.getInvid());
			itemTransactionMaster.setItemId(entry.getKey());
			itemTransactionMaster.setItemQuantity(entry.getValue());
			itemTransactionMasterDAO.createItemTransaction(itemTransactionMaster);
			itemMaster = itemMasterDAO.getItemMaster(entry.getKey());
			itemStock = itemMaster.getItemStock();
			itemMaster.setItemStock(itemStock-entry.getValue());
			itemMasterDAO.updateItem(itemMaster);
			item.put(itemMasterDAO.getItemMaster(entry.getKey()),entry.getValue());
		}
		totalDetails.setItemList(item);
		int total=0;
		for (HashMap.Entry<ItemMaster,Integer> entry : item.entrySet()) {
			total+=(entry.getKey().getItemPrice())*entry.getValue();
		}
		totalDetails.setTotal(total);
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(totalDetails);
			out.flush();
			byte[] bytes = bos.toByteArray();
			Blob blob = new SerialBlob(bytes);
			invoiceMaster.setTotalDetails(blob);
			invoiceMasterDAO.updateInvoice(invoiceMaster);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return totalDetails;
	}
	
}
