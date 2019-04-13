package com.happy.prj.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {
	@RequestMapping(value="/spring.do", method=RequestMethod.GET)
	public String helloCtrl(Model model, String test) {
		model.addAttribute("test", test);
		return "helloSpring";
	}
}
