package basicpack2;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
public class Client {
	public static void main(String[] args) throws Exception{
		
		URL url=new URL("http://localhost:8888/ws/hello?wsdl");
		
		QName qName=new QName("http://basicpack2/","HelloWorldService");
		
		Service service=Service.create(url,qName);
		
		Hello hservice=service.getPort(Hello.class);
		Employee emp = new Employee();
		emp.setName("Ramu");
		emp.setAge(10);
		Employee result = hservice.sayHello(emp);
		System.out.println("The result is..:"+result);
	}
}