package com.service;

import com.model.TotalDetails;

public interface ExportInvoiceService {
	public void createXML(TotalDetails totalDetails);
	public void XMLToPDF(int invno);
	public void XMLToExcel(int invno);
	public void XMLToMail(int invno);
	public void XMLToSMS(int invno);
	public TotalDetails getTotalDetails(int invno);
}
