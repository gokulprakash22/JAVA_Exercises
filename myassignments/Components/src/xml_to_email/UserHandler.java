package xml_to_email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
class UserHandler extends DefaultHandler {

	   boolean sno = false;
	   boolean itemname = false;
	   boolean price = false;
	   boolean quantity = false;
	   boolean amount=false;
	   
	   String snovar = "";
	   String itemnamevar = "";
	   String pricevar = "";
	   String quantityvar = "";
	   String amountvar="";
	   
	   String messagevar="";
	   @Override
	   public void startElement(
	      String uri, String localName, String qName, Attributes attributes)
	      throws SAXException {
//		   System.out.println("Start Element "+qName);
	     if (qName.equalsIgnoreCase("sno")) {
	         sno = true;
	      } else if (qName.equalsIgnoreCase("itemname")) {
	         itemname = true;
	      } else if (qName.equalsIgnoreCase("price")) {
	         price = true;
	      } else if (qName.equalsIgnoreCase("quantity")) {
		         quantity = true;
		      }
	      else if (qName.equalsIgnoreCase("amount")) {
		         amount = true;
		      }
	   }

	   @Override
	   public void endElement(String uri, 
	      String localName, String qName) throws SAXException {
	      if (qName.equalsIgnoreCase("items")) {
	    	  sendEmail(messagevar,"Invoice Details","vishaalfeb2000@gmail.com","gokulprakash22@gmail.com");
	      }
	   }

	   @Override
	   public void characters(char ch[], int start, int length) throws SAXException {
//		   System.out.println(to+" "+subject+" "+message);
	      if(sno) {
	         snovar=new String(ch, start, length);
	         sno = false;
	         messagevar+="Sno: "+snovar+"\n";
	         
	      } else if (itemname) {;
	         itemnamevar=new String(ch, start, length);
	         itemname = false;
	         messagevar+="ItemName: "+itemnamevar+"\n";
	      } else if (price) {
	         pricevar=new String(ch, start, length);
	         price = false;
	         messagevar+="Price: "+pricevar+"\n";
	      } 
	      else if (quantity) {
		         quantityvar=new String(ch, start, length);
		         quantity = false;
		         messagevar+="Quantity: "+quantityvar+"\n";
		      } 
	      else if (amount) {
		         amountvar=new String(ch, start, length);
		         amount = false;
		         messagevar+="Amount: "+amountvar+"\n";
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