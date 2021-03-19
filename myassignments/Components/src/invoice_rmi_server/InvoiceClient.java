package invoice_rmi_server;
import java.rmi.Naming;
import java.util.HashMap;
import java.util.Scanner;
public class InvoiceClient {
	public static void main(String[] args) throws Exception{
		Invoice inv=(Invoice)Naming.lookup("rmi://localhost:1099/myinvoiceapp");
		Scanner scan=new Scanner(System.in);
		System.out.println("Please select your choice...");
		System.out.println("1-Create new Invoice");
		System.out.println("2-Calculate Delivery date and time");
		System.out.println("3-Create PDF");
		System.out.println("4-Create Excel");
		System.out.println("5-Enter Items");
		int choice=scan.nextInt();
		int custno;
		int itemno;
		int itemqty=0;
		int invno=0;
		String itemdescription="";
		String itemunit="";
		int price;
		switch(choice) {
		case 1:{
			
			HashMap<Integer,Integer> itemmap=new HashMap<Integer,Integer>();
//			HashMap<Integer, HashMap<Integer, Integer>> invmap=new HashMap<Integer, HashMap<Integer, Integer>>();
			System.out.println("Enter Customer Number");
			custno=scan.nextInt();
			System.out.println("Enter itemno and item quantity");
			while(true)
			{
				System.out.print("itemno: ");
				itemno=scan.nextInt();
				if(itemno==-1)
					break;
				System.out.print("quantity: ");
				itemqty=scan.nextInt();
				itemmap.put(itemno,itemqty);
			}
			if(inv.createInvoice(custno,invno,itemmap))
				System.out.println("Invoice Created Successfully");
			
			break;
		}
		case 2:{
			
			break;
		}
		case 3:{
			System.out.println("Enter Invoice Number");
			invno=scan.nextInt();
			if(inv.createPDF(invno))
				System.out.println("PDF Created Successfully");
			break;
		}
		case 4:{
			System.out.println("Enter Invoice Number");
			invno=scan.nextInt();
			if(inv.createExcel(invno))
				System.out.println("Excel Created Successfully");
			break;
		}
		case 5:{
			System.out.println("Enter Item Description");
			itemdescription=scan.next();
			scan.nextLine();
			System.out.println("Enter Item Unit");
			itemunit=scan.nextLine();
			System.out.println("Enter Item Price");
			price=scan.nextInt();
			if(inv.enterItem(itemdescription, itemunit, price))
				System.out.println("Items Inserted Successfully");
			break;
		}
		default:
			System.out.println("wrong choice...");
		}
		
	}
}