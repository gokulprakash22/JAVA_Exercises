package basicpack2;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService(endpointInterface="basicpack2.Hello",name="myservice")
@HandlerChain(file="handler-chain.xml")
public class HelloWorld implements Hello {
public Employee sayHello(Employee emp) {
	System.out.println("Say hello called................");
	System.out.println("Welcome to Webservices....:"+emp.getName()+":"+emp.getAge());
	return emp;
	}
}
@WebService
interface Hello {
	@WebMethod
	public Employee sayHello(Employee emp);
}