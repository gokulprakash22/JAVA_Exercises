package demo;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
public class Client {
	public static void main(String[] args) throws Exception{
		
		URL url=new URL("http://localhost:5000/ws/hello?wsdl");
		
		QName qName=new QName("http://demo/","HelloServiceImplService");
		
		Service service=Service.create(url,qName);
		
		HelloService hservice=service.getPort(HelloService.class);
		
		String result=hservice.sayHello("ramu");
		System.out.println("The result is..:"+result);
	}
}
/*
 *
 * SOAPREQUEST
 * <soap:envelope>
	 * <soap:body>
		 * <sayHello>
		 * 	<arg0>ramu</arg0>
		 * </sayHello>
	 * </soap:body>
 * </soap:envelope>
 *
 * SOAPRESPONSE
 * <soap:envelope>
	 * <soap:body>
		 * <sayHelloResponse>
		 * 	<return>
		 * 			Welcome to Webservices...ramu.
		 *  </return>
		 * </sayHelloResponse>
	 * </soap:body>
 * </soap:envelope>
 */