package xml_to_excel;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLToExcelUsingSAX {
	public static void main(String[] args) {
		Object obj=new XMLToExcelUsingSAX();
		XMLToExcelSAX createExcelobj=new XMLToExcelSAX();
		obj=Proxy.newProxyInstance(XMLToExcelUsingSAX.class.getClassLoader(), new Class[] {ExcelSAX.class},new MyInvocationHandler2SAX(createExcelobj));
		ExcelSAX excelobj=(ExcelSAX)obj;
		excelobj.convertXMLToExcel("105");
	}
}
class MyInvocationHandler2SAX implements InvocationHandler
{
	Object obj;
	public MyInvocationHandler2SAX(Object obj) {
		this.obj=obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object o=method.invoke(obj, args);
		return o;
	}
}

interface ExcelSAX{
	public void convertXMLToExcel(String filename);
}

class XMLToExcelSAX implements ExcelSAX{
	@Override
	public void convertXMLToExcel(String filename) {
		try {
			System.out.println("Converting XML To Excel");
			String[] arr=new String[100];
			HSSFWorkbook wb = new HSSFWorkbook();   
			OutputStream fileOut = new FileOutputStream(filename+".xls");   
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
			sp.parse(filename+".xml", handle);
			
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
}