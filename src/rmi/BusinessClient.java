package rmi;

import java.rmi.Naming;

public class BusinessClient {
	public static void main(String[] args) throws Exception{
		Business business = (Business)Naming.lookup("rmi://localhost:1099/myservice");
		System.out.println(business.getStockPrice("ey"));
	}
}
