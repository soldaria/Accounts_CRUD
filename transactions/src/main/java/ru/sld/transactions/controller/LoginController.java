package ru.sld.transactions.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import ru.sld.transactions.model.Account;
import ru.sld.transactions.model.User;
import ru.sld.transactions.service.UserService;

@Controller
@SessionAttributes("user")
public class LoginController {

  @Autowired
  private UserService userService;  
  
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView entry() {
	return new ModelAndView("login","user",new User());
  }
  
  @RequestMapping(value = "/{locale:en|ru}/login", method = RequestMethod.GET)
  public ModelAndView entryLocale() {
	return new ModelAndView("login","user",new User());
  }
  
  @RequestMapping(value = "/check-user", method = RequestMethod.POST)
  public String checkLogin(	@ModelAttribute("user") User user,
		  					Map<String, Object> model) {	 
	  
	  User userExist = userService.validateUser(user);	  
	  if (userExist != null) {		  
		  model.put("user", userExist);	  	
	      return "redirect:/home";
	  }
	  else{
		  return "login"; 
	  }
  }
  
  @RequestMapping(value = "/home", method = RequestMethod.GET)
  public String home(	Map<String, Object> model,
		  				HttpServletRequest request) {	 
	  Account acc = new Account();
	  User user = (User)request.getSession().getAttribute("user");      
	  this.userService.refreshUser(user);
      model.put("acc",acc);
      model.put("accList", user.getAccounts()); 
      if (user.isAdmin())
    	  model.put("userList", userService.listUser()); 
	  return "home";
  }  
  
  @RequestMapping(value = "/check-in", method = RequestMethod.POST)
  public ModelAndView addUser(@ModelAttribute("user") User user) {	 
	  return new ModelAndView("home", "user", user);
  }
  
  @RequestMapping("/out")
	public String out(SessionStatus sessionStatus) {
	  sessionStatus.setComplete(); 
	  return "redirect:/";
  }

  
}
