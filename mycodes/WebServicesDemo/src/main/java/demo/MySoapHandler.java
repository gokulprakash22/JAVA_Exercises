package demo;
import java.io.PrintStream;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
public class MySoapHandler implements SOAPHandler<SOAPMessageContext>{
	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		try {
		System.out.println("handle message called for both request and response....");
		SOAPMessage sm=context.getMessage();
		PrintStream out=new PrintStream(System.out);
		sm.writeTo(out);
		out.println();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	@Override
	public boolean handleFault(SOAPMessageContext context) {
		System.out.println("handle fault is called for both req and res when fault occurs...");
		return false;
	}
	@Override
	public void close(MessageContext context) {
		System.out.println("called when request and response closes....");	
	}
	@Override
	public Set<QName> getHeaders() {
		System.out.println("called in the instantiation.....- headers.......");
		return null;
	}
	
}