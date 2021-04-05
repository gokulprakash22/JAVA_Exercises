package basicpack2;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class MyHandler implements SOAPHandler<SOAPMessageContext> {
	@Override
	public void close(MessageContext context) {
		// TODO Auto-generated method stub
	}
	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		Boolean result = (Boolean)context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if(result) {
			System.out.println("out bound message-response");
			try {
				SOAPMessage msg=context.getMessage();
				PrintStream
				out=new PrintStream(System.out);
				msg.writeTo(out);
				out.println();
				FileOutputStream os = new FileOutputStream("response.xml");
				msg.writeTo(os);
			}catch(Exception e){}
		}
		else if(!result) {
			System.out.println("in bound message-request");
			try {
				SOAPMessage msg=context.getMessage();
				PrintStream out = new PrintStream(System.out);
				msg.writeTo(out);
				FileOutputStream os = new FileOutputStream("request.xml");
				msg.writeTo(os);
			}catch(Exception e){}
		}
		System.out.println("Result...:"+result);
		return true;
	}
	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return false;
	}
	@Override
	public Set<QName>getHeaders() {
		return null;
	}
}