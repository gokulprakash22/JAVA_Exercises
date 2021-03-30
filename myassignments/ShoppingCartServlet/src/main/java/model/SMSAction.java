package model;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.invoice_db.TotalDetailsDTO;
import service.ExportInvoice;
import service.ExportInvoiceImpl;
import service.ShoppingService;
import service.ShoppingServiceImpl;

public class SMSAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		Properties prop=(Properties)request.getServletContext().getAttribute("properties");
		HttpSession session=request.getSession();
		ExportInvoice exportInvoiceService = ExportInvoiceImpl.getExportInvoiceImpl(prop);
		exportInvoiceService.createXML((int)session.getAttribute("invno"));
		exportInvoiceService.XMLToSMS((int)session.getAttribute("invno"));
		return "shop3";
	}
}
