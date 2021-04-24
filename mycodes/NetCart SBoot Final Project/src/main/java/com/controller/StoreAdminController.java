package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.model.Items;
import com.service.ItemService;
@Controller
@RequestMapping("store")
public class StoreAdminController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value = "displayitems",method = RequestMethod.GET)
	public ModelAndView loadForm(ModelAndView mandv) {
		List<Items> item=itemService.loadAllItems();
		mandv.addObject("itemlist",item);
		mandv.addObject("item", new Items());
		mandv.setViewName("displayitems");
		return mandv;
	}
	@RequestMapping(value="deleteitem/{itemno}", method=RequestMethod.GET)
	public ModelAndView deleteItem(@PathVariable int itemno,ModelAndView mandv) {
		itemService.deleteItembyItemno(itemno);
		mandv.setViewName("redirect:/store/displayitems");
		return mandv;
	}
	


	
	@RequestMapping(value="itemAdd")
	public ModelAndView itemAdd(@ModelAttribute("item") Items itemdetail,ModelAndView mandv) {
		itemService.insertItem(itemdetail);
		mandv.setViewName("redirect:displayitems");
		return mandv;
	}
	
	@RequestMapping(value = "itemUpdate")
	public ModelAndView itemUpdate(ModelAndView mandv,HttpServletRequest request) {
		Items item=new Items();
		item.setCategory(request.getParameter("category"));
		item.setImage(request.getParameter("imageURL"));
		item.setItemdescription(request.getParameter("itemdesc"));
		item.setItemno(Integer.parseInt(request.getParameter("id")));
		item.setItemprice(Integer.parseInt(request.getParameter("price")));
		item.setStock(Integer.parseInt(request.getParameter("stock")));
		item.setItemunit(request.getParameter("unit"));
		itemService.updateItem(item);
		mandv.setViewName("redirect:/store/displayitems");
		return mandv;
	}
	
//	@RequestMapping(value="stockform",method = RequestMethod.GET) 
//	public ModelAndView stockForm(ModelAndView mandv)	{
//		Item item=new Item();
//		mandv.addObject("item", item);
//		mandv.setViewName("stock");
//		return mandv;
//	}
	
	@RequestMapping(value = "getItemDetails",method = RequestMethod.POST)
	public ResponseEntity<Items> getStrr(@RequestParam("id") String idd){
		System.out.println("HI"+idd);
		Items item=itemService.loadItembyItemno(Integer.parseInt(idd));
		System.out.println(item);
		return new ResponseEntity<Items>(item, HttpStatus.OK);
	}	
	
}
