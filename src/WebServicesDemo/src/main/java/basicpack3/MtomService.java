package basicpack3;

import java.awt.Image;

import javax.jws.WebService;

@WebService
//@SOAPBinding(style = Style.RPC)
interface MtomService {
	public Image getImage(String name)throws Exception;
	public void setImage(Image img,String name);
}
