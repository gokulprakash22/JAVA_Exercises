package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BusinessServer extends UnicastRemoteObject implements Business{
	public BusinessServer() throws RemoteException{
		
	}
	@Override
	public String getStockPrice(String stockName) throws RemoteException {
		if(stockName.equals("ey")) {
			return "The stock price is 1000";
		}
		else {
			return "The stock price is 500";
		}
		
	}
	
}
