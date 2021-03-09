package collections;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class CollectionsDemo6Properties {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties prop = new Properties();
//		prop.put("a1", "hello");
		prop.load(new FileInputStream("abc.properties"));
		Enumeration e = prop.elements();
		
		while(e.hasMoreElements()) {
			String value1 = (String)e.nextElement();
			String value2 = prop.getProperty("a1");
			String value3 = prop.getProperty("a2");
			System.out.println(value1+":"+value2+":"+value3);
		}
	}
}