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

public class ExcelAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Excel Create");
		Properties prop=(Properties)request.getServletContext().getAttribute("properties");
		HttpSession session=request.getSession();
		ShoppingService shoppingService=ShoppingServiceImpl.getShoppingServiceImpl(prop);
		ExportInvoice exportInvoiceService = ExportInvoiceImpl.getExportInvoiceImpl(prop);
		exportInvoiceService.createXML((int)session.getAttribute("invno"));
		exportInvoiceService.XMLToExcel((int)session.getAttribute("invno"));
		return "shop3";
	}

}
