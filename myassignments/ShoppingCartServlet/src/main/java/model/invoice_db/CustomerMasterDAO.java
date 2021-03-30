package model.invoice_db;
import java.util.Set;

public interface CustomerMasterDAO {
		public int insertCustomer(CustomerMasterDTO customerMasterDTO);
		public int deleteCustomer(int custno);
		public int updateCustomer(CustomerMasterDTO customerMasterDTO);
		public CustomerMasterDTO getCustomerMaster(int custno);
		public Set<CustomerMasterDTO> getCustomerMasterAll();
		public CustomerMasterDTO getCustomerMasterByEmail(String email);
		public int getCustnoByEmail(String email);
}
