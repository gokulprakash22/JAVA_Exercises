package model;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.invoice_db.TotalDetailsDTO;
import service.ExportInvoice;
import service.ExportInvoiceImpl;
import service.ShoppingService;
import service.ShoppingServiceImpl;

/**
 * Servlet implementation class Pdfaction
 */
public class PDFAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("PDF Create");
		Properties prop=(Properties)request.getServletContext().getAttribute("properties");
		HttpSession session=request.getSession();
		ExportInvoice exportInvoiceService = ExportInvoiceImpl.getExportInvoiceImpl(prop);
		exportInvoiceService.createXML((int)session.getAttribute("invno"));
		exportInvoiceService.XMLToPDF((int)session.getAttribute("invno"));
		return "shop3";
	}
	
}
