package invoice_rmi_server;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
public class InvoiceAppLoader {
	public static void main(String[] args)throws Exception {
		InvoiceServer is=new InvoiceServer();
		
		LocateRegistry.createRegistry(1099);
		System.out.println("Invoice app ready...");
		
		Naming.bind("rmi://localhost:1099/myinvoiceapp", is);
	}
}