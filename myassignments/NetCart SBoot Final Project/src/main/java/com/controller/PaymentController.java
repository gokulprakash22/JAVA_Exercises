package com.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.Customer;
import com.model.Items;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
@Controller
@RequestMapping("payment")
public class PaymentController {

	private RazorpayClient client;
	private String apiKey;
	private String secretKey;
	
	public PaymentController() {
	    this.apiKey = "rzp_test_px3hQBNFEYVrmD";
	    this.secretKey = "eUAreUDfuc5FTmw6YWRJkG5R";
	    try {
	      this.client = new RazorpayClient(this.apiKey, this.secretKey);
	      System.out.println("done");
	    }
	    catch (RazorpayException e) {
	      e.printStackTrace();
	    }
	}
	
	  @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="start", method=RequestMethod.GET)
	  public ModelAndView getPaymentForm(HttpServletRequest request,ModelAndView mandv) throws RazorpayException {
		HttpSession session=request.getSession(false);
		int amount = 0;
		HashMap<Items,Integer> hm=(HashMap<Items,Integer>)session.getAttribute("itemmap");
		for(Map.Entry item:hm.entrySet()) {
			Items key=(Items)item.getKey();int qty=(int)item.getValue();
			amount+=(key.getItemprice()*qty);
		}
		amount+=200+(0.05)*amount;
		amount*=100;
		System.out.println(amount);
	    JSONObject options = new JSONObject();
	    options.put("amount", amount);
	    options.put("currency", "INR");
	    options.put("receipt", "txn_123456");
	    options.put("payment_capture", 1);
	    Order order = client.Orders.create(options);
	    mandv.addObject("amount",amount);
	    mandv.addObject("razorpayOrderId",(String) order.get("id"));
	    mandv.setViewName("checkout");
		return mandv;
	  }
	 
	  @RequestMapping(value="verify" , method=RequestMethod.POST)
	  public ModelAndView getVerified(ModelAndView mandv, HttpServletRequest request){
		  String razorpay_payment_id=request.getParameter("razorpay_payment_id");
		  String razorpay_order_id=request.getParameter("razorpay_order_id");
		  String razorpay_signature=request.getParameter("razorpay_signature");
		  JSONObject options = new JSONObject();
		  options.put("razorpay_order_id", razorpay_order_id);
		  options.put("razorpay_payment_id", razorpay_payment_id);
		  options.put("razorpay_signature", razorpay_signature);
		  try {
			  if(Utils.verifyPaymentSignature(options, "eUAreUDfuc5FTmw6YWRJkG5R")) {
				  mandv.setViewName("redirect:/shop/loadInvoice");
			  	  return mandv;
			  }
			  else {
				  mandv.setViewName("start");
			  	  return mandv;
			  }
		  }
		  catch(Exception e) {
			  mandv.setViewName("start");
		  	  return mandv;
		  }
	    
	  }

}