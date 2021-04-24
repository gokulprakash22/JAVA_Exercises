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
public class StoreAdminFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("Store do filter method called....");
		HttpServletRequest hrequest=(HttpServletRequest)request;
		HttpServletResponse hresponse=(HttpServletResponse)response;
		HttpSession session=hrequest.getSession(false);
		if(session!=null && session.getAttribute("admindetails")!=null) {
			chain.doFilter(request, response);
		}
		else {
			hresponse.sendRedirect("/accesserror");
		}
		
	}
	
	@Bean(name="storeadminfilter")
	public FilterRegistrationBean<StoreAdminFilter> storeFilter()
	    {
	        FilterRegistrationBean<StoreAdminFilter> bean = new FilterRegistrationBean<>();
	        bean.setFilter(new StoreAdminFilter());
	        bean.addUrlPatterns("/store/*");  
	        return bean;
	    }
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Store admin Filter....");
	}
	
	

}
