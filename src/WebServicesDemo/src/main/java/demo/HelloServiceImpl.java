package demo;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
@WebService(endpointInterface = "demo.HelloService")
@HandlerChain(file="handler-chain.xml")
public class HelloServiceImpl implements HelloService{
	
	@WebMethod
	@Override
	public String sayHello(String name) {
		// TODO Auto-generated method stub
		return "Welcome to SOAP based standard webservices....:"+name;
	}
}