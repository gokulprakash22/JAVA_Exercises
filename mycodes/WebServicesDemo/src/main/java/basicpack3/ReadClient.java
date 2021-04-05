package basicpack3;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class ReadClient {
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://localhost:8888/ws/hello?wsdl");
		QName qname = new QName("http://basicpack3/","MtomServiceImplService");
		Service service = Service.create(url, qname);
		MtomService mtom=service.getPort(MtomService.class);
		Image img= mtom.getImage("hills.jpg");
		JFrame frame = new JFrame();
		frame.setSize(300, 300);
		JLabel label = new JLabel(new ImageIcon(img));
		frame.add(label);
		frame.setVisible(true);
	}
}