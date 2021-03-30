package model;

import model.invoice_db.TotalDetailsDTO;

public interface ExportBillDAO {
	public void createXML(int invno);
	public void createPDF(int invno,String invdate);
	public void createExcel(int invno,String invdate);
	public void sendMail(String name,String email,int invno);
	public void sendSMS(String phone,TotalDetailsDTO totaldetails);
}
