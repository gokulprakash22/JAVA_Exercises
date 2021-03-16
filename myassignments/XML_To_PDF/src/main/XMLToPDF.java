package main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Scanner;

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

public class XMLToPDF {
	public static void main(String[] args) {
		Object obj1=new XMLToPDF();
		createPDF createPDFobj=new createPDF();
		obj1=Proxy.newProxyInstance(XMLToPDF.class.getClassLoader(), new Class[] {PDF.class},new MyInvocationHandler(createPDFobj));
		PDF pdfobj=(PDF)obj1;
		pdfobj.convertToPdf();
	}
}
class MyInvocationHandler implements InvocationHandler
{
	Object obj;
	public MyInvocationHandler(Object obj) {
		this.obj=obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object o=method.invoke(obj, args);
		return o;
	}
}

interface PDF{
	public void convertToPdf();
}

class createPDF implements PDF
{

	@Override
	public void convertToPdf() {
		try
		{
		com.itextpdf.text.Document document=new com.itextpdf.text.Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Bill.pdf"));
        document.open();
        
        Font boldheadFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
        
        Paragraph para1 = new Paragraph("Customer Name: Gokul    Bill No : 123456    Bill Date : 01/01/2021",boldheadFont);
        para1.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(para1);
        
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f); 
        table.setSpacingAfter(10f); 
 
        float[] columnWidths = {1f,1f,1f,1f,1f};
        table.setWidths(columnWidths);
        PdfPCell cells[]=new PdfPCell[5];
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        
        cells[0] = new PdfPCell(new Paragraph("S.NO",boldFont));
        cells[1] = new PdfPCell(new Paragraph("Item Name",boldFont));
        cells[2] = new PdfPCell(new Paragraph("Price",boldFont));
        cells[3] = new PdfPCell(new Paragraph("Quantity",boldFont));
        cells[4] = new PdfPCell(new Paragraph("Amount",boldFont));
        for(int i=0;i<5;i++)
		{
        	cells[i].setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
	        table.addCell(cells[i]);
		}
        for(int i=0;i<5;i++)
        {
        	  cells[i].setBorderColor(BaseColor.BLACK);
	          cells[i].setPaddingLeft(10);
	          cells[i].setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
	          cells[i].setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
        }
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setValidating(true);
        DocumentBuilder db=dbf.newDocumentBuilder();
		Document doc=db.parse("items.xml");
        Element rootElement=doc.getDocumentElement();
		for(int i=0;i<rootElement.getChildNodes().getLength();i++) {
			for(int j=0;j<rootElement.getChildNodes().item(i).getChildNodes().getLength();j++) {  
				  cells[j] = new PdfPCell(new Paragraph(rootElement.getChildNodes().item(i).getChildNodes().item(j).getFirstChild().getNodeValue()));
			      cells[j].setBorderColor(BaseColor.BLACK);
			      cells[j].setPaddingLeft(10);
			      cells[j].setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
			      cells[j].setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
			}
			for(int j=0;j<rootElement.getChildNodes().item(i).getChildNodes().getLength();j++)
			{
		        table.addCell(cells[j]);
			}
		}
		
		
		
		System.out.println("PDF Created Successfully");
        document.add(table);
        document.close();
		

		
	}	
		catch(Exception e) {}
	}
}