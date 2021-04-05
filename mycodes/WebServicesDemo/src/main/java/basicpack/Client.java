package basicpack;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class Client {
	public static void main(String[] args) throws Exception {
		URL url =new URL("http://localhost:8888/ws/hello?wsdl");
		QName qname = new QName("http://basicpack/","HelloWorldService");
		Service service = Service.create(url,qname);
		Hello hello = service.getPort(Hello.class);
		System.out.println(hello.sayHello("Falah Technologies"));
	}
}