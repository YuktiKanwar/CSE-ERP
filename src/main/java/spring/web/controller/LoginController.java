package spring.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping(value = { "/"}, method = RequestMethod.GET)
	public ModelAndView welcomePage(@RequestParam(value = "error",required = false) String error,
			@RequestParam(value = "logout",	required = false) String logout) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid Credentials provided.");
		}

		if (logout != null) {
			model.addObject("message", "Logged out from ERPApp successfully.");
		}
		model.setViewName("welcomePage");
		return model;
	}

	@RequestMapping(value = { "/homePage"}, method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("homePage");
		return model;
	}
	
	@RequestMapping(value = { "/adminPage"}, method = RequestMethod.GET)
	public ModelAndView newPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("adminPage");
		return model;
	}
	
	@RequestMapping(value = { "/403"}, method = RequestMethod.GET)
	public ModelAndView accessDeniedPage(Principal user) {
		
		ModelAndView model = new ModelAndView();
		
		if (user != null) {
			model.addObject("msg",user.getName());
		}
		
		model.setViewName("handlers/403");
		return model;
	}
	


}