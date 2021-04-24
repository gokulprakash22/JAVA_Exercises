package com.service;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.CustomerMasterDAO;
import com.dao.InvoiceMasterDAO;
import com.dao.ItemMasterDAO;
import com.model.Invoice;
import com.model.InvoiceWrapper;
import com.model.Items;
import java.sql.Date;  




@Service
@Transactional
public class ShoppingServiceImpl implements ShoppingService{

	@Autowired
	private InvoiceMasterDAO invoiceDao;
	@Autowired
	private CustomerMasterDAO customerDao;
	@Autowired
	private ItemMasterDAO itemDao;

	@Autowired
	private ItemService itemService;

	public InvoiceWrapper invoicewrapperdet=new InvoiceWrapper();


	@Override
	public List<Invoice> getInvoiceByCustomerNo(int custno) {
		return invoiceDao.getInvoiceByCustomerNo(custno);
	}
	@Override
	public List<Invoice> getInvoiceByInvDate(String startdate,String enddate) {
		System.out.println(startdate);
		return invoiceDao.getInvoiceByInvDate(startdate, enddate);
	}
	@Override
	public HashMap<Items,Integer> checkoutDetails(HashMap<Integer, Integer> hm){
		HashMap<Items,Integer> itemqtyhm=new HashMap<Items,Integer>();
		Iterator hmIterator = hm.entrySet().iterator();
		 while (hmIterator.hasNext()) {
	            Map.Entry mapElement = (Map.Entry)hmIterator.next();
	            int itemqty = ((int)mapElement.getValue());
	            int itemno = ((int)mapElement.getKey());
	            itemqtyhm.put(itemService.loadItembyItemno(itemno), itemqty);
	        }
		 return itemqtyhm;
	}
	@Override
	public Invoice createInvoice(HashMap<Integer, Integer> hm, int custno) {
		Invoice invoice=new Invoice();
		System.out.println(custno);
		HashMap<Items,Integer> itemqtyhm=new HashMap<Items,Integer>();
		invoice.setInvdate(new Date(System.currentTimeMillis()));
		invoice.setCustomerno(custno);
		Iterator hmIterator = hm.entrySet().iterator();
		 while (hmIterator.hasNext()) {
	            Map.Entry mapElement = (Map.Entry)hmIterator.next();
	            int itemqty = ((int)mapElement.getValue());
	            int itemno = ((int)mapElement.getKey());
	            itemqtyhm.put(itemService.loadItembyItemno(itemno), itemqty);
	            itemDao.decreaseStock(itemno, itemqty);
	        }
		 System.out.println(itemqtyhm);
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 try {
	     ObjectOutputStream oos = new ObjectOutputStream(baos);
	     oos.writeObject(itemqtyhm);
	     oos.flush();
	     byte[] mapAsBytes = baos.toByteArray();
		 invoice.setItemqtymap(mapAsBytes);
		 return invoice;
		 }catch(Exception e) {e.printStackTrace();};
		return invoice;
	}
	@Override
	public void getInvoiceMapByInvNo(int invno) {
		invoiceDao.getInvoiceMapByInvNo(invno); 
		
	}
	@Override
	public int getInvoiceNo(Invoice invoice) {
		return invoiceDao.insertInvoice(invoice);
	}
	@Override
	public InvoiceWrapper createInvoiceWrapperbyInvno(int invno) {
		try {
		InvoiceWrapper invoicewrapper=new InvoiceWrapper();
		Invoice invoice=invoiceDao.getInvoiceMaster(invno);
		byte[] itemmapinbytes =(byte[])invoice.getItemqtymap();
		ByteArrayInputStream baip = new ByteArrayInputStream(itemmapinbytes);
		ObjectInputStream ois = new ObjectInputStream(baip);
		HashMap<Items,Integer> itemqtymap = (HashMap<Items,Integer>) ois.readObject();
		invoice.setItemquantitymap(itemqtymap);
		invoicewrapper.setInvoice(invoice);
		invoicewrapper.setCustomer(customerDao.getCustomerMaster(invoice.getCustomerno()));
		return invoicewrapper;
		}catch(Exception e) {e.printStackTrace();return null;}
		
	}
	@Override
	public InvoiceWrapper getInvoicewrapper() {
		return invoicewrapperdet;
	}
	@Override
	public void setInvoicewrapper(InvoiceWrapper invoicewrapper) {
		this.invoicewrapperdet=invoicewrapper;
		
	}
	




	


}
