package com.filters;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.service.LoginService;



@Component
public class MySessionListener implements HttpSessionListener {
	
	@Autowired
	private LoginService service;
	
    public LoginService getService() {
		return service;
	}

	public void setService(LoginService service) {
		this.service = service;
	}

	public void sessionCreated(HttpSessionEvent se)  { 
        System.out.println("session created called.....:"+se.getSession());
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  {
    	System.out.println("session destroyed called...:");
    	HttpSession session=se.getSession();
    	try {
    		Integer uid=(Integer) session.getAttribute("uid");
    		if(uid!=null) {
    			service.updateFlag(uid, 0);
    			System.out.println("Logged Out");
    		}
    			
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		System.out.println("Logged Out Already");
    	}
    	
		
        
    }
	
}
