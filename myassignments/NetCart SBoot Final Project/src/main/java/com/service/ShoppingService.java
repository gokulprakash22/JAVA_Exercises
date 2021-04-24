package com.service;

import java.util.HashMap;
import java.util.List;
import com.model.Invoice;
import com.model.InvoiceWrapper;
import com.model.Items;



public interface ShoppingService {
	
	public int getInvoiceNo(Invoice invoice);
	public List<Invoice> getInvoiceByInvDate(String startdate,String enddate);
	public List<Invoice> getInvoiceByCustomerNo(int custno);
	public HashMap<Items,Integer> checkoutDetails(HashMap<Integer, Integer> hm);
	public Invoice createInvoice(HashMap<Integer,Integer> hm,int custno);
	public void getInvoiceMapByInvNo(int invno); 
	public InvoiceWrapper createInvoiceWrapperbyInvno(int invno);
	public InvoiceWrapper getInvoicewrapper();
	public void setInvoicewrapper(InvoiceWrapper invoicewrapper);
}
