package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Business extends Remote{
	public String getStockPrice(String stockName) throws RemoteException;
}
