package xml_to_pdf;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.util.CellRangeAddress;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class XMLToPDFUsingSAX {
	public static void main(String[] args) {
		Object obj=new XMLToPDFUsingSAX();
		XMLToPDFSAX createPDFobj=new XMLToPDFSAX();
		obj=Proxy.newProxyInstance(XMLToPDFUsingSAX.class.getClassLoader(), new Class[] {PDFSAX.class},new MyInvocationHandlerSAX(createPDFobj));
		PDFSAX pdfobj=(PDFSAX)obj;
		pdfobj.convertXMLToPDF("105");
	}
}
class MyInvocationHandlerSAX implements InvocationHandler
{
	Object obj;
	public MyInvocationHandlerSAX(Object obj) {
		this.obj=obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object o=method.invoke(obj, args);
		return o;
	}
}

interface PDFSAX{
	public void convertXMLToPDF(String filename);
}

class XMLToPDFSAX implements PDFSAX
{
	@Override
	public void convertXMLToPDF(String filename) {
		try {
			System.out.println("Converting XML To PDF");
			String[] arr=new String[5];
			String[] arr2=new String[3];
			com.itextpdf.text.Document document=new com.itextpdf.text.Document();
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
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
			sp.parse(filename+".xml", handle);
			
			Paragraph toppara=new Paragraph("Invoice No : "+arr[0]+"\nInvoice Date : "+arr2[1]);
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
}