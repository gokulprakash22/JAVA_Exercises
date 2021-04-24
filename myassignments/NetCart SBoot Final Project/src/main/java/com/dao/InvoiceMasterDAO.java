package com.dao;

import java.util.List;


import com.model.Invoice;
public interface InvoiceMasterDAO {
	public int insertInvoice(Invoice invMasterDTO);
	public int deleteInvoice(int invno);
	public int updateInvoice(Invoice invMasterDTO);
	public byte[] getInvoiceMapByInvNo(int invno);
	public Invoice getInvoiceMaster(int invo);
	public List<Invoice> getInvoiceMasterAll();
	public List<Invoice> getInvoiceByCustomerNo(int custno);
	public List<Invoice> getInvoiceByInvDate(String startdate,String enddate);
	public String getDate(int invno);
	public int getCustno(int invno);
}

