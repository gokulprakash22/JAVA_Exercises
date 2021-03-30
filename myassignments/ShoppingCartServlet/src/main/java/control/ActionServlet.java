package control;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionServlet extends HttpServlet {
	RequestProcessor rp;Properties prop;
	@Override
		public void init(ServletConfig config) throws ServletException {
		try {
			String file=config.getInitParameter("config");
			String dbfile=config.getInitParameter("dbconfig");
			String path=config.getServletContext().getRealPath(file);
			String dbpath=config.getServletContext().getRealPath(dbfile);
			config.getServletContext().setAttribute("path", path);
			prop=new Properties();
			prop.load(new FileInputStream(dbpath));
			Class.forName(prop.getProperty("driver"));
			config.getServletContext().setAttribute("properties", prop);
			this.rp=new RequestProcessor();
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.rp.process(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}