package com.service;

import java.util.HashMap;
import java.util.List;

import com.model.CustomerMaster;
import com.model.InvoiceMaster;
import com.model.ItemMaster;
import com.model.TotalDetails;

public interface ShoppingService {
	public List<ItemMaster> getItemsByCategory(String category);
	public TotalDetails createInvoice(CustomerMaster customerMaster, HashMap<Integer,Integer> hm);
}
