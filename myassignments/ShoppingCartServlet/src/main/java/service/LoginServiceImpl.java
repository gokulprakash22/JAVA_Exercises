package service;

import java.util.Properties;

import model.invoice_db.DBUtility;
import model.invoice_db.CustomerMasterDAO;
import model.invoice_db.CustomerMasterDAOImpl;
import model.invoice_db.CustomerMasterDTO;

public class LoginServiceImpl implements LoginService,Cloneable{
	Properties prop;
	public LoginServiceImpl(Properties prop) {
		this.prop=prop;
	}
	private static LoginServiceImpl loginServiceImpl;
	
	public static LoginServiceImpl getLoginServiceImpl(Properties prop) {
		if(loginServiceImpl==null) {
			loginServiceImpl=new LoginServiceImpl(prop);
			return loginServiceImpl;
		}
		else {
			return loginServiceImpl.createClone();
		}
	}
	public LoginServiceImpl createClone() {
		try {
			return (LoginServiceImpl)super.clone();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int authenticateUser(String email, String pass) {
		CustomerMasterDAO customerMasterDAOImpl = CustomerMasterDAOImpl.getCustomerMasterDAOImpl(prop);
		CustomerMasterDTO customerMasterDTO = customerMasterDAOImpl.getCustomerMasterByEmail(email);
		
		if(customerMasterDTO!=null) {
			if(customerMasterDTO.getCustomerpassword().equals(pass)) {
				return customerMasterDTO.getCustomerno();
			}
			return -1;
		}
		return -1;
	}

	@Override
	public boolean checkUserNotLogedIn(int custno) {
		CustomerMasterDAO customerMasterDAOImpl = CustomerMasterDAOImpl.getCustomerMasterDAOImpl(prop);
		CustomerMasterDTO customerMasterDTO = customerMasterDAOImpl.getCustomerMaster(custno);
		if(customerMasterDTO!=null) {
			if(customerMasterDTO.getCustomerflag()==0) {
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public int updateCustomerFlag(int custno, int flag) {
		CustomerMasterDAO customerMasterDAOImpl = CustomerMasterDAOImpl.getCustomerMasterDAOImpl(prop);
		CustomerMasterDTO customerMasterDTO = customerMasterDAOImpl.getCustomerMaster(custno);
		if(customerMasterDTO!=null) {
			customerMasterDTO.setCustomerflag(flag);
			return customerMasterDAOImpl.updateCustomer(customerMasterDTO);		
		}
		else {
			return 0;
		}
		
			
	}
	@Override
	public int registerNewCustomer(CustomerMasterDTO customerMasterDTO) {
		CustomerMasterDAO customerMasterDAOImpl = CustomerMasterDAOImpl.getCustomerMasterDAOImpl(prop);
		return customerMasterDAOImpl.insertCustomer(customerMasterDTO);
	}
	
	@Override
	public String getCustomerName(int custno) {
		CustomerMasterDAO customerMasterDAOImpl = CustomerMasterDAOImpl.getCustomerMasterDAOImpl(prop);
		CustomerMasterDTO customerMasterDTO = customerMasterDAOImpl.getCustomerMaster(custno);
		if(customerMasterDTO!=null) {
			return customerMasterDTO.getCustomername();
		}
		return null;
	}

}