package rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class BusinessAppLoader {
	public static void main(String[] args) throws Exception{
		LocateRegistry.createRegistry(1099); //registry service
		BusinessServer rmiService = new BusinessServer();
		
		System.out.println("RMI Business Sever Ready...");
		Naming.bind("rmi://localhost:1099/myservice", rmiService);
	}
}
