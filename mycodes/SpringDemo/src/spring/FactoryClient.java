package spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryClient {
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx=new ClassPathXmlApplicationContext("config.xml");
		//new FileSystemXmlApplicationContext();
		
		try {
			ShoeSeller seller=ctx.getBean("advisedShop",ShoeSeller.class);
			
			Customer customer= new ShoeCustomer("ramu");
		
			Shoe shoe=seller.sellShoe(customer);
			seller.test();
//			seller.sellShoe(customer);
			
			System.out.println(shoe);
			
			Export export = (Export)seller;
			
			export.doExport();
//			System.out.println(seller.getFactory().getKey());
//			System.out.println(seller.getKey());
			ctx.close();
		} catch(Exception e) {
			System.out.println(e);
		}
		
	}
}
