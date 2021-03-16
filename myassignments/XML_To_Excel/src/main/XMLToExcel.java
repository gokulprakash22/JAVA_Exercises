package main;

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

public class XMLToExcel {
	public static void main(String[] args) {
		Object obj1=new XMLToExcel();
		createExcel createExcelobj=new createExcel();
		obj1=Proxy.newProxyInstance(XMLToExcel.class.getClassLoader(), new Class[] {Excel.class},new MyInvocationHandler1(createExcelobj));
		Excel excelobj=(Excel)obj1;
		excelobj.convertToExcel();
	}
}
class MyInvocationHandler1 implements InvocationHandler
{
	Object obj;
	public MyInvocationHandler1(Object obj) {
		this.obj=obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object o=method.invoke(obj, args);
		return o;
	}
}

interface Excel{
	public void convertToExcel();
}

class createExcel implements Excel
{

	@Override
	public void convertToExcel() {
		try
		{
		HSSFWorkbook wb = new HSSFWorkbook();   
		OutputStream fileOut = new FileOutputStream("Bill.xls");   
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
		rowhead.createCell(3).setCellValue("Bill No : 123456");  
		rowhead.createCell(5).setCellValue("Bill Date : 01/01/2021");
		
		HSSFRow row0 = sheet.createRow((short)1);  
		row0.createCell(0).setCellValue("S.No.");  
		row0.createCell(1).setCellValue("Item Name");  
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
		System.out.println("Excel Created Successfully");
		workbook.write(fileOut);
		wb.write(fileOut);   
		wb.close();
		fileOut.close();  
		}catch(Exception e) {}
	}
	
}