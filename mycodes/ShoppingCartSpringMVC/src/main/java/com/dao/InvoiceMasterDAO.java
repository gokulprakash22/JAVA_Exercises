package com.dao;

import com.model.InvoiceMaster;
import com.model.TotalDetails;

public interface InvoiceMasterDAO {
	public InvoiceMaster createInvoice(InvoiceMaster invoiceMaster);
	public void updateInvoice(InvoiceMaster invoiceMaster);
	public InvoiceMaster getInvoiceMaster(int invid);
	public TotalDetails getTotalDetails(int invid);
}
