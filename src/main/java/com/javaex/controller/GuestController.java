  
package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;


@Controller
@RequestMapping(value = "/guest")
public class GuestController {

	@Autowired
	private GuestDao guestDao;
	
	@RequestMapping(value = "list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {

		System.out.println("ok");

		List<GuestVo> guestVo = guestDao.getguestList();

		System.out.println("dao:get:"+guestVo.toString());
		System.out.println("dao:get:"+guestVo.get(0).getDate());
		model.addAttribute("gList", guestVo);

		return "list";
	}
	
	@RequestMapping(value = "write", method = { RequestMethod.GET, RequestMethod.POST })
	public String wtire(@ModelAttribute GuestVo guestVo) {
		
		System.out.println("write");
	
		guestDao.guestInsert(guestVo);
		return "redirect:/guest/list";
	}
	

	@RequestMapping(value = "confirmpassword", method = { RequestMethod.GET, RequestMethod.POST })
	public String confirmpassword() {
		
		System.out.println("confirmpassword:");
		return "confirmpassword";
	}
	
	@RequestMapping(value = "notsamepwd", method = { RequestMethod.GET, RequestMethod.POST })
	public String notsamepwd(@RequestParam("pwd") String pwd,
							 @RequestParam("where")String where, 
						 	 @RequestParam("no") int no) {
		
		System.out.println("notsamepwd: no = "+ no +" pwd = " +pwd +" where ="+ where);
		
		GuestVo guestVo = guestDao.getList(no);
		
		String pwd2 = guestVo.getPassword();
		
		System.out.println("notsamepwd: pwd = "+ pwd + "/"+"pwd2 = "+ pwd2);
	
		if(pwd.equals(pwd2)) {
			System.out.println("notnamepwd:equals");
			return "redirect:"+where+"/"+no;
			
		}else {
			System.out.println("notnamepwd:nequals");
			return "notsamepwd";
			
		}
	}
	
	@RequestMapping(value = "delete/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@PathVariable("no") int no) {
		System.out.println("delete: no = " + no);
		
		guestDao.guestDelete(no);
	
		return "redirect:/guest/list";
	}
	
	@RequestMapping(value = "modifyForm/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyform(@PathVariable("no") int no,Model model) {
		
		System.out.println("modifyForm: no = " + no);
		GuestVo guestVo = guestDao.getList(no);
		
		model.addAttribute("gList", guestVo);
		return "modify";
	}
	
	@RequestMapping(value = "modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute GuestVo guestVo) {
		
		System.out.println("modify");
		guestDao.guestupdate(guestVo);
		
		return "redirect:/guest/list";
	}
	

}