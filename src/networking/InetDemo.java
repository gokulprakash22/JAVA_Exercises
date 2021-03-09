package networking;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;

public class InetDemo {
	public static void main(String[] args)throws Exception {
		 InetAddress ineta=InetAddress.getByName("www.irctc.co.in");
		
		 System.out.println(ineta.getHostAddress());
		
		 InetAddress inetarra[]=InetAddress.getAllByName("www.irctc.co.in");
		 for(InetAddress a:inetarra) {
			 System.out.println(a.getHostAddress());
		 }
		
		 InetAddress intetname=InetAddress.getByAddress(new byte[]{103,10,10,18});
		
		 //Inet6Address.get
		
		 System.out.println(intetname.getHostName());
	 }
} 