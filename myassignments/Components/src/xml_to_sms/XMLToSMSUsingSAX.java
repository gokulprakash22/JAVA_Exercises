package xml_to_sms;

import com.twilio.*;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLToSMSUsingSAX {
	public static void main(String[] args) throws Exception{
		Object obj=new XMLToSMSUsingSAX();
		XMLToSMS createSMSobj=new XMLToSMS();
		obj=Proxy.newProxyInstance(XMLToSMSUsingSAX.class.getClassLoader(), new Class[] {SMS.class},new MyInvocationHandler(createSMSobj));
		SMS smsobj=(SMS)obj;
		smsobj.convertXMLToSMS("numbers");
	}
}
class MyInvocationHandler implements InvocationHandler
{
	Object obj;
	public MyInvocationHandler(Object obj) {
		this.obj=obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object o=method.invoke(obj, args);
		return o;
	}
}

interface SMS{
	public void convertXMLToSMS(String filename) throws ParserConfigurationException, SAXException, IOException;
}

class XMLToSMS implements SMS{

	  @Override
	  public void convertXMLToSMS(String filename) throws ParserConfigurationException, SAXException, IOException {
		  String ACCOUNT_SID = "ACed9066982355c8c63b965bff60292263";
		  String AUTH_TOKEN = "58fa646ac392d5e12027d5c525977ace";  
		  Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		    
		    SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			DefaultHandler handle = new DefaultHandler(){
				String elementValue;
				String to;
				String message;
				public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
					
				}
				@Override
				public void endElement(String uri, String localName, String qName) throws SAXException {
					switch(qName) {
					case "to":
						to = elementValue;
						break;
					case "message":
						message = elementValue;
						break;
					case "number":
						try {
							Message.creator(new PhoneNumber(to),new PhoneNumber("+19544407776"), message).create();
							System.out.println("Message ("+message+") Has Been Delivered to "+to+" Successfully.");
						}
						catch(Exception e) {
							e.printStackTrace();
						}
						
					}	
				}
				@Override
				public void characters(char[] ch, int start, int length) throws SAXException {
					elementValue = new String(ch,start,length);
				}
			};
			sp.parse(filename+".xml", handle);
	}
}