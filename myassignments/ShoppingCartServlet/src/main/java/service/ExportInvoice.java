package service;

import model.invoice_db.TotalDetailsDTO;

public interface ExportInvoice {
	public void createXML(int invno);
	public void XMLToPDF(int invno);
	public void XMLToExcel(int invno);
	public void XMLToMail(int invno);
	public void XMLToSMS(int invno);
}
