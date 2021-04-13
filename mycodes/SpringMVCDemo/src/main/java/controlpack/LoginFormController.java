package controlpack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("login")
public class LoginFormController {
	
	@RequestMapping(value="loadform", method = RequestMethod.GET)
	public ModelAndView loadForm(ModelAndView mandv) {
		LoginFormBean lfb=new LoginFormBean();
		mandv.addObject("lfb",lfb);
		mandv.setViewName("login");
		return mandv;
	}
	
	@RequestMapping(value="submitform", method = RequestMethod.POST)
	public ModelAndView submitForm(LoginFormBean loginForm,ModelAndView mandv) {
		System.out.println(loginForm.getUname()+":"+loginForm.getUpass());
		mandv.addObject("lfb",loginForm);
		mandv.setViewName("welcome");
		return mandv;
	}
}
