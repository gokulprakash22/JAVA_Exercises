package basicpack3;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.soap.MTOM;

@MTOM
@WebService(endpointInterface = "basicpack3.MtomService")
public class MtomServiceImpl implements MtomService{
	@Override
	public Image getImage(String name)throws Exception {
		File f=new File(name);
		return ImageIO.read(f);
	}
	@Override
	public void setImage(Image img,String name) {
		try {
		File fos=new File(name);
		BufferedImage bimage=(BufferedImage)img;
		ImageIO.write(bimage, "jpg", fos);
		}catch(Exception e){e.printStackTrace();}
	}
}