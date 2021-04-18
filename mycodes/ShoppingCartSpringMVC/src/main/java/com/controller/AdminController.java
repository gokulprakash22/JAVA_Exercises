package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model.ItemMaster;
import com.service.AdminService;


@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView loginForm(ModelAndView mandv) {
		mandv.addObject("loginMsg","Please Enter The Password");
		mandv.addObject("loginMsgColor","green");
		mandv.setViewName("adminlogin");
		return mandv;
	}

	@RequestMapping(value="submitlogin", method=RequestMethod.POST)
	public ModelAndView loginForm(ModelAndView mandv, @RequestParam String pass) {
		if(pass.equals("admin@123")) {
			List<ItemMaster> itemMasterList = adminService.getAllItems();
			mandv.addObject("itemMasterList",itemMasterList);
			mandv.setViewName("admin");
		}
		else {
			mandv.addObject("loginMsg","Password is Wrong");
			mandv.addObject("loginMsgColor","red");
			mandv.setViewName("adminlogin");
		}
		return mandv;
	}
	
	@RequestMapping(value="additem", method=RequestMethod.GET)
	public ModelAndView addItemForm(ModelAndView mandv) {
		ItemMaster itemMaster = new ItemMaster();
		mandv.addObject("itemMaster",itemMaster);
		mandv.setViewName("additem");
		return mandv;
	}
	
	@RequestMapping(value="submititem", method=RequestMethod.POST)
	public ModelAndView submitItem(ItemMaster itemMaster, ModelAndView mandv) {
		adminService.createItem(itemMaster);
		List<ItemMaster> itemMasterList = adminService.getAllItems();
		mandv.addObject("itemMasterList",itemMasterList);
		mandv.setViewName("admin");
		return mandv;
	}
	@RequestMapping(value="updateitem/{itemid}", method=RequestMethod.GET)
	public ModelAndView updateItemForm(@PathVariable int itemid, ModelAndView mandv) {
		ItemMaster itemMaster = adminService.getitem(itemid);
		mandv.addObject("itemMaster",itemMaster);
		mandv.setViewName("updateitem");
		return mandv;
	}
	@RequestMapping(value="submitupdateitem", method=RequestMethod.POST)
	public ModelAndView submitUpdateItem(ItemMaster itemMaster, ModelAndView mandv) {
		adminService.updateItem(itemMaster);
		List<ItemMaster> itemMasterList = adminService.getAllItems();
		mandv.addObject("itemMasterList",itemMasterList);
		mandv.setViewName("admin");
		return mandv;
	}
	@RequestMapping(value="deleteitem/{itemid}", method=RequestMethod.GET)
	public ModelAndView deleteItem(@PathVariable int itemid, ModelAndView mandv) {
		adminService.deleteItem(itemid);
		List<ItemMaster> itemMasterList = adminService.getAllItems();
		mandv.addObject("itemMasterList",itemMasterList);
		mandv.setViewName("admin");
		return mandv;
	}
	
	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
}
