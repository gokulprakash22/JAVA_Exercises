package com;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class HelloServlet extends HttpServlet {
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init method called....");
		String value=config.getInitParameter("param1");
		System.out.println("Init param value...:"+value);
		
		
		Enumeration<String> e=config.getInitParameterNames();
		while(e.hasMoreElements()) {
			String name=e.nextElement();
			String value1=config.getInitParameter(name);
			System.out.println(name+":"+value1);
		}
		ServletContext ctx=config.getServletContext();//servletcontext is global object
		ctx.setAttribute("globalkey", "global value...");//this key you can read accross the servlets....
		String path=ctx.getRealPath("/WEB-INF/dbconfig.properties");
		System.out.println(path);
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do get called...");
		String uname=request.getParameter("uname");
		String pass=request.getParameter("upass");
		
		if(uname.equals("ramu")) {
			//response.sendRedirect("welcome.html");
			RequestDispatcher rd=request.getRequestDispatcher("welcome.html");
			rd.forward(request, response);
		}
		else {
			ServletOutputStream out=response.getOutputStream();
			response.setContentType("text/html");
			out.println("<h2>"+uname+":"+pass);
			
			Enumeration<String> e=request.getParameterNames();
			while(e.hasMoreElements()) {
				String name=e.nextElement();
				String value=request.getParameter(name);
				out.println(name+":"+value);
			}
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do post called...");
		doGet(request,response);		
	}
	@Override
	public void destroy() {
		System.out.println("destroy method...");
	}
	
}