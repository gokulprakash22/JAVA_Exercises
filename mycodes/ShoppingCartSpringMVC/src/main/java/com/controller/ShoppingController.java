package com.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model.CustomerMaster;
import com.model.InvoiceMaster;
import com.model.ItemMaster;
import com.model.TotalDetails;
import com.service.ShoppingService;


@Controller
@RequestMapping("/shopping")
public class ShoppingController {

	@Autowired
	private ShoppingService shoppingService;
	
	HashMap<Integer,Integer> hm;
	
	@RequestMapping(value="shop1", method=RequestMethod.GET)
	public ModelAndView loadShop1(ModelAndView mandv, HttpServletRequest request) {
		hm = new HashMap<Integer,Integer>();
		List<ItemMaster> itemMasterList = shoppingService.getItemsByCategory("biscuits");
		mandv.addObject("itemMasterList",itemMasterList);
		mandv.setViewName("shop1");
		return mandv;
	}
	
	@RequestMapping(value="shop2", method=RequestMethod.POST)
	public ModelAndView loadShop2(ModelAndView mandv, HttpServletRequest request) {
		if(request.getParameterMap().containsKey("selected")){
			String[] selectedItems = request.getParameterValues("selected");
			for(int i=0;i<selectedItems.length;i++) {
				String qty=request.getParameter(selectedItems[i]);
				hm.put(Integer.parseInt(selectedItems[i]),Integer.parseInt(qty));
			}
		}
		List<ItemMaster> itemMasterList = shoppingService.getItemsByCategory("vegetables");
		mandv.addObject("itemMasterList",itemMasterList);
		mandv.setViewName("shop2");
		return mandv;
	}
	
	@RequestMapping(value="shop3", method=RequestMethod.POST)
	public ModelAndView loadShop3(ModelAndView mandv, HttpServletRequest request) {
		if(request.getParameterMap().containsKey("selected")){
			String[] selectedItems = request.getParameterValues("selected");
			for(int i=0;i<selectedItems.length;i++) {
				String qty=request.getParameter(selectedItems[i]);
				hm.put(Integer.parseInt(selectedItems[i]),Integer.parseInt(qty));
			}
		}
		List<ItemMaster> itemMasterList = shoppingService.getItemsByCategory("fruits");
		mandv.addObject("itemMasterList",itemMasterList);
		mandv.setViewName("shop3");
		return mandv;
	}
	
	@RequestMapping(value="invoice", method=RequestMethod.POST)
	public ModelAndView loadInvoice(ModelAndView mandv, HttpServletRequest request, HttpSession session) {
		if(request.getParameterMap().containsKey("selected")){
			String[] selectedItems = request.getParameterValues("selected");
			for(int i=0;i<selectedItems.length;i++) {
				String qty=request.getParameter(selectedItems[i]);
				hm.put(Integer.parseInt(selectedItems[i]),Integer.parseInt(qty));
			}
		}
		System.out.println(hm);
		CustomerMaster customerMaster = (CustomerMaster)session.getAttribute("customerMaster");
		TotalDetails totalDetails = shoppingService.createInvoice(customerMaster, hm);
		session.setAttribute("invno", totalDetails.getInvid());
		mandv.addObject("totalDetails",totalDetails);
		mandv.addObject("successMsg","");
		mandv.setViewName("invoice");
		return mandv;
	}

	public ShoppingService getShoppingService() {
		return shoppingService;
	}

	public void setShoppingService(ShoppingService shoppingService) {
		this.shoppingService = shoppingService;
	}
}
