package invoice_rmi_server;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;

public class InvoiceClient {
	public static void main(String[] args) throws Exception{
		Invoice inv=(Invoice)Naming.lookup("rmi://localhost:1099/myinvoiceapp");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please select your choice...");
		System.out.println("1-Create New Invoice");
		System.out.println("2-Calculate Delivery Date and Time");
		System.out.println("3-Generate PDF");
		System.out.println("4-Generate Excel");
		System.out.println("5-Add Items");
		System.out.println("6-Add Customers");
		int choice = Integer.parseInt(input.readLine());
		inv.startInvoiceApp(choice);
	}
}
