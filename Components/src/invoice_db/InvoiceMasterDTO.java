package invoice_db;
import java.io.Serializable;
import java.util.Date;
import java.time.LocalDate;
public class InvoiceMasterDTO implements Serializable{
	public final int getInvno() {
		return invno;
	}
	public final void setInvno(int invno) {
		this.invno = invno;
	}
	public final String getInvdate() {
		return invdate;
	}
	public final void setInvdate(String date) {
		this.invdate = date;
	}
	public final int getCustomerno() {
		return customerno;
	}
	public final void setCustomerno(int customerno) {
		this.customerno = customerno;
	}
	private int invno;
	private String invdate;
	private int customerno;
	
	@Override
	public String toString() {
		return "InvoiceMasterDTO [invno=" + invno + ", invdate=" + invdate + ", customerno=" + customerno + "]";
	}
	
	
}