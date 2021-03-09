package xml;

import java.io.FileWriter;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLDemo2 {
	public static void main(String[] args) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(true);
		dbf.setIgnoringElementContentWhitespace(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse("books.xml");
		
		Element root = doc.getDocumentElement();
		
		Node newNode = doc.createElement("newElement");
		Node newData = doc.createTextNode("new data");
		Node newAttr = doc.createAttribute("bid");
		Node newAttrValue = doc.createTextNode("a11");
		
		newAttr.appendChild(newAttrValue);
		newNode.getAttributes().setNamedItem(newAttr);
		
		newNode.appendChild(newData);
		root.insertBefore(newNode.cloneNode(true), root.getFirstChild());
		

		Transformer transform=TransformerFactory.newInstance().newTransformer();
		transform.setOutputProperty(OutputKeys.INDENT, "yes");
		
		DOMSource domSource=new DOMSource(doc);
		
		StringWriter sw=new StringWriter();
		StreamResult sr=new StreamResult(sw);
		
		transform.transform(domSource, sr);
		
		System.out.println(sw.toString());
		
		FileWriter out=new FileWriter("newbooks.xml");
		out.write(sw.toString());
		out.close();
	}
}