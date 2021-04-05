package control;
import java.util.Properties;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import service.LoginService;
import service.LoginServiceImpl;

@WebListener
public class MyListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent se)  { 
        System.out.println("created");
   }

   public void sessionDestroyed(HttpSessionEvent se)  { 
   	  System.out.println("destroyed");	 
   	  
   	  HttpSession session=se.getSession();
         Properties prop=(Properties)session.getServletContext().getAttribute("properties");
     	  LoginService loginService=LoginServiceImpl.getLoginServiceImpl(prop);
     	  
     	  Object n=session.getAttribute("custno");
     	  
     	  if(n!=null) {
     		 int custno=(int)n;
     		 loginService.updateCustomerFlag(custno, 0);
     	  }
   }
}
