package basicpack3;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

public class Client{
	public static void main(String[] args) throws
	Exception {
		URL url = new
		URL("http://localhost:8888/ws/hello?wsdl");
		QName qname = new QName("http://basicpack3/","MtomServiceImplService");
		Service service = Service.create(url, qname);
		MtomService mtom=service.getPort(MtomService.class);
		BindingProvider bp = (BindingProvider) mtom;
		SOAPBinding binding = (SOAPBinding) bp.getBinding();
		binding.setMTOMEnabled(true);
		System.out.println(new File("").getAbsoluteFile());
		Image img=ImageIO.read(new File("mountain.jpg"));
		mtom.setImage(img,"hills.jpg");
	}
}