package xml_to_excel;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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

public class XMLToExcelUsingDOM {
	public static void main(String[] args) {
		Object obj=new XMLToExcelUsingDOM();
		XMLToExcel createExcelobj=new XMLToExcel();
		obj=Proxy.newProxyInstance(XMLToExcelUsingDOM.class.getClassLoader(), new Class[] {Excel.class},new MyInvocationHandler2(createExcelobj));
		Excel excelobj=(Excel)obj;
		excelobj.convertXMLToExcel("105");
	}
}
class MyInvocationHandler2 implements InvocationHandler
{
	Object obj;
	public MyInvocationHandler2(Object obj) {
		this.obj=obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object o=method.invoke(obj, args);
		return o;
	}
}

interface Excel{
	public void convertXMLToExcel(String filename);
}

class XMLToExcel implements Excel{
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
			HSSFRow row = sheet.createRow((short)0);  
			row.setRowStyle(style1);
			row.createCell(0).setCellValue("Gokul Stores");
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
			
			//DOM Parser
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			dbf.setIgnoringElementContentWhitespace(true);
			dbf.setValidating(true);
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(filename+".xml");
			Element rootElement=doc.getDocumentElement();
			row = sheet.createRow((short)(1));
			row.createCell(2).setCellValue(rootElement.getChildNodes().item(0).getChildNodes().item(0).getFirstChild().getNodeValue());  
			row.createCell(0).setCellValue("Customer No");
			row = sheet.createRow((short)(2));
			row.createCell(2).setCellValue(rootElement.getChildNodes().item(0).getChildNodes().item(1).getFirstChild().getNodeValue());
			row.createCell(0).setCellValue("Customer Name");
			row = sheet.createRow((short)(3));
			row.createCell(2).setCellValue(rootElement.getChildNodes().item(0).getChildNodes().item(2).getFirstChild().getNodeValue());
			row.createCell(0).setCellValue("Customer Address");
			row = sheet.createRow((short)(4));
			row.createCell(2).setCellValue(rootElement.getChildNodes().item(0).getChildNodes().item(3).getFirstChild().getNodeValue()); 
			row.createCell(0).setCellValue("Customer Email");
			row = sheet.createRow((short)(5));
			row.createCell(2).setCellValue(rootElement.getChildNodes().item(0).getChildNodes().item(4).getFirstChild().getNodeValue()); 
			row.createCell(0).setCellValue("Customer Phone");
			row = sheet.createRow((short)(6));
			row.createCell(2).setCellValue(rootElement.getChildNodes().item(2).getChildNodes().item(0).getFirstChild().getNodeValue()); 
			row.createCell(0).setCellValue("Invoice No");
			row = sheet.createRow((short)(7));
			row.createCell(2).setCellValue(rootElement.getChildNodes().item(2).getChildNodes().item(1).getFirstChild().getNodeValue()); 
			row.createCell(0).setCellValue("Date");
			row = sheet.createRow((short)(8));
			row.createCell(0).setCellValue("Item No");  
			row.createCell(1).setCellValue("Description");
			row.createCell(2).setCellValue("Unit");
			row.createCell(3).setCellValue("Price");  
			row.createCell(4).setCellValue("Quantity");  
			row.createCell(5).setCellValue("Total");
			for(int j = 0; j<=5; j++) {
				row.getCell(j).setCellStyle(style);
			}
			for(int j=0;j<rootElement.getChildNodes().item(1).getChildNodes().getLength();j++) {  
				row = sheet.createRow((short)(j+9));
				for(int k=0;k<rootElement.getChildNodes().item(1).getChildNodes().item(j).getChildNodes().getLength();k++)
				{
					row.createCell(k).setCellValue(rootElement.getChildNodes().item(1).getChildNodes().item(j).getChildNodes().item(k).getFirstChild().getNodeValue());  
				}
			}
			int lastrown = rootElement.getChildNodes().item(1).getChildNodes().getLength()+9;
			sheet.addMergedRegion(new CellRangeAddress(lastrown,lastrown,3,4));
			row = sheet.createRow((short)(lastrown));
			row.createCell(3).setCellValue("Grand Total");
			row.createCell(5).setCellValue(rootElement.getChildNodes().item(2).getChildNodes().item(2).getFirstChild().getNodeValue());
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