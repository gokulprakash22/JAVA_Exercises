package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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
import com.twilio.Twilio;
import com.twilio.*;
import com.twilio.rest.api.v2010.account.*;
import com.twilio.type.PhoneNumber;

import model.invoice_db.CustomerMasterDAOImpl;
import model.invoice_db.CustomerMasterDTO;
import model.invoice_db.DBUtility;
import model.invoice_db.InvoiceMasterDAOImpl;
import model.invoice_db.InvoiceMasterDTO;
import model.invoice_db.ItemMasterDAOImpl;
import model.invoice_db.ItemMasterDTO;
import model.invoice_db.ItemTransactionMasterDAOImpl;
import model.invoice_db.ItemTransactionMasterDTO;
import model.invoice_db.ItemDetailsDTO;
import model.invoice_db.TotalDetailsDTO;

public class ExportBillDAOImpl implements ExportBillDAO,Cloneable{
	private static ExportBillDAOImpl filecreationdaoimpl;
	private Properties prop;
	private ExportBillDAOImpl(Properties prop) {
		this.prop=prop;
	}
	
	synchronized public static ExportBillDAOImpl getFileCreationDAOImpl(Properties prop) {
		
		if(filecreationdaoimpl==null) {
			filecreationdaoimpl=new ExportBillDAOImpl(prop);
			return filecreationdaoimpl;
		}
		else {
			return filecreationdaoimpl.createClone();
		}
	}
	public ExportBillDAOImpl createClone() {
		try {
			return (ExportBillDAOImpl)super.clone();
		}catch(Exception e) {
			return null;
		}
	}
	@Override
	public void createXML(int invno) {
		System.out.println("Creating XML");
		StringBuilder sb=new StringBuilder();
		InvoiceMasterDTO invoicedetails=new InvoiceMasterDTO();
		InvoiceMasterDAOImpl invoicemasterdaoimpl=InvoiceMasterDAOImpl.getInvoiceMasterDAOImpl(prop);
		invoicedetails=invoicemasterdaoimpl.getInvoiceMaster(invno);
		String date=invoicedetails.getInvdate();
		int custno=invoicedetails.getCustomerno();
		ItemTransactionMasterDAOImpl itemtransactionmasterdaoimpl=ItemTransactionMasterDAOImpl.getItemTransactionMasterDAOImpl(prop);
		Set<ItemTransactionMasterDTO> itemtransactiondetails=new HashSet<ItemTransactionMasterDTO>();
		itemtransactiondetails=itemtransactionmasterdaoimpl.getItemTransactionMasterAllByInvno(invno);
		CustomerMasterDAOImpl customermasterdaoimpl=CustomerMasterDAOImpl.getCustomerMasterDAOImpl(prop);
		CustomerMasterDTO customerobj=new CustomerMasterDTO();
		customerobj=customermasterdaoimpl.getCustomerMaster(custno);
		sb.append("<!DOCTYPE invoice SYSTEM \"invoice.dtd\">");
		sb.append("<invoice>");
		sb.append("<customer>");
		sb.append("<customerno>"+custno+"</customerno>");
		sb.append("<customername>"+customerobj.getCustomername()+"</customername>");
		sb.append("<customeraddress>"+customerobj.getCustomeraddress()+"</customeraddress>");
		sb.append("<customeremail>"+customerobj.getCustomeremail()+"</customeremail>");
		sb.append("<customerphone>"+customerobj.getCustomerphone()+"</customerphone>");
		sb.append("</customer>");
		sb.append("<items>");
		double sum=0;
		for (ItemTransactionMasterDTO i : itemtransactiondetails) {
			sb.append("<item>"); 
	        sb.append("<itemno>"+i.getItemno()+"</itemno>");       
	        ItemMasterDAOImpl itemmasterdaoimpl=ItemMasterDAOImpl.getItemMasterDAOImpl(prop);
	        ItemMasterDTO itemdetails=new ItemMasterDTO();
	        itemdetails=itemmasterdaoimpl.getItemMaster(i.getItemno());
	        sb.append("<itemdescription>"+itemdetails.getItemdescription()+"</itemdescription>");
	        sb.append("<itemunit>"+itemdetails.getItemunit()+"</itemunit>");
	        sb.append("<itemprice>"+itemdetails.getItemprice()+"</itemprice>");
	        sb.append("<itemqty>"+i.getItemqty()+"</itemqty>");
	        double total = itemdetails.getItemprice()*i.getItemqty();
	        sum+=total;
	        sb.append("<itemtotal>"+total+"</itemtotal>");
	        sb.append("</item>");
		  }
		sb.append("</items>");
		sb.append("<invdetails>");
		sb.append("<invno>"+invno+"</invno>");
		sb.append("<date>"+date+"</date>");
		sb.append("<total>"+sum+"</total>");
		sb.append("</invdetails>");
		sb.append("</invoice>");
	    try {
			File output = new File(invno+".xml");
			FileWriter fwriter = new FileWriter(output);
			fwriter.write(sb.toString());
			fwriter.flush();
			fwriter.close();
	    }
	    catch(Exception e) {
	    	e.printStackTrace();
	    }
	    System.out.println("XML Created Successfully");
		
	}

	@Override
	public void createPDF(int invno, String invdate) {
		try {
			String[] arr=new String[100];
			com.itextpdf.text.Document document=new com.itextpdf.text.Document();
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:/EYIntern/JAVA/workspace/ShoppingCartServlet/results/"+invno+".pdf"));
		    document.open();
		    Font boldheadFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
		    Font boldheadFont1 = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
		    Font boldheadFont2 = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
		    Paragraph parahead = new Paragraph("ABC Stores"+"\n",boldheadFont1);
		    Paragraph parahead1 = new Paragraph("123 Madison drive, Seattle,WA,7829Q"+"\n"+"www.abcstores.com"+"\n"+"990-120-4560"+"\n\n",boldheadFont2);
			Paragraph datepara=new Paragraph("Invoice Date : "+invdate);
			datepara.setAlignment(Paragraph.ALIGN_RIGHT);
		    DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse("D:/EYIntern/JAVA/workspace/ShoppingCartServlet/results/"+invno+".xml");
		    Element rootElement=doc.getDocumentElement();
		    PdfPTable table = new PdfPTable(6);
		    table.setWidthPercentage(100);
		    table.setSpacingBefore(10f); 
		    table.setSpacingAfter(10f); 
		    float[] columnWidths = {1f,1f,1f,1f,1f,1f};
		    table.setWidths(columnWidths);
		    PdfPCell cells[]=new PdfPCell[6];
		    Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		    cells[0] = new PdfPCell(new Paragraph("Item No",boldFont));
		    cells[1] = new PdfPCell(new Paragraph("Desription",boldFont));
		    cells[2] = new PdfPCell(new Paragraph("Price",boldFont));
		    cells[3] = new PdfPCell(new Paragraph("Quantity",boldFont));
		    cells[4] = new PdfPCell(new Paragraph("Unit",boldFont));
		    cells[5] = new PdfPCell(new Paragraph("Total",boldFont));
		    for(int i=0;i<6;i++)
			{
		    	cells[i].setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
		        table.addCell(cells[i]);
			}
			for(int i=0;i<rootElement.getChildNodes().getLength();i++) {
				if(i==0)
				{
				for(int j=0;j<rootElement.getChildNodes().item(i).getChildNodes().getLength();j++) {  
					arr[j]=rootElement.getChildNodes().item(i).getChildNodes().item(j).getFirstChild().getNodeValue();
				}
				}
				else
				{
					for(int j=0;j<rootElement.getChildNodes().item(i).getChildNodes().getLength();j++) {  
						for(int k=0;k<rootElement.getChildNodes().item(i).getChildNodes().item(j).getChildNodes().getLength();k++)
						{
							  cells[k] = new PdfPCell(new Paragraph(rootElement.getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getFirstChild().getNodeValue()));
						      cells[k].setBorderColor(BaseColor.BLACK);
						      cells[k].setPaddingLeft(10);
						      cells[k].setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
						      cells[k].setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
						}
						for(int k=0;k<rootElement.getChildNodes().item(i).getChildNodes().item(j).getChildNodes().getLength();k++)
						{
					        table.addCell(cells[k]);
						}
						}
				}
			}
		    Paragraph para1 = new Paragraph("BILL TO:\n"+"________________________"+"\n",boldheadFont);
		    Paragraph para2 = new Paragraph(arr[1]+"\n"+arr[2]+"\n"+arr[3]+"\n"+arr[4]+"\n\n");
		    document.add(parahead);
		    document.add(parahead1);
		    document.add(datepara);
		    document.add(para1);
		    document.add(para2);
		    document.add(table);
		    System.out.println("done");
		    document.close();
	 }catch(Exception e) {e.printStackTrace();}
	}

	@Override
	public void createExcel(int invno, String invdate) {
		try {
		String[] arr=new String[100];
		HSSFWorkbook wb = new HSSFWorkbook();   
		OutputStream fileOut = new FileOutputStream("D:/EYIntern/JAVA/workspace/ShoppingCartServlet/results/"+invno+".xls");   
		HSSFWorkbook workbook = new HSSFWorkbook();  
		HSSFSheet sheet = workbook.createSheet("Bill_Sheet");   
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setFontHeightInPoints((short)10);
		font.setBold(true);
		style.setFont(font);
		HSSFCellStyle style1 = workbook.createCellStyle();
		HSSFFont font1 = workbook.createFont();
		font1.setFontName(HSSFFont.FONT_ARIAL);
		font1.setFontHeightInPoints((short)14);
		font1.setBold(true);
		style1.setFont(font);
		sheet.addMergedRegion(new CellRangeAddress(0,0,2,4));
		HSSFRow rowhead = sheet.createRow((short)0);  
		rowhead.setRowStyle(style1);
		rowhead.createCell(2).setCellValue("ABC Stores");
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setValidating(true);
		sheet.addMergedRegion(new CellRangeAddress(1,1,1,2));
		sheet.addMergedRegion(new CellRangeAddress(1,1,3,4));
		sheet.addMergedRegion(new CellRangeAddress(2,2,1,2));
		sheet.addMergedRegion(new CellRangeAddress(2,2,3,4));
		sheet.addMergedRegion(new CellRangeAddress(3,3,1,2));
		sheet.addMergedRegion(new CellRangeAddress(3,3,3,4));
		sheet.addMergedRegion(new CellRangeAddress(4,4,1,2));
		sheet.addMergedRegion(new CellRangeAddress(4,4,3,4));
		sheet.addMergedRegion(new CellRangeAddress(5,5,1,2));
		sheet.addMergedRegion(new CellRangeAddress(5,5,3,4));
		sheet.addMergedRegion(new CellRangeAddress(6,6,1,2));
		sheet.addMergedRegion(new CellRangeAddress(6,6,3,4));
		DocumentBuilder db=dbf.newDocumentBuilder();
		Document doc=db.parse("D:/EYIntern/JAVA/workspace/ShoppingCartServlet/results/"+invno+".xml");
		Element rootElement=doc.getDocumentElement();
		HSSFRow row1 = sheet.createRow((short)(1));
		row1.createCell(1).setCellValue("Customer No");
		row1.createCell(3).setCellValue(rootElement.getChildNodes().item(0).getChildNodes().item(0).getFirstChild().getNodeValue());  
		HSSFRow row2 = sheet.createRow((short)(2));
		row2.createCell(3).setCellValue(rootElement.getChildNodes().item(0).getChildNodes().item(1).getFirstChild().getNodeValue());
		row2.createCell(1).setCellValue("Customer Name");
		HSSFRow row3 = sheet.createRow((short)(3));
		row3.createCell(3).setCellValue(rootElement.getChildNodes().item(0).getChildNodes().item(2).getFirstChild().getNodeValue());
		row3.createCell(1).setCellValue("Customer Address");
		HSSFRow row4 = sheet.createRow((short)(4));
		row4.createCell(3).setCellValue(rootElement.getChildNodes().item(0).getChildNodes().item(3).getFirstChild().getNodeValue()); 
		row4.createCell(1).setCellValue("Customer Email");
		HSSFRow row5 = sheet.createRow((short)(5));
		row5.createCell(3).setCellValue(rootElement.getChildNodes().item(0).getChildNodes().item(4).getFirstChild().getNodeValue()); 
		row5.createCell(1).setCellValue("Customer Phone");
		HSSFRow row6 = sheet.createRow((short)(6));
		row6.createCell(3).setCellValue(rootElement.getChildNodes().item(0).getChildNodes().item(5).getFirstChild().getNodeValue()); 
		row6.createCell(1).setCellValue("Invoice No");
		HSSFRow row0 = sheet.createRow((short)7);  
		row0.createCell(0).setCellValue("Item No");  
		row0.createCell(1).setCellValue("Description");  
		row0.createCell(2).setCellValue("Price");  
		row0.createCell(3).setCellValue("Quantity");  
		row0.createCell(4).setCellValue("Unit");
		row0.createCell(5).setCellValue("Total");
		for(int j = 0; j<=5; j++)
		row0.getCell(j).setCellStyle(style);
		for(int i=1;i<rootElement.getChildNodes().getLength();i++) {
			for(int j=0;j<rootElement.getChildNodes().item(i).getChildNodes().getLength();j++) {  
				HSSFRow row = sheet.createRow((short)(j+8));
				for(int k=0;k<rootElement.getChildNodes().item(i).getChildNodes().item(j).getChildNodes().getLength();k++)
				{
					row.createCell(k).setCellValue(rootElement.getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getFirstChild().getNodeValue());  
				}
			}
		}
		System.out.println("Excel Created Successfully");
		workbook.write(fileOut);
		wb.write(fileOut);   
		wb.close();
		fileOut.close();  
		}catch(Exception e) {}
		
	}

	@Override
	public void sendMail(String name,String email,int invno) {
		String host="smtp.gmail.com";
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES "+properties);
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		Session session=Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {				
				return new PasswordAuthentication("gokulprakash22@gmail.com", "opengOOgledog@123");
			}
		});
		session.setDebug(true);
		MimeMessage m = new MimeMessage(session);
		try {
		Message message = new MimeMessage(session); 
		message.setFrom(new InternetAddress("gokulprakash22@gmail.com")); 
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email)); 
		message.setSubject("Bill Receipt"); 

		BodyPart messageBodyPart = new MimeBodyPart(); 
		messageBodyPart.setText("Hi "+name+"\n"+"Here is your bill");
		MimeBodyPart attachmentPart = new MimeBodyPart();
		attachmentPart.attachFile(new File("D:/EYIntern/JAVA/workspace/ShoppingCartServlet/results/"+invno+".pdf"));
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		multipart.addBodyPart(attachmentPart);
		message.setContent(multipart);
		Transport.send(message);		
		System.out.println("Email Sucessfully Sent");
		}catch (Exception e) {
			e.printStackTrace();
		}
			
	}

	@Override
	public void sendSMS(String phone,TotalDetailsDTO totaldetails) {
		final String ACCOUNT_SID = "ACed9066982355c8c63b965bff60292263";
		final String AUTH_TOKEN = "58fa646ac392d5e12027d5c525977ace";
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		String to;
		String message="";
		to="+918946069629";
		message="Hello "+totaldetails.getCustomermasterdto().getCustomername()+"\n";
		StringBuilder sb=new StringBuilder();
		sb.append("Items:"+"\n");
		int i=1;
		for (ItemDetailsDTO id : totaldetails.getItemdetails()) {
			sb.append("Item "+i+" :"+"\n"); 
	       sb.append("Item No:"+id.getItemno()+"\n");       
	       sb.append("Item Description: "+id.getItemdescription()+"\n"
	       		+ "Item Price: "+id.getItemprice()+"\n"+"Item Quantity: "+id.getItemqty()+"\n"+"Item Unit: "+
	    		   id.getItemunit()+"\n"+"Item Total: "+id.getItemqty()*id.getItemprice());
	       sb.append("\n");
	       i++;
		}
		message=message+sb.toString();
		System.out.println(to);
		System.out.println(message);
		com.twilio.rest.api.v2010.account.Message messageobj = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber(to),
		        new PhoneNumber("+19544407776"), 
		        message).create();
			    System.out.println(messageobj.getSid());
		}
	}

