package xml_to_pdf;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class XMLToPDFUsingDOM {
	public static void main(String[] args) {
		Object obj=new XMLToPDFUsingDOM();
		XMLToPDF createPDFobj=new XMLToPDF();
		obj=Proxy.newProxyInstance(XMLToPDFUsingDOM.class.getClassLoader(), new Class[] {PDF.class},new MyInvocationHandler(createPDFobj));
		PDF pdfobj=(PDF)obj;
		pdfobj.convertXMLToPDF("105");
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
	public void convertXMLToPDF(String filename);
}

class XMLToPDF implements PDF
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
		    
		    //DOM Parser
		    DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		    dbf.setIgnoringElementContentWhitespace(true);
			dbf.setValidating(true);
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(filename+".xml");
		    Element rootElement=doc.getDocumentElement();
		    
			for(int i=0;i<rootElement.getChildNodes().getLength();i++) {
				if(i==0) {
						for(int j=0;j<rootElement.getChildNodes().item(i).getChildNodes().getLength();j++) {  
							arr[j]=rootElement.getChildNodes().item(i).getChildNodes().item(j).getFirstChild().getNodeValue();
						}
				}
				else if(i==1) {
					for(int j=0;j<rootElement.getChildNodes().item(i).getChildNodes().getLength();j++) {  
						for(int k=0;k<rootElement.getChildNodes().item(i).getChildNodes().item(j).getChildNodes().getLength();k++) {
							  cells[k] = new PdfPCell(new Paragraph(rootElement.getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getFirstChild().getNodeValue()));
						      cells[k].setBorderColor(BaseColor.BLACK);
						      cells[k].setPaddingLeft(10);
						      cells[k].setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
						      cells[k].setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
						      table.addCell(cells[k]);
						}
					}
				}
				else{
					for(int j=0;j<rootElement.getChildNodes().item(i).getChildNodes().getLength();j++) {  
						arr2[j]=rootElement.getChildNodes().item(i).getChildNodes().item(j).getFirstChild().getNodeValue();
					}
				}
			}
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