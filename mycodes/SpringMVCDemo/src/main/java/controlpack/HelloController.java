package controlpack;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("hello")
public class HelloController {
	@Autowired
	private MyBusiness mybusiness;
	
	@RequestMapping(value="hello1", method = RequestMethod.GET)
	public void sayHello() {
		System.out.println("Welcome to Spring MVC....");
	}
	
	@RequestMapping(value="hello2", method = RequestMethod.GET)
	public void sayHello2() {
		System.out.println("Welcome to Spring MVC..2");
	}
	
	@RequestMapping(value="hello3", method = RequestMethod.GET)
	public String sayHello3() {
		System.out.println("Welcome to Spring MVC....");
		mybusiness.doBusiness();
		return "hello3";
	}
	
	@RequestMapping(value="hello4", method = RequestMethod.GET)
	public ModelAndView sayHello4() {
		System.out.println("Welcome to Spring MVC....");
		Employee emp=mybusiness.doBusiness();
		ModelAndView mandv=new ModelAndView();
		mandv.setViewName("hello4");
		mandv.addObject("hello","hello world hello world...");
		mandv.addObject("emp",emp);
		return mandv;
	}
	
	@RequestMapping(value="hello5", method = RequestMethod.GET)
	public ModelAndView sayHello5(ModelAndView mandv) {
		System.out.println("Welcome to Spring MVC....");
		Employee emp=mybusiness.doBusiness();
		//ModelAndView mandv=new ModelAndView();
		mandv.setViewName("hello4");
		mandv.addObject("hello","hello world hello world.5..");
		mandv.addObject("emp",emp);
		return mandv;
	}
	@RequestMapping(value="hello6", method = RequestMethod.GET)
	public ModelAndView sayHello6(ModelAndView mandv,HttpServletRequest request) {
		System.out.println("Welcome to Spring MVC....");
		Employee emp=mybusiness.doBusiness();
		mandv.setViewName("hello4");
		request.setAttribute("hello","hello world hello world.6..");
		request.setAttribute("emp",emp);
		return mandv;
	}
	
	@RequestMapping(value="hello7/{value}", method = RequestMethod.GET)
	public ModelAndView sayHello7(@PathVariable String value, ModelAndView mandv,HttpServletRequest request) {
		System.out.println("Welcome to Spring MVC....");
		Employee emp=mybusiness.doBusiness();
		mandv.setViewName("hello4");
		request.setAttribute("hello",value);
		request.setAttribute("emp",emp);
		return mandv;
	}
	
}

class Employee{
	@Override
	public String toString() {
		return "this is employee object....";
	}
}
@Component
class MyBusiness{
	@Autowired
	private MyService myservice;
	
	public Employee doBusiness() {
		System.out.println("do business called...................");
		myservice.doService();
		return new Employee();
	}

	public MyService getMyservice() {
		return myservice;
	}

	public void setMyservice(MyService myservice) {
		this.myservice = myservice;
	}
}

@Service
class MyService{
	@Autowired
	private MyDao mydao;
	
	public void doService() {
		System.out.println("do service called......");
		mydao.doDao();
	}

	public MyDao getMydao() {
		return mydao;
	}

	public void setMydao(MyDao mydao) {
		this.mydao = mydao;
	}
}

@Repository
class MyDao{
	public void doDao() {
		System.out.println("do doa called.............");
	}
}