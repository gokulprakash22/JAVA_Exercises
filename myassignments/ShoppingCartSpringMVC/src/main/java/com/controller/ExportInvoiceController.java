package com.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.ItemMaster;
import com.model.TotalDetails;
import com.service.ExportInvoiceService;

@Controller
@RequestMapping("/invoice")
public class ExportInvoiceController {

	@Autowired
	private ExportInvoiceService exportInvoiceService;
	
	@RequestMapping(value="exportpdf/{invno}", method=RequestMethod.GET)
	public ModelAndView exportPDF(@PathVariable int invno, ModelAndView mandv) {
		TotalDetails totalDetails = exportInvoiceService.getTotalDetails(invno);
		exportInvoiceService.createXML(totalDetails);
		exportInvoiceService.XMLToPDF(invno);
		mandv.addObject("totalDetails",totalDetails);
		mandv.addObject("successMsg","Invoice as PDF Exported Successfully");
		mandv.setViewName("invoice");
		return mandv;
	}
	
	@RequestMapping(value="exportexcel/{invno}", method=RequestMethod.GET)
	public ModelAndView exportExcel(@PathVariable int invno, ModelAndView mandv) {
		TotalDetails totalDetails = exportInvoiceService.getTotalDetails(invno);
		exportInvoiceService.createXML(totalDetails);
		exportInvoiceService.XMLToExcel(invno);
		mandv.addObject("totalDetails",totalDetails);
		mandv.addObject("successMsg","Invoice as Excel Exported Successfully");
		mandv.setViewName("invoice");
		return mandv;
	}
	
	@RequestMapping(value="exportmail/{invno}", method=RequestMethod.GET)
	public ModelAndView exportMail(@PathVariable int invno, ModelAndView mandv) {
		TotalDetails totalDetails = exportInvoiceService.getTotalDetails(invno);
		exportInvoiceService.createXML(totalDetails);
		exportInvoiceService.XMLToMail(invno);
		mandv.addObject("totalDetails",totalDetails);
		mandv.addObject("successMsg","Invoice via Mail Sent Successfully");
		mandv.setViewName("invoice");
		return mandv;
	}
	
	@RequestMapping(value="exportsms/{invno}", method=RequestMethod.GET)
	public ModelAndView exportSMS(@PathVariable int invno, ModelAndView mandv) {
		TotalDetails totalDetails = exportInvoiceService.getTotalDetails(invno);
		exportInvoiceService.createXML(totalDetails);
		exportInvoiceService.XMLToSMS(invno);
		mandv.addObject("totalDetails",totalDetails);
		mandv.addObject("successMsg","Invoice via SMS Sent Successfully");
		mandv.setViewName("invoice");
		return mandv;
	}

	public final ExportInvoiceService getExportInvoiceService() {
		return exportInvoiceService;
	}

	public final void setExportInvoiceService(ExportInvoiceService exportInvoiceService) {
		this.exportInvoiceService = exportInvoiceService;
	}
	
}
