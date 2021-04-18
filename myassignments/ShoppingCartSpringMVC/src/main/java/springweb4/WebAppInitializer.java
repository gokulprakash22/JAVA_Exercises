package springweb4;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		AnnotationConfigWebApplicationContext webCfg=new AnnotationConfigWebApplicationContext();
		webCfg.setServletContext(servletContext);
		webCfg.register(SpringConfig.class);
		
		DispatcherServlet dispatcher=new DispatcherServlet(webCfg);
		
		Dynamic dispatcherServlet=servletContext.addServlet("dispatcher",dispatcher);
		dispatcherServlet.addMapping("/");
		dispatcherServlet.setLoadOnStartup(1);
		
	}
}
