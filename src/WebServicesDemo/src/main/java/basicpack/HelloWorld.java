package basicpack;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService(endpointInterface="basicpack.Hello",name="myservice")
public class HelloWorld implements Hello {
	public String sayHello(String name) {
		System.out.println("Say hello called................");
		return "Welcome to webservices…:"+name;
	}
}
@WebService
interface Hello {
	@WebMethod
	public String sayHello(String name);
	}
