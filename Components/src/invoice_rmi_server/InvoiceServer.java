package invoice_rmi_server;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Date;

import invoice_db.CustomerMasterDAOImpl;
import invoice_db.CustomerMasterDTO;
import invoice_db.DBUtility;
import invoice_db.InvoiceMasterDAOImpl;
import invoice_db.InvoiceMasterDTO;
import invoice_db.ItemMasterDAOImpl;
import invoice_db.ItemMasterDTO;
import invoice_db.ItemTransactionMasterDAOImpl;
import invoice_db.ItemTransactionMasterDTO;

public class InvoiceServer extends UnicastRemoteObject implements Invoice{

	private static final long serialVersionUID = 1L;
	public InvoiceServer() throws RemoteException{}
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	Scanner scan = new Scanner(System.in);
	
	@Override
	public void startInvoiceApp(int choice) throws RemoteException {
		Object obj = new InvoiceServer();
		obj = Proxy.newProxyInstance(InvoiceServer.class.getClassLoader(),  new Class[] {Consignment.class, PDF.class, Excel.class},  new MyInvocationHandler( new Object[] {new ConsignmentTracker(), new XMLToPDF(),new XMLToExcel()}));
		try {
			int invno;
			switch(choice) {
			case 1:
				createInvoice();
				break;
			case 2:
				Consignment consignmentobj = (Consignment)obj;
				System.out.println("Enter Invoice Number");
				invno = Integer.parseInt(input.readLine());
				consignmentobj.calculateDelivery(invno);
				break;
			case 3:
				System.out.println("Enter Invoice Number");
				invno = Integer.parseInt(input.readLine());
				createXML(invno);
				PDF pdfobj = (PDF)obj;
				pdfobj.convertXMLToPDF(String.valueOf(invno));
				break;
			case 4:
				System.out.println("Enter Invoice Number");
				invno = Integer.parseInt(input.readLine());
				createXML(invno);
				Excel excelobj = (Excel)obj;
				excelobj.convertXMLToExcel(String.valueOf(invno));
				break;
			case 5:
				addItem();
				break;
			case 6:
				addCustomer();
				break;
			default:
				System.out.println("Invalid Input");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	class MyInvocationHandler implements InvocationHandler {
		Object obj[];
		public MyInvocationHandler(Object obj[]) {
			this.obj = obj;
		}
			
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Object object = null;
			for(Object o: obj) {
				Method methods[] = o.getClass().getDeclaredMethods();
				for(Method m: methods) {
					if(m.getName().equals(method.getName())) {
						m.setAccessible(true);
						object = method.invoke(o, args);
					}
				}
			}
			return object;
		}
	}

public int createInvoice() throws Exception{
	System.out.println("Enter Customer Number");
	int custno = Integer.parseInt(input.readLine());
	HashMap<Integer,Integer> itemmap = new HashMap<Integer,Integer>();
	System.out.println("Enter Itemno and Item Quantity");
	int itemno;
	int itemqty;
	while(true)
	{
		System.out.print("Itemno: ");
		itemno = Integer.parseInt(input.readLine());
		if(itemno==-1)
			break;
		System.out.print("Quantity: ");
		itemqty = Integer.parseInt(input.readLine());
		itemmap.put(itemno,itemqty);
	}
	System.out.println("Creating Invoice");
	InvoiceMasterDAOImpl invoicemasterdaoimpl=new InvoiceMasterDAOImpl(DBUtility.getConnection());
	InvoiceMasterDTO invoicemasterdto=new InvoiceMasterDTO();
	invoicemasterdto.setCustomerno(custno);
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
    Date date = new Date();
	invoicemasterdto.setInvdate(formatter.format(date));
	int invno= invoicemasterdaoimpl.insertInvoice(invoicemasterdto);
	for (Map.Entry<Integer,Integer> entry : itemmap.entrySet())  {
		ItemTransactionMasterDTO itemtransactionmasterdto=new ItemTransactionMasterDTO();
		itemtransactionmasterdto.setInvno(invno);
		itemtransactionmasterdto.setItemno(entry.getKey());
		itemtransactionmasterdto.setItemqty(entry.getValue());
		ItemTransactionMasterDAOImpl itemtransactionmasterdaoimpl=new ItemTransactionMasterDAOImpl(DBUtility.getConnection());
		itemtransactionmasterdaoimpl.insertItem(itemtransactionmasterdto);
	}
	System.out.println("Invoice Created Succesfully");
	return invno;
}

public int addItem() throws Exception{
	System.out.println("Enter Item Description");
	String itemdescription = input.readLine();
	System.out.println("Enter Item Unit");
	String itemunit = input.readLine();
	System.out.println("Enter Item Price");
	int price = Integer.parseInt(input.readLine());
	System.out.println("Adding Item");
	ItemMasterDTO itemmasterdto=new ItemMasterDTO();
	itemmasterdto.setItemdescription(itemdescription);
	itemmasterdto.setItemprice(price);
	itemmasterdto.setItemunit(itemunit);
	ItemMasterDAOImpl itemmasterdaoimpl=new ItemMasterDAOImpl(DBUtility.getConnection());
	int itemno = itemmasterdaoimpl.insertItemDetails(itemmasterdto);
	System.out.println("Item Added Successfully");
	return itemno;
}

public int addCustomer() throws Exception{
	System.out.println("Enter Customer Name");
	String customername = input.readLine();
	System.out.println("Enter Customer Address");
	String customeraddress = input.readLine();
	System.out.println("Enter Customer Email");
	String customeremail = input.readLine();
	System.out.println("Enter Customer Phone Number");
	String customerphone = input.readLine();
	System.out.println("Adding Item");
	CustomerMasterDTO customermasterdto=new CustomerMasterDTO();
	customermasterdto.setCustomername(customername);
	customermasterdto.setCustomeraddress(customeraddress);
	customermasterdto.setCustomeremail(customeremail);
	customermasterdto.setCustomerphone(customerphone);
	CustomerMasterDAOImpl customermasterdaoimpl=new CustomerMasterDAOImpl(DBUtility.getConnection());
	int custno = customermasterdaoimpl.insertCustomer(customermasterdto);
	System.out.println("Item Added Successfully");
	return custno;
}

public void createXML(int invno) {
	System.out.println("Creating XML");
	StringBuilder sb=new StringBuilder();
	InvoiceMasterDTO invoicedetails=new InvoiceMasterDTO();
	InvoiceMasterDAOImpl invoicemasterdaoimpl=new InvoiceMasterDAOImpl(DBUtility.getConnection());
	invoicedetails=invoicemasterdaoimpl.getInvoiceMaster(invno);
	String date=invoicedetails.getInvdate();
	int custno=invoicedetails.getCustomerno();
	ItemTransactionMasterDAOImpl itemtransactionmasterdaoimpl=new ItemTransactionMasterDAOImpl(DBUtility.getConnection());
	Set<ItemTransactionMasterDTO> itemtransactiondetails=new HashSet<ItemTransactionMasterDTO>();
	itemtransactiondetails=itemtransactionmasterdaoimpl.getItemTransactionMasterAllByInvno(invno);
	CustomerMasterDAOImpl customermasterdaoimpl=new CustomerMasterDAOImpl(DBUtility.getConnection());
	CustomerMasterDTO customerobj=new CustomerMasterDTO();
	customerobj=customermasterdaoimpl.getCustomerMaster(custno);
	sb.append("<!DOCTYPE invoice SYSTEM \"invoice.dtd\">");
	sb.append("<invoice>");
	sb.append("<customer>");
	sb.append("<customerno>"+custno+"</customerno>");
	sb.append("<customername>"+customerobj.getCustomername()+"</customername>");
	sb.append("<customeraddress>"+customerobj.getCustomeraddress()+"</customeraddress>");
	sb.append("<customeremail>"+customerobj.getCustomeremail()+"</customeremail>");
	sb.append("<customerphone>"+customerobj.getCustomerphone()+"</customerphone>");
	sb.append("</customer>");
	sb.append("<items>");
	double sum=0;
	for (ItemTransactionMasterDTO i : itemtransactiondetails) {
		sb.append("<item>"); 
        sb.append("<itemno>"+i.getItemno()+"</itemno>");       
        ItemMasterDAOImpl itemmasterdaoimpl=new ItemMasterDAOImpl(DBUtility.getConnection());
        ItemMasterDTO itemdetails=new ItemMasterDTO();
        itemdetails=itemmasterdaoimpl.getItemMaster(i.getItemno());
        sb.append("<itemdescription>"+itemdetails.getItemdescription()+"</itemdescription>");
        sb.append("<itemunit>"+itemdetails.getItemunit()+"</itemunit>");
        sb.append("<itemprice>"+itemdetails.getItemprice()+"</itemprice>");
        sb.append("<itemqty>"+i.getItemqty()+"</itemqty>");
        double total = itemdetails.getItemprice()*i.getItemqty();
        sum+=total;
        sb.append("<itemtotal>"+total+"</itemtotal>");
        sb.append("</item>");
	  }
	sb.append("</items>");
	sb.append("<invdetails>");
	sb.append("<invno>"+invno+"</invno>");
	sb.append("<date>"+date+"</date>");
	sb.append("<total>"+sum+"</total>");
	sb.append("</invdetails>");
	sb.append("</invoice>");
    try {
		File output = new File(invno+".xml");
		FileWriter fwriter = new FileWriter(output);
		fwriter.write(sb.toString());
		fwriter.flush();
		fwriter.close();
    }
    catch(Exception e) {
    	e.printStackTrace();
    }
    System.out.println("XML Created Successfully");
}
}