package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.invoice_db.ItemMasterDTO;
import service.LoginService;
import service.LoginServiceImpl;
import service.ShoppingService;
import service.ShoppingServiceImpl;

public class ShoppingAction extends Action{
@Override
public String execute(HttpServletRequest request, HttpServletResponse response) {
	HttpSession session=request.getSession();
	String shopid=request.getParameter("shopid");
	String currentid=request.getParameter("currentid");
	Properties prop=(Properties)request.getServletContext().getAttribute("properties");
	ShoppingService shoppingService=ShoppingServiceImpl.getShoppingServiceImpl(prop);
	HashMap<Integer,Integer> hm;
	if(session.getAttribute("itemsselected")==null) {
		hm=new HashMap<Integer,Integer>();
	}
	else {
		 hm=(HashMap<Integer, Integer>) session.getAttribute("itemsselected");
	}
	if(currentid!=null) {
	String[] selectedItems = request.getParameterValues("selected");
	if(selectedItems!=null) {
	for(int i=0;i<selectedItems.length;i++) {
		String qty=request.getParameter(selectedItems[i]);
		hm.put(Integer.parseInt(selectedItems[i]),Integer.parseInt(qty));
	}
	session.setAttribute("itemsselected", hm);
	}
}
	Set<ItemMasterDTO> items=new HashSet<ItemMasterDTO>();
	items=shoppingService.loadAllItems(shopid);
	session.setAttribute("items", items);
	session.setAttribute(shopid, shopid);
	System.out.println(hm);
	System.out.println(shopid);
	return shopid;
}
}
