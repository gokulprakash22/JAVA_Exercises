package main;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import  org.apache.poi.hssf.usermodel.HSSFSheet;  
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import  org.apache.poi.hssf.usermodel.HSSFRow;  
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
public class XMLToPDF   
{   
public static void main(String[] args)
{   	
	try {
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setValidating(true);
		DocumentBuilder db=dbf.newDocumentBuilder();
		Document doc=db.parse("items.xml");
		Element rootElement=doc.getDocumentElement();
		
		com.itextpdf.text.Document document=new com.itextpdf.text.Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Bill.pdf"));
        document.open();
        
        Font boldheadFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
        
        Paragraph para1 = new Paragraph("Customer Name: Gokul   Bill No : 31243    Bill Date : 08/03/2021",boldheadFont);
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
//				System.out.println(cells[j]);
		        table.addCell(cells[j]);
			}
		}
		
        document.add(table);
        document.close();
        System.out.println("PDF created successfully");
		


		
	}

	catch(Exception e) {e.printStackTrace();}
}   

}  
