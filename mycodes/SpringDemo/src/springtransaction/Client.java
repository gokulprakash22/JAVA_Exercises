package springtransaction;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("config3.xml");
		
		BusinessBean bean = ctx.getBean("businessadvisedbean", BusinessBean.class);
		bean.doTransaction(1, 2, 500);
		
		ctx.close();
	}
}
