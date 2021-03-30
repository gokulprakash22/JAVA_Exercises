package invoice_rmi_server;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Invoice extends Remote{
	public void startInvoiceApp(int choice) throws RemoteException;
}