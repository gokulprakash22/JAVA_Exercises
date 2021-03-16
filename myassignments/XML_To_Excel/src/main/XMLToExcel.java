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
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import  org.apache.poi.hssf.usermodel.HSSFRow;  
public class XMLToExcel   
{   
public static void main(String[] args) throws FileNotFoundException, IOException, ParserConfigurationException, SAXException   
{   	
		HSSFWorkbook wb = new HSSFWorkbook();   
		OutputStream fileOut = new FileOutputStream("Bill.xls");   
		System.out.println("Excel File has been created successfully.");   
		HSSFWorkbook workbook = new HSSFWorkbook();  
		HSSFSheet sheet = workbook.createSheet("Bill_Sheet");   
		
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setFontHeightInPoints((short)10);
		font.setBold(true);
		style.setFont(font);
		
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,2));
		sheet.addMergedRegion(new CellRangeAddress(0,0,3,4));
		sheet.addMergedRegion(new CellRangeAddress(0,0,5,6));
		
		HSSFRow rowhead = sheet.createRow((short)0);  
		rowhead.createCell(0).setCellValue("Customer Name : Gokul");  
		rowhead.createCell(3).setCellValue("Bill No : 31243");  
		rowhead.createCell(5).setCellValue("Bill Date : 08/03/2021");
		
		HSSFRow row0 = sheet.createRow((short)1);  
		row0.createCell(0).setCellValue("S.No.");  
		row0.createCell(1).setCellValue("Item");  
		row0.createCell(2).setCellValue("Price");  
		row0.createCell(3).setCellValue("Quantity");  
		row0.createCell(4).setCellValue("Amount");
		
		for(int j = 0; j<=4; j++)
		row0.getCell(j).setCellStyle(style);

	
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setValidating(true);
		
		
		DocumentBuilder db=dbf.newDocumentBuilder();
		Document doc=db.parse("items.xml");
		
		Element rootElement=doc.getDocumentElement();
		
		for(int i=0;i<rootElement.getChildNodes().getLength();i++) {
			HSSFRow row = sheet.createRow((short)(i+2));
			for(int j=0;j<rootElement.getChildNodes().item(i).getChildNodes().getLength();j++) {
				row.createCell(j).setCellValue(rootElement.getChildNodes().item(i).getChildNodes().item(j).getFirstChild().getNodeValue());  
			}
		}
	
		
		workbook.write(fileOut);
		wb.write(fileOut);   
		wb.close();
		fileOut.close();  
}   
}