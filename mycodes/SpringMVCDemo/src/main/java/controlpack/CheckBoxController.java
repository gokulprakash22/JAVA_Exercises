package controlpack;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("checkbox")
public class CheckBoxController {
	
	private ItemListBean list;
	
	public CheckBoxController() {
		list=new ItemListBean();
	}
	public final ItemListBean getList() {
		return list;
	}
	public final void setList(ItemListBean list) {
		this.list = list;
	}

	@ModelAttribute("modelItemList")
	public List<String> getModel() {
		List<String> modelItemList=new ArrayList<>();
		modelItemList.add("rice");
		modelItemList.add("daal");
		modelItemList.add("groundnut");
		modelItemList.add("cashewnut");
		return modelItemList;
	}
	
	@RequestMapping(value="loadcheckform", method=RequestMethod.GET)
	public ModelAndView loadCheckBoxForm(ModelAndView mandv) {
		mandv.addObject("list",list);
		mandv.setViewName("checkboxform");
		return mandv;
	}
	
	@RequestMapping(value="submitcheckform", method=RequestMethod.POST)
	public ModelAndView submitCheckBoxForm(ItemListBean itemlist,ModelAndView mandv) {
		mandv.addObject("itemlist",itemlist.getItemlist());
		mandv.setViewName("display");
		return mandv;
	}
}
