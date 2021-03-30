package invoice_rmi_server;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import invoice_db.CustomerMasterDAOImpl;
import invoice_db.CustomerMasterDTO;
import invoice_db.DBUtility;
import invoice_db.InvoiceMasterDAOImpl;
import invoice_db.InvoiceMasterDTO;

interface Consignment{
	public boolean calculateDelivery(int invno);
}

class ConsignmentTracker implements Consignment{
	@Override
	public boolean calculateDelivery(int invno) {
		try {
			InvoiceMasterDTO invoicedetails = new InvoiceMasterDTO();
			InvoiceMasterDAOImpl invoicemasterdaoimpl = new InvoiceMasterDAOImpl(DBUtility.getConnection());
			invoicedetails = invoicemasterdaoimpl.getInvoiceMaster(invno);
			String invdate = invoicedetails.getInvdate();
			int custno = invoicedetails.getCustomerno();
			CustomerMasterDTO customerdetails = new CustomerMasterDTO();
			CustomerMasterDAOImpl customermasterdaoimpl = new CustomerMasterDAOImpl(DBUtility.getConnection());
			customerdetails = customermasterdaoimpl.getCustomerMaster(custno);
			String location = customerdetails.getCustomeraddress();
			
			int speed = 30;
			Date date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(invdate);
			System.out.println("Location: "+location);
			Properties prop=new Properties();
			prop.load(new FileInputStream("location.properties"));
			int distance = Integer.parseInt((String) prop.getProperty(location));
			int time = distance/speed;
			int noOfWorkingHours = 12;
			int noOfDays = time/noOfWorkingHours;
			int remainingHours = time - (noOfDays*noOfWorkingHours);
			System.out.println("Working Time: 6AM To 6PM");
			System.out.println("Time Taken To Deliver: "+noOfDays+" Working Days and "+remainingHours+" Working Hours");
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			String strDate;
			DateFormat dateFormat;
			dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			strDate = dateFormat.format(date);
			System.out.println("Ordered Date: "+strDate);
			System.out.println("Consignment Getting Ready. Taking 1Hr...");
			c.add(Calendar.HOUR_OF_DAY, 1);
			date = c.getTime();
			strDate = dateFormat.format(date);
			System.out.println("After 1Hr: "+strDate);
			String[] words;
			words=strDate.split("\\s");
		    Date time1 = new SimpleDateFormat("HH:mm:ss").parse(words[1]);
		    Calendar calendar1 = Calendar.getInstance();
		    calendar1.setTime(time1);
		    calendar1.add(Calendar.DATE, 1);
			String string2 = "06:00:00";
		    Date time2 = new SimpleDateFormat("HH:mm:ss").parse(string2);
		    Calendar calendar2 = Calendar.getInstance();
		    calendar2.setTime(time2);
		    calendar2.add(Calendar.DATE, 1);
		    String string3 = "18:00:00";
		    Date time3 = new SimpleDateFormat("HH:mm:ss").parse(string3);
		    Calendar calendar3 = Calendar.getInstance();
		    calendar3.setTime(time3);
		    calendar3.add(Calendar.DATE, 1);
		    Date x = calendar1.getTime();
		    if(x.after(calendar3.getTime())) {
		    	System.out.println("Working");
		    	c.add(Calendar.DATE, 1);
		    }
		    if(x.before(calendar2.getTime()) || x.after(calendar3.getTime())) {
		    	System.out.println("This is working");
		    	c.set(Calendar.HOUR_OF_DAY, 06); 
		        c.set(Calendar.MINUTE, 00);
		        c.set(Calendar.SECOND, 00); 
		        calendar1.setTime(time2);
		        x = calendar1.getTime();
		    }
		    int dayOfWeek;
			Date newdate;
			while(noOfDays>0) {	
				newdate = c.getTime();
				dateFormat = new SimpleDateFormat("dd/MM");
				strDate = dateFormat.format(newdate);
				if(strDate.equals("01/01")) {
					System.out.println(strDate+" - New Year Holiday");
					c.add(Calendar.DATE, 1);
					continue;
				}
				if(strDate.equals("26/01")) {
					System.out.println(strDate+" - Republic Day Holiday");
					c.add(Calendar.DATE, 1);
					continue;
				}
				if(strDate.equals("15/08")) {
					System.out.println(strDate+" - Independence Day Holiday");
					c.add(Calendar.DATE, 1);
					continue;
				}
				if(strDate.equals("02/10")) {
					System.out.println(strDate+" - Gandhi Jayanthi Holiday");
					c.add(Calendar.DATE, 1);
					continue;
				}
				dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
				if(dayOfWeek==7) {
					System.out.println(strDate+" - Saturday Holiday");
					c.add(Calendar.DATE, 1);
					continue;
				}
				if(dayOfWeek==1) {
					System.out.println(strDate+" - Sunday Holiday");
					c.add(Calendar.DATE, 1);
					continue;
				}
				System.out.println(strDate+" - Consignment Travelled");
				noOfDays--;
				c.add(Calendar.DATE, 1);
			}
			int differenceInMilliSeconds = (int)(Math.abs(time3.getTime() - x.getTime()));
			int remainingMilliSeconds = remainingHours*60*60*1000;
			if(differenceInMilliSeconds < remainingMilliSeconds) {
				c.add(Calendar.DATE, 1);
				newdate = c.getTime();
				dateFormat = new SimpleDateFormat("dd/MM");
				strDate = dateFormat.format(newdate);
				System.out.println(strDate+" - Consignment Travelled");
				c.set(Calendar.HOUR_OF_DAY, 06); 
		        c.set(Calendar.MINUTE, 00);
		        c.set(Calendar.SECOND, 00); 
				c.add(Calendar.MILLISECOND, remainingMilliSeconds-differenceInMilliSeconds);
			}
			else {
				c.add(Calendar.MILLISECOND, remainingMilliSeconds);
			}
			newdate = c.getTime();
			dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			strDate = dateFormat.format(newdate);
			System.out.println("Expected Delivery Time: "+strDate);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
