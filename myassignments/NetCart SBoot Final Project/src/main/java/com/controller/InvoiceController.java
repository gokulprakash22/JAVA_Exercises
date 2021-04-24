package com.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.service.ExportService;
import com.service.ShoppingService;

@Controller
@RequestMapping("export")
public class InvoiceController {
	@Autowired
	private ExportService exportService;
	@Autowired
	private ShoppingService shoppingService;
	

	@RequestMapping(value="createPDF", method=RequestMethod.POST)
	public ModelAndView createPDF(ModelAndView mandv) {
		exportService.createPDF(shoppingService.getInvoicewrapper());
		System.out.println("Invoice Wrapper Details"+shoppingService.getInvoicewrapper());
		mandv.addObject("invoicewrapperdetails",shoppingService.getInvoicewrapper());
		mandv.setViewName("invoice");
		return mandv;
		
	}
	
	@RequestMapping(value="createExcel", method=RequestMethod.POST)
	public ModelAndView createExcel(ModelAndView mandv) {
		exportService.createExcel(shoppingService.getInvoicewrapper());
		mandv.addObject("invoicewrapperdetails",shoppingService.getInvoicewrapper());
		mandv.setViewName("invoice");
		return mandv;
		
	}

	@RequestMapping(value="sendEmail", method=RequestMethod.POST)
	public ModelAndView sendMail(ModelAndView mandv) {
		exportService.sendMail(shoppingService.getInvoicewrapper());
		mandv.addObject("invoicewrapperdetails",shoppingService.getInvoicewrapper());
		mandv.setViewName("invoice");
		return mandv;
		
	}
	
	@RequestMapping(value="sendSMS", method=RequestMethod.POST)
	public ModelAndView sendSMS(ModelAndView mandv) {
		exportService.sendSMS(shoppingService.getInvoicewrapper());
		mandv.addObject("invoicewrapperdetails",shoppingService.getInvoicewrapper());
		mandv.setViewName("invoice");
		return mandv;
		
	}

}
