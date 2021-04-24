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
public class ExportFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("export do filter method called....");
		HttpServletRequest hrequest=(HttpServletRequest)request;
		HttpServletResponse hresponse=(HttpServletResponse)response;
		HttpSession session=hrequest.getSession(false);
		if(session!=null && ((session.getAttribute("customer")!=null)|| session.getAttribute("managementdetails")!=null)){
			chain.doFilter(request, response);
		}
		else {
			hresponse.sendRedirect("/accesserror");
		}
		
		
		
	}
	
	@Bean(name = "exportfilter")
	public FilterRegistrationBean<ExportFilter> exportFilter()
	    {
	        FilterRegistrationBean<ExportFilter> bean = new FilterRegistrationBean<>();
	        bean.setFilter(new ExportFilter());
	        bean.addUrlPatterns("/export/*");  
	        return bean;
	    }
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Export Filter....");
	}
	
	
	
}
