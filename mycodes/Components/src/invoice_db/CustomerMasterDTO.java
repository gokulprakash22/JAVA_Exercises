package invoice_db;

import java.io.Serializable;
import java.sql.Connection;

public class CustomerMasterDTO implements Serializable{
	private int customerno;
	private String customername;
	private String customeraddress;
	private String customeremail;
	private String customerphone;
	private String customerpassword;
	private int customerflag;
	public final int getCustomerno() {
		return customerno;
	}
	public final void setCustomerno(int customerno) {
		this.customerno = customerno;
	}
	public final String getCustomername() {
		return customername;
	}
	public final void setCustomername(String customername) {
		this.customername = customername;
	}
	public final String getCustomeremail() {
		return customeremail;
	}
	public final void setCustomeremail(String customeremail) {
		this.customeremail = customeremail;
	}
	public final String getCustomerphone() {
		return customerphone;
	}
	public final void setCustomerphone(String customerphone) {
		this.customerphone = customerphone;
	}
	public final String getCustomeraddress() {
		return customeraddress;
	}
	public final void setCustomeraddress(String customeraddress) {
		this.customeraddress = customeraddress;
	}
	public final String getCustomerpassword() {
		return customerpassword;
	}
	public final void setCustomerpassword(String customerpassword) {
		this.customerpassword = customerpassword;
	}
	public final int getCustomerflag() {
		return customerflag;
	}
	public final void setCustomerflag(int customerflag) {
		this.customerflag = customerflag;
	}
	@Override
	public String toString() {
		return "CustomerMasterDTO [customerno=" + customerno + ", customername=" + customername + ", customeraddress="
				+ customeraddress + ", customeremail=" + customeremail + ", customerphone=" + customerphone + "customerpassword=" + customerpassword + "customerflag=" + customerflag +"]";
	}
	
	
}
