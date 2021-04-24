package com.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ManagerFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("Manager do filter method called....");
		HttpServletRequest hrequest=(HttpServletRequest)request;
		HttpServletResponse hresponse=(HttpServletResponse)response;
		HttpSession session=hrequest.getSession(false);
		if(session!=null && session.getAttribute("managementdetails")!=null) {
			chain.doFilter(request, response);
		}
		else {
			hresponse.sendRedirect("/accesserror");
		}
		
	}
	
	@Bean(name = "managerfilter")
	public FilterRegistrationBean<ManagerFilter> managerFilter()
	    {
	        FilterRegistrationBean<ManagerFilter> bean = new FilterRegistrationBean<>();
	        bean.setFilter(new ManagerFilter());
	        bean.addUrlPatterns("/manage/*");  
	        return bean;
	    }
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Manager Filter....");
	}
	

}
