package com.service;

import com.model.InvoiceWrapper;

public interface ExportService {
	public void createPDF(InvoiceWrapper invoicewrapper);
	public void createExcel(InvoiceWrapper invoicewrapper);
	public void sendMail(InvoiceWrapper invoicewrapper);
	public void sendSMS(InvoiceWrapper invoicewrapper);
}
