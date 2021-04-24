package com.controller;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.model.Invoice;
import com.model.InvoiceWrapper;
import com.model.Items;
import com.service.ItemService;
import com.service.ShoppingService;

@Controller
@RequestMapping("manage")
public class ManagementController {
	@Autowired
	private ShoppingService shoppingService;
	@Autowired
	private ItemService itemService;

	@RequestMapping(value="managementhome", method=RequestMethod.GET)
	public ModelAndView managementhome(ModelAndView mandv) {
		mandv.setViewName("home");
		return mandv;
	}
	@RequestMapping(value="stockreport", method=RequestMethod.GET)
	public ModelAndView stockreport(HttpServletRequest request,ModelAndView mandv) {
		List<Items> item=itemService.loadAllItems();
		mandv.addObject("itemlist",item);
		mandv.setViewName("stockreport");
		return mandv;
	}
	
	  @RequestMapping(value="invoicebyinvno",method=RequestMethod.POST) 
	  public ModelAndView invoicebyinvno(HttpServletRequest request,ModelAndView mandv) { int
	  invno=Integer.parseInt(request.getParameter("invno")); 
	  InvoiceWrapper invoicewrapper=shoppingService.createInvoiceWrapperbyInvno(invno);
	  shoppingService.setInvoicewrapper(invoicewrapper);
	  mandv.addObject("invoicewrapperdetails",invoicewrapper);
	  mandv.setViewName("invoice"); return mandv; }
	 
	
	@RequestMapping(value="itemstockybyitemno", method=RequestMethod.POST)
	public ModelAndView itemstockybyitemno(ModelAndView mandv,HttpServletRequest request) {
		int itemno=Integer.parseInt(request.getParameter("itemno"));
		Items item=itemService.loadItembyItemno(itemno);
		mandv.addObject("item",item);
		mandv.setViewName("itemstock");
		return mandv;
	}
	@RequestMapping(value="invoicebycustno", method=RequestMethod.POST)
	public ModelAndView invoicebycustno(ModelAndView mandv,HttpServletRequest request) {
		int custno=Integer.parseInt(request.getParameter("custno"));
		List<Invoice> invoice=shoppingService.getInvoiceByCustomerNo(custno);
		mandv.addObject("invoicelist",invoice);
		mandv.setViewName("listinvoice");
		return mandv;
	}
	@RequestMapping(value="invoicebyinvdate", method=RequestMethod.POST)
	public ModelAndView invoicebyinvdate(ModelAndView mandv,HttpServletRequest request) {
		String startdate=request.getParameter("startdate");
		String enddate=request.getParameter("enddate");
		List<Invoice> invoice=new ArrayList<Invoice>();
		invoice=shoppingService.getInvoiceByInvDate(startdate,enddate);
		mandv.addObject("invoicelist",invoice);
		mandv.setViewName("listinvoice");
		return mandv;
	}
	@RequestMapping(value = "getItemDetails",method = RequestMethod.POST)
	public ResponseEntity<Items> getStrr(@RequestParam("id") String idd){
		System.out.println("HI"+idd);
		Items item=itemService.loadItembyItemno(Integer.parseInt(idd));
		System.out.println(item);
		return new ResponseEntity<Items>(item, HttpStatus.OK);
	}	

}
