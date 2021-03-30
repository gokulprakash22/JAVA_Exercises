package xml_to_email;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class XMLToEmailUsingSAX {
	public static void main(String[] args) throws Exception{
		Object obj=new XMLToEmailUsingSAX();
		XMLToEmail createEmailobj=new XMLToEmail();
		obj=Proxy.newProxyInstance(XMLToEmailUsingSAX.class.getClassLoader(), new Class[] {Email.class},new MyInvocationHandler(createEmailobj));
		Email emailobj=(Email)obj;
		emailobj.convertXMLToEmail("emails");
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

interface Email{
	public void convertXMLToEmail(String filename) throws Exception;
}

class XMLToEmail implements Email{
	@Override
	public void convertXMLToEmail(String filename) throws Exception{
        
        String from = "gokulprakash22@gmail.com";
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("gokulprakash22@gmail.com", "****");
            }
        });
        
        SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		DefaultHandler handle = new DefaultHandler(){
			String elementValue;
			String to;
			String subject;
			String message;
			public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
				
			}
			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				switch(qName) {
				case "to":
					to = elementValue;
					break;
				case "subject":
					subject = elementValue;
					break;
				case "message":
					message = elementValue;
					break;
				case "email":
					try {
						MimeMessage mail;
						mail = new MimeMessage(session);
						mail.setFrom(new InternetAddress(from));
						mail.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
						mail.setSubject(subject);
						mail.setText(message);
			            System.out.println("To: "+to);
			            System.out.println("Subject: "+subject);
			            System.out.println("Message: "+message);
			            System.out.println("Sending Mail");
			            Transport.send(mail);
			            System.out.println("Sent Mail Successfully....");
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
