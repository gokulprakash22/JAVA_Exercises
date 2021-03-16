package internationalization;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class InternationalizationDemo2 {
	public static void main(String[] args) {
		NumberFormat numberFormatter=NumberFormat.getCurrencyInstance(new Locale("en","US"));
		int i=2_0_0_0_0_3_42;
		System.out.println(String.format("%s",numberFormatter.format(i)));
		
		DateFormat dateformatter=DateFormat.getDateInstance(DateFormat.SHORT,new Locale("en","US"));
		Date date=new Date();
		System.out.println(dateformatter.format(date));
		//please do R&D on these patterns....
		DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("d-MMM-YYYY",new Locale("en","US"));
		String currentDate=LocalDate.now().format(dateTimeFormatter);
		System.out.println(currentDate);
	}
}
