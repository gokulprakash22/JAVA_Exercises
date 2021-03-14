import com.twilio.*;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class App {
		//sms sent using twillo 
	  static ArrayList<ArrayList<String>> ar=new ArrayList<ArrayList<String>>();
	  public static final String ACCOUNT_SID = "AC1f50b87bb7c56d21d8df8afcecee9877";
	  public static final String AUTH_TOKEN = "13e3e4aec985f8938393a7ce2befe3ca";

	  public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		    DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			dbf.setIgnoringElementContentWhitespace(true);
			dbf.setValidating(true);
	        DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse("C:\\Java\\java_training\\SMSSending\\numbers.xml");
	        Element rootElement=doc.getDocumentElement();
			for(int i=0;i<rootElement.getChildNodes().getLength();i++) {
				ar.add(new ArrayList<String>()); 
				for(int j=0;j<rootElement.getChildNodes().item(i).getChildNodes().getLength();j++) {  
					ar.get(i).add(rootElement.getChildNodes().item(i).getChildNodes().item(j).getFirstChild().getNodeValue());
				}
			
			}
			String to="";
			String message="";
			for(int i=0;i<ar.size();i++)
			{
				to=ar.get(i).get(0);
				message=ar.get(0).get(1);
			    Message messageobj = Message.creator(new PhoneNumber(to),
				        new PhoneNumber("+12107022886"), 
				        message).create();

				    System.out.println(messageobj.getSid());
			}

		  }
	  
}