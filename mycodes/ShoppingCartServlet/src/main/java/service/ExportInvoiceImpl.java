package service;

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
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

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

public class ExportInvoiceImpl implements ExportInvoice,Cloneable{
	private static ExportInvoiceImpl exportinvoiceimpl;
	private Properties prop;
	private ExportInvoiceImpl(Properties prop) {
		this.prop=prop;
	}
	
	synchronized public static ExportInvoiceImpl getExportInvoiceImpl(Properties prop) {
		
		if(exportinvoiceimpl==null) {
			exportinvoiceimpl=new ExportInvoiceImpl(prop);
			return exportinvoiceimpl;
		}
		else {
			return exportinvoiceimpl.createClone();
		}
	}
	public ExportInvoiceImpl createClone() {
		try {
			return (ExportInvoiceImpl)super.clone();
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
			File output = new File("D:/EYIntern/JAVA/workspace/ShoppingCartServlet/results/"+invno+".xml");
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
	public void XMLToPDF(int invno) {
			try {
				System.out.println("Converting XML To PDF");
				String[] arr=new String[5];
				String[] arr2=new String[3];
				com.itextpdf.text.Document document=new com.itextpdf.text.Document();
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:/EYIntern/JAVA/workspace/ShoppingCartServlet/results/"+invno+".pdf"));
			    document.open();
			    Font boldheadFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			    Font boldheadFont1 = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
			    Font boldheadFont2 = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
			    Paragraph parahead1 = new Paragraph("Gokul Stores"+"\n",boldheadFont1);
			    Paragraph parahead2 = new Paragraph("No.7, Roja Street, Kumari Nagar, Chennai-119"+"\n"+"www.gokulstores.com"+"\n"+"999-888-3210"+"\n\n",boldheadFont2);
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
			    cells[2] = new PdfPCell(new Paragraph("Unit",boldFont));
			    cells[3] = new PdfPCell(new Paragraph("Price",boldFont));
			    cells[4] = new PdfPCell(new Paragraph("Quantity",boldFont));
			    cells[5] = new PdfPCell(new Paragraph("Total",boldFont));
			    for(int i=0;i<6;i++)
				{
			    	cells[i].setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
			        table.addCell(cells[i]);
				}
			    
			    //SAX Parser
			    SAXParserFactory spf = SAXParserFactory.newInstance();
				SAXParser sp = spf.newSAXParser();
				DefaultHandler handle = new DefaultHandler(){
					String elementValue;
					public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
						
					}
					@Override
					public void endElement(String uri, String localName, String qName) throws SAXException {
						switch(qName) {
						case "customerno":
							arr[0] = elementValue;
							break;
						case "customername":
							arr[1] = elementValue;
							break;
						case "customeraddress":
							arr[2] = elementValue;
							break;
						case "customeremail":
							arr[3] = elementValue;
							break;
						case "customerphone":
							arr[4] = elementValue;
							break;
						case "invno":
							arr2[0] = elementValue;
							break;
						case "date":
							arr2[1] = elementValue;
							break;
						case "itemno":
							cells[0] = new PdfPCell(new Paragraph(elementValue));
							cells[0].setBorderColor(BaseColor.BLACK);
							cells[0].setPaddingLeft(10);
							cells[0].setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
							cells[0].setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
							table.addCell(cells[0]);
							break;
						case "itemdescription":
							cells[1] = new PdfPCell(new Paragraph(elementValue));
							cells[1].setBorderColor(BaseColor.BLACK);
							cells[1].setPaddingLeft(10);
							cells[1].setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
							cells[1].setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
							table.addCell(cells[1]);
							break;
						case "itemunit":
							cells[2] = new PdfPCell(new Paragraph(elementValue));
							cells[2].setBorderColor(BaseColor.BLACK);
							cells[2].setPaddingLeft(10);
							cells[2].setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
							cells[2].setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
							table.addCell(cells[2]);
							break;
						case "itemprice":
							cells[3] = new PdfPCell(new Paragraph(elementValue));
							cells[3].setBorderColor(BaseColor.BLACK);
							cells[3].setPaddingLeft(10);
							cells[3].setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
							cells[3].setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
							table.addCell(cells[3]);
							break;
						case "itemqty":
							cells[4] = new PdfPCell(new Paragraph(elementValue));
							cells[4].setBorderColor(BaseColor.BLACK);
							cells[4].setPaddingLeft(10);
							cells[4].setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
							cells[4].setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
							table.addCell(cells[4]);
							break;
						case "itemtotal":
							cells[5] = new PdfPCell(new Paragraph(elementValue));
							cells[5].setBorderColor(BaseColor.BLACK);
							cells[5].setPaddingLeft(10);
							cells[5].setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
							cells[5].setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
							table.addCell(cells[5]);
							break;
						case "total":
							arr2[2] = elementValue;
						}	
					}
					@Override
					public void characters(char[] ch, int start, int length) throws SAXException {
						elementValue = new String(ch,start,length);
					}
				};
				sp.parse("D:/EYIntern/JAVA/workspace/ShoppingCartServlet/results/"+invno+".xml", handle);
				
				Paragraph toppara=new Paragraph("Invoice No : "+arr2[0]+"\nInvoice Date : "+arr2[1]);
				toppara.setAlignment(Paragraph.ALIGN_RIGHT);
			    Paragraph para1 = new Paragraph("BILL TO:\n"+"________________________"+"\n",boldheadFont);
			    Paragraph para2 = new Paragraph(arr[1]+"\n"+arr[2]+"\n"+arr[3]+"\n"+arr[4]+"\n\n");
			    Paragraph totalpara=new Paragraph("Grand Total : "+arr2[2]);
			    totalpara.setAlignment(Paragraph.ALIGN_RIGHT);
			    document.add(toppara);
			    document.add(parahead1);
			    document.add(parahead2);
			    document.add(para1);
			    document.add(para2);
			    document.add(table);
			    document.add(totalpara);
			    document.close();
			    System.out.println("XML To PDF Converted Successfully");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	@Override
	public void XMLToExcel(int invno) {
		try {
			System.out.println("Converting XML To Excel");
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
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,1));
			HSSFRow rowhead = sheet.createRow((short)0);  
			rowhead.setRowStyle(style1);
			rowhead.createCell(0).setCellValue("Gokul Stores");
			sheet.addMergedRegion(new CellRangeAddress(1,1,0,1));
			sheet.addMergedRegion(new CellRangeAddress(1,1,2,3));
			sheet.addMergedRegion(new CellRangeAddress(2,2,0,1));
			sheet.addMergedRegion(new CellRangeAddress(2,2,2,3));
			sheet.addMergedRegion(new CellRangeAddress(3,3,0,1));
			sheet.addMergedRegion(new CellRangeAddress(3,3,2,3));
			sheet.addMergedRegion(new CellRangeAddress(4,4,0,1));
			sheet.addMergedRegion(new CellRangeAddress(4,4,2,3));
			sheet.addMergedRegion(new CellRangeAddress(5,5,0,1));
			sheet.addMergedRegion(new CellRangeAddress(5,5,2,3));
			sheet.addMergedRegion(new CellRangeAddress(6,6,0,1));
			sheet.addMergedRegion(new CellRangeAddress(6,6,2,3));
			sheet.addMergedRegion(new CellRangeAddress(7,7,0,1));
			sheet.addMergedRegion(new CellRangeAddress(7,7,2,3));
			
			//SAX Parser
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			DefaultHandler handle = new DefaultHandler(){
				String elementValue;
				HSSFRow row;
				int rownum = 9;
				public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
					switch(qName) {
					case "items":
						row = sheet.createRow((short)(8));
						row.createCell(0).setCellValue("Item No");  
						row.createCell(1).setCellValue("Description");
						row.createCell(2).setCellValue("Unit");
						row.createCell(3).setCellValue("Price");  
						row.createCell(4).setCellValue("Quantity");  
						row.createCell(5).setCellValue("Total");
						for(int j = 0; j<=5; j++){
							row.getCell(j).setCellStyle(style);
						}
						break;
					case "item":
						row = sheet.createRow((short)(rownum));
					}
				}
				@Override
				public void endElement(String uri, String localName, String qName) throws SAXException {
					switch(qName) {
					case "customerno":
						row = sheet.createRow((short)(1));
						row.createCell(2).setCellValue(elementValue);  
						row.createCell(0).setCellValue("Customer No");
						break;
					case "customername":
						row = sheet.createRow((short)(2));
						row.createCell(2).setCellValue(elementValue);  
						row.createCell(0).setCellValue("Customer Name");
						break;
					case "customeraddress":
						row = sheet.createRow((short)(3));
						row.createCell(2).setCellValue(elementValue);  
						row.createCell(0).setCellValue("Customer Address");
						break;
					case "customeremail":
						row = sheet.createRow((short)(4));
						row.createCell(2).setCellValue(elementValue);  
						row.createCell(0).setCellValue("Customer Email");
						break;
					case "customerphone":
						row = sheet.createRow((short)(5));
						row.createCell(2).setCellValue(elementValue);  
						row.createCell(0).setCellValue("Customer Phone");
						break;
					case "invno":
						row = sheet.createRow((short)(6));
						row.createCell(2).setCellValue(elementValue);  
						row.createCell(0).setCellValue("Invoice No");
						break;
					case "date":
						row = sheet.createRow((short)(7));
						row.createCell(2).setCellValue(elementValue);  
						row.createCell(0).setCellValue("Date");
						break;
					case "itemno":
						row.createCell(0).setCellValue(elementValue);
						break;
					case "itemdescription":
						row.createCell(1).setCellValue(elementValue);
						break;
					case "itemunit":
						row.createCell(2).setCellValue(elementValue);
						break;
					case "itemprice":
						row.createCell(3).setCellValue(elementValue);
						break;
					case "itemqty":
						row.createCell(4).setCellValue(elementValue);
						break;
					case "itemtotal":
						row.createCell(5).setCellValue(elementValue);
						break;
					case "item":
						rownum+=1;
						break;
					case "total":
						sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,3,4));
						row = sheet.createRow((short)(rownum));
						row.createCell(3).setCellValue("Grand Total");
						row.createCell(5).setCellValue(elementValue);
					}	
				}
				@Override
				public void characters(char[] ch, int start, int length) throws SAXException {
					elementValue = new String(ch,start,length);
				}
			};
			sp.parse("D:/EYIntern/JAVA/workspace/ShoppingCartServlet/results/"+invno+".xml", handle);
			
			workbook.write(fileOut);
			wb.write(fileOut);   
			wb.close();
			fileOut.close();  
			System.out.println("XML To Excel Converted Successfully");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void XMLToMail(int invno) {
		try {
			String from = "gokulprakash22@gmail.com";
	        String host = "smtp.gmail.com";
	        Properties properties = System.getProperties();
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", "465");
	        properties.put("mail.smtp.ssl.enable", "true");
	        properties.put("mail.smtp.auth", "true");
	        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("gokulprakash22@gmail.com", "******");
	            }
	        });
	        SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			DefaultHandler handle = new DefaultHandler(){
				String elementValue;
				String to;
				String subject = "Invoice";
				String message;
				StringBuilder sb=new StringBuilder();
				public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
					switch(qName) {
					case "items":
						sb.append("\nItems:\n");
					}
				}
				@Override
				public void endElement(String uri, String localName, String qName) throws SAXException {
					switch(qName) {
					case "customername":
						sb.append("Hi, "+elementValue+". Here is your invoice,\n");
						break;
					case "customeraddress":
						sb.append("Customer Address: "+elementValue+"\n");
						break;
					case "customeremail":
						sb.append("Customer Email: "+elementValue+"\n");
						//to=elementValue;
						to="gokulprakash22@gmail.com";
						break;
					case "customerphone":
						sb.append("Customer Phone: "+elementValue+"\n");
						break;
					case "invno":
						sb.append("Customer InvoiceNo: "+elementValue+"\n");
						break;
					case "date":
						sb.append("Date: "+elementValue+"\n\n");
						break;
					case "itemno":
						sb.append("ItemNo: "+elementValue+"\n");
						break;
					case "itemdescription":
						sb.append("Item Description: "+elementValue+"\n");
						break;
					case "itemunit":
						sb.append("Item Unit: "+elementValue+"\n");
						break;
					case "itemprice":
						sb.append("Item Price: "+elementValue+"\n");
						break;
					case "itemqty":
						sb.append("Item Quantity: "+elementValue+"\n");
						break;
					case "itemtotal":
						sb.append("Item Total: "+elementValue+"\n");
						break;
					case "item":
						sb.append("\n");
						break;
					case "total":
						sb.append("Grand Total: "+elementValue);
						message = sb.toString();
						try {
							MimeMessage mail;
							mail = new MimeMessage(session);
							mail.setFrom(new InternetAddress(from));
							mail.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
							mail.setSubject(subject);
							mail.setText(message);
				            System.out.println("To: "+to);
				            System.out.println("Subject: "+subject);
				            System.out.println("Message: "+message);
				            System.out.println("Sending Mail");
				            Transport.send(mail);
				            System.out.println("Sent Mail Successfully....");
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
				@Override
				public void characters(char[] ch, int start, int length) throws SAXException {
					elementValue = new String(ch,start,length);
				}
			};
			sp.parse("D:/EYIntern/JAVA/workspace/ShoppingCartServlet/results/"+invno+".xml", handle);
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void XMLToSMS(int invno) {
		try {
		  String ACCOUNT_SID = "ACed9066982355c8c63b965bff60292263";
		  String AUTH_TOKEN = "58fa646ac392d5e12027d5c525977ace";  
		  Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		    
		    SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			StringBuilder sb=new StringBuilder();
			DefaultHandler handle = new DefaultHandler(){
				String elementValue;
				String to;
				String message;
				public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
					switch(qName) {
					case "items":
						sb.append("\nItems:\n");
					}
				}
				@Override
				public void endElement(String uri, String localName, String qName) throws SAXException {
					switch(qName) {
					case "customername":
						sb.append("Hi, "+elementValue+". Here is your invoice,\n");
						break;
					case "customeraddress":
						sb.append("Customer Address: "+elementValue+"\n");
						break;
					case "customeremail":
						sb.append("Customer Email: "+elementValue+"\n");
						break;
					case "customerphone":
						sb.append("Customer Phone: "+elementValue+"\n");
						//to = elementValue;
						to = "+918946069629";
						break;
					case "invno":
						sb.append("Customer InvoiceNo: "+elementValue+"\n");
						break;
					case "date":
						sb.append("Date: "+elementValue+"\n\n");
						break;
					case "itemno":
						sb.append("ItemNo: "+elementValue+"\n");
						break;
					case "itemdescription":
						sb.append("Item Description: "+elementValue+"\n");
						break;
					case "itemunit":
						sb.append("Item Unit: "+elementValue+"\n");
						break;
					case "itemprice":
						sb.append("Item Price: "+elementValue+"\n");
						break;
					case "itemqty":
						sb.append("Item Quantity: "+elementValue+"\n");
						break;
					case "itemtotal":
						sb.append("Item Total: "+elementValue+"\n");
						break;
					case "item":
						sb.append("\n");
						break;
					case "total":
						sb.append("Grand Total: "+elementValue);
						message = sb.toString();
						try {
							com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber(to),new PhoneNumber("+19544407776"), message).create();
							System.out.println("Message Has Been Delivered to "+to+" Successfully.");
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}	
				}
				@Override
				public void characters(char[] ch, int start, int length) throws SAXException {
					elementValue = new String(ch,start,length);
				}
			};
			sp.parse("D:/EYIntern/JAVA/workspace/ShoppingCartServlet/results/"+invno+".xml", handle);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

