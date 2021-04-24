package com.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.model.InvoiceWrapper;
import com.model.Items;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
@Service
@Transactional
public class ExportServiceImpl implements ExportService {

	@Override
	public void createPDF(InvoiceWrapper invoicewrapper) {
		try {
			String[] arr=new String[100];
			com.itextpdf.text.Document document=new com.itextpdf.text.Document();
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(invoicewrapper.getInvoice().getInvno()+".pdf"));
		    document.open();
		    int total=0;
		    Font boldheadFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
		    Font boldheadFont1 = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
		    Font boldheadFont2 = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
		    Paragraph parahead = new Paragraph("NetCart"+"\n",boldheadFont1);
		    Paragraph parahead1 = new Paragraph("2, Beach Road, Besant Nagar, Chennai"+"\n"+"www.netcart.com"+"\n"+"+91 990-120-4560"+"\n\n",boldheadFont2);
			Paragraph datepara=new Paragraph("Invoice Date : "+invoicewrapper.getInvoice().getInvdate());
			datepara.setAlignment(Paragraph.ALIGN_RIGHT);
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
		   
		    for (Map.Entry mapElement :invoicewrapper.getInvoice().getItemquantitymap().entrySet()) {
	            Items key = (Items)mapElement.getKey();
	            int qty=(int)mapElement.getValue();
	            cells[0] = new PdfPCell(new Paragraph(String.valueOf(key.getItemno())));table.addCell(cells[0]);System.out.println(key.getItemno());
	            cells[1] = new PdfPCell(new Paragraph(key.getItemdescription()));table.addCell(cells[1]);
	            cells[2] = new PdfPCell(new Paragraph(String.valueOf(key.getItemprice())));table.addCell(cells[2]);
	            cells[3] = new PdfPCell(new Paragraph(String.valueOf(qty)));table.addCell(cells[3]);
	            cells[4] = new PdfPCell(new Paragraph(key.getItemunit()));table.addCell(cells[4]);
	            cells[5] = new PdfPCell(new Paragraph(String.valueOf(qty*key.getItemprice())));table.addCell(cells[5]);
	            total+=qty*key.getItemprice();
		    }
	
			Font boldheadFont3 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			int tax=(int) (0.05*total);
			int shipping=200;
		    Paragraph para1 = new Paragraph("BILL TO:\n"+"________________________"+"\n",boldheadFont);
		    Paragraph para2 = new Paragraph(invoicewrapper.getCustomer().getCustomername()+"\n"+invoicewrapper.getCustomer().getCustomeraddress()+"\n"+invoicewrapper.getCustomer().getCustomeremail()+"\n"+invoicewrapper.getCustomer().getCustomerphone()+"\n\n");
		    document.add(parahead);
		    document.add(parahead1);
		    document.add(datepara);
		    document.add(para1);
		    document.add(para2);
		    document.add(table);
		    int sum=total+shipping+tax;
			Paragraph Total=new Paragraph("SubTotal : "+total+"\n"+"Shipping : "+shipping+"\n"+"Tax : "+tax+"\n"+"SubTotal : "+sum,boldheadFont3);
			Total.setAlignment(Paragraph.ALIGN_RIGHT);
			document.add(Total);
		    System.out.println("done");
		    document.close();
	 }catch(Exception e) {e.printStackTrace();}
		
	}
	@Override
	public void createExcel(InvoiceWrapper invoicewrapper) {
		try {
			String[] arr=new String[100];
			HSSFWorkbook wb = new HSSFWorkbook();   
			OutputStream fileOut = new FileOutputStream(invoicewrapper.getInvoice().getInvno()+".xls");   
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
			rowhead.createCell(2).setCellValue("NetCart");
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
			HSSFRow row1 = sheet.createRow((short)(1));
			row1.createCell(1).setCellValue("Customer No");
			row1.createCell(3).setCellValue(invoicewrapper.getCustomer().getCustomerno());  
			HSSFRow row2 = sheet.createRow((short)(2));
			row2.createCell(3).setCellValue(invoicewrapper.getCustomer().getCustomername());
			row2.createCell(1).setCellValue("Customer Name");
			HSSFRow row3 = sheet.createRow((short)(3));
			row3.createCell(3).setCellValue(invoicewrapper.getCustomer().getCustomeraddress());
			row3.createCell(1).setCellValue("Customer Address");
			HSSFRow row4 = sheet.createRow((short)(4));
			row4.createCell(3).setCellValue(invoicewrapper.getCustomer().getCustomeremail()); 
			row4.createCell(1).setCellValue("Customer Email");
			HSSFRow row5 = sheet.createRow((short)(5));
			row5.createCell(3).setCellValue(invoicewrapper.getCustomer().getCustomerphone()); 
			row5.createCell(1).setCellValue("Customer Phone");
			HSSFRow row6 = sheet.createRow((short)(6));
			row6.createCell(3).setCellValue(invoicewrapper.getInvoice().getInvno()); 
			row6.createCell(1).setCellValue("Invoice No");
			HSSFRow row0 = sheet.createRow((short)7);  
			row0.createCell(0).setCellValue("Item No");  
			row0.createCell(1).setCellValue("Description");  
			row0.createCell(2).setCellValue("Price");  
			row0.createCell(3).setCellValue("Quantity");  
			row0.createCell(4).setCellValue("Unit");
			row0.createCell(5).setCellValue("Total");
			int j=0;
			for (Map.Entry mapElement :invoicewrapper.getInvoice().getItemquantitymap().entrySet()) {
				HSSFRow row = sheet.createRow((short)(j+8));
	            Items key = (Items)mapElement.getKey();
	            int qty=(int)mapElement.getValue();
	            row.createCell(0).setCellValue(String.valueOf(key.getItemno()));
	            row.createCell(1).setCellValue(String.valueOf(key.getItemdescription()));
	            row.createCell(2).setCellValue(String.valueOf(key.getItemprice()));
	            row.createCell(3).setCellValue(String.valueOf(qty));
	            row.createCell(4).setCellValue(String.valueOf(key.getItemunit()));
	            row.createCell(5).setCellValue(String.valueOf(key.getItemprice()*qty));
	            j++;
		    }
			System.out.println("Excel Created Successfully");
			workbook.write(fileOut);
			wb.write(fileOut);   
			wb.close();
			fileOut.close();  
			}catch(Exception e) {}
			
		
	}

	@Override
	public void sendMail(InvoiceWrapper invoicewrapper){
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
				return new PasswordAuthentication("<emailid>","<password>");
			}
		});
		session.setDebug(true);
		MimeMessage m = new MimeMessage(session);
		try {
		Message message = new MimeMessage(session); 
		message.setFrom(new InternetAddress("saivishaal1920@gmail.com")); 
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(invoicewrapper.getCustomer().getCustomeremail())); 
		message.setSubject("Bill Receipt"); 

		BodyPart messageBodyPart = new MimeBodyPart(); 
		messageBodyPart.setText("Hi "+invoicewrapper.getCustomer().getCustomername()+"\n"+"Here is your bill");
		MimeBodyPart attachmentPart = new MimeBodyPart();
		attachmentPart.attachFile(new File(invoicewrapper.getInvoice().getInvno()+".pdf"));
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
	public void sendSMS(InvoiceWrapper invoicewrapper) {
		final String ACCOUNT_SID = "*********************************";
		final String AUTH_TOKEN = "*********************************";
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		String to="";
		String message="";
		to="+91"+invoicewrapper.getCustomer().getCustomerphone();
		message="Hello "+invoicewrapper.getCustomer().getCustomername()+"\n";
		StringBuilder sb=new StringBuilder();
		sb.append("Items:"+"\n");
		int i=1;
		HashMap<Items,Integer> itemqtymap=invoicewrapper.getInvoice().getItemquantitymap();
		for (Map.Entry item : itemqtymap.entrySet()) {
			Items itemdetail=(Items) item.getKey();
			int qty=(int)item.getValue();
			sb.append("Item :"+i+"\n"); 
	       sb.append("Item No:"+itemdetail.getItemno()+"\n");       
	       sb.append("Item Description: "+itemdetail.getItemdescription()+"\n"
	       		+ "Item Price: "+itemdetail.getItemprice()+"\n"+"Item Quantity: "+qty+"\n"+"Item Unit: "+
	    		   itemdetail.getItemunit()+"\n"+"Item Total: "+qty*itemdetail.getItemprice());
	       sb.append("\n");
	       i++;
		}
		message=message+sb.toString();
		System.out.println(to);
		System.out.println(message);
		com.twilio.rest.api.v2010.account.Message messageobj = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber(to),
		        new PhoneNumber("+12107022886"), 
		        message).create();
			    System.out.println(messageobj.getSid());
		}
		
}
