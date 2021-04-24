package com.controller;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model.Customer;
import com.model.Invoice;
import com.model.InvoiceWrapper;
import com.model.Items;
import com.service.CustomerService;
import com.service.ItemService;
import com.service.ShoppingService;

@Controller
@RequestMapping("shop")
public class ShoppingCartController {
	@Autowired
	private ShoppingService shoppingService;
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CustomerService customerService;
	
	HashMap<Integer,Integer> hm;
	
	@RequestMapping(value = "userhome",method = RequestMethod.GET)
	public String getHomePage() {
		return "welcome";
	}
	
	@RequestMapping(value = "{cat}",method = RequestMethod.GET)
	public String getItems(@PathVariable String cat,Model model) {
		System.out.println("Category: "+cat);
		model.addAttribute("items",itemService.loadAllItemsByCategory(cat));
		return cat;	
	}
	
	@RequestMapping(value="profile", method=RequestMethod.GET)
	public ModelAndView profile(HttpServletRequest request,ModelAndView mandv) {
		HttpSession session=request.getSession();
		int custno=(int) session.getAttribute("custno");
		Customer cust=customerService.loadCustomerByCustno(custno);
		mandv.addObject("customer",cust);
		mandv.setViewName("profile_page");
		return mandv;
	}
	
	@RequestMapping(value="updateProfile", method= RequestMethod.POST)
	public ModelAndView updateProfile(ModelAndView mandv, HttpSession hsess, Customer customer)
	{
		
		System.out.println(customer.toString());
		Customer customer_original = (Customer) hsess.getAttribute("customer");
		customer.setUser(customer_original.getUser());
		customerService.updateCustomer(customer);
		hsess.setAttribute("customer", customer);
		mandv.setViewName("redirect:profile");
		return mandv;
				
	}
	
	@RequestMapping(value="editprofile", method=RequestMethod.POST)
	public ModelAndView editprofile(@ModelAttribute("cust") Customer customer,HttpServletRequest request,ModelAndView mandv) {
		mandv.setViewName("profile_page");
		return mandv;
	}
	
	@RequestMapping(value = "savestationary",method = RequestMethod.POST)
	public String submitJewels(@RequestParam(value = "selecteditems",required = false) List<Integer> items,HttpServletRequest request) {
		hm=new HashMap<Integer,Integer>();
		if(items!=null) {
			for(Integer item:items) {
				hm.put(item,Integer.parseInt(request.getParameter(String.valueOf(item))));
			}
		}
		
		return "redirect:Grocery";
		
	}
	
	@RequestMapping(value = "savegrocery",method = RequestMethod.POST)
	public String submitGrocery(@RequestParam(value = "selecteditems",required = false) List<Integer> items,
			HttpServletRequest request) {
		if(items!=null) {
			for(Integer item:items) {
				hm.put(item,Integer.parseInt(request.getParameter(String.valueOf(item))));
			}
		}
		return "redirect:Fruits";
		
	}
	
	@RequestMapping(value = "savefruits",method = RequestMethod.POST)
	public String submitFruits(@RequestParam(value = "selecteditems",required = false) List<Integer> items,
			HttpServletRequest request,Model mandv) {
		if(items!=null) {
			for(Integer item:items) {
				hm.put(item,Integer.parseInt(request.getParameter(String.valueOf(item))));
			}
		}
		HashMap<Items,Integer> itemmap=shoppingService.checkoutDetails(hm);
		HttpSession session=request.getSession(false);
		session.setAttribute("itemmap",itemmap);
		
		return "redirect:/payment/start";
	}
	@RequestMapping(value="loadInvoice", method=RequestMethod.GET)
	public ModelAndView loadInvoice(HttpServletRequest request,ModelAndView mandv) {
		try {
		HttpSession session=request.getSession(false);
		Invoice invoice=shoppingService.createInvoice(hm, (Integer)session.getAttribute("custno"));
		int invno=shoppingService.getInvoiceNo(invoice);
		shoppingService.getInvoiceMapByInvNo(invno);
		InvoiceWrapper invoicewrapper=shoppingService.createInvoiceWrapperbyInvno(invno);
		mandv.addObject("invoicewrapperdetails",invoicewrapper);
		shoppingService.setInvoicewrapper(invoicewrapper);
		mandv.setViewName("invoice");
		return mandv;}catch(Exception e) {}
		return mandv;
	}





}
