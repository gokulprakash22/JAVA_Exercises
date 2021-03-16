package com.xml.email_sending;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class App {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		ArrayList<ArrayList<String>> ar=new ArrayList<ArrayList<String>>();
		 DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			dbf.setIgnoringElementContentWhitespace(true);
			dbf.setValidating(true);
	        DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse("emails.xml");
	        Element rootElement=doc.getDocumentElement();
			for(int i=0;i<rootElement.getChildNodes().getLength();i++) {
				 ar.add(new ArrayList<String>()); 
				for(int j=0;j<rootElement.getChildNodes().item(i).getChildNodes().getLength();j++) {  
					ar.get(i).add(rootElement.getChildNodes().item(i).getChildNodes().item(j).getFirstChild().getNodeValue());
				}
			}
			System.out.println(ar.get(0).size());
		System.out.println("Initializing to send Email ...");
		String message = "";
		String subject = "";
		String to = "";
		String from = "gokulprakash22@gmail.com";
		for(int i=0;i<ar.size();i++)
		{
			to=ar.get(i).get(0);
			subject=ar.get(0).get(1);
			message=ar.get(0).get(2);
			sendEmail(message,subject,to,from);
		}
	}

	private static void sendEmail(String message, String subject, String to, String from) {
		String host="smtp.gmail.com";
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES "+properties);
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		Session session=Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {				
				return new PasswordAuthentication("gokulprakash22@gmail.com", "password");
			}
		});
		session.setDebug(true);
		MimeMessage m = new MimeMessage(session);
		try {
		m.setFrom(from);
		m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		System.out.println(Message.RecipientType.TO);
		m.setSubject(subject);
		m.setText(message);
		Transport.send(m);		
		System.out.println("Email Sucessfully Sent");
		}catch (Exception e) {
			e.printStackTrace();
		}
			
	}
}
