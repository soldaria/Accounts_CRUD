package ru.sld.transactions.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import ru.sld.transactions.model.Account;
import ru.sld.transactions.model.User;
import ru.sld.transactions.service.UserService;

@Controller
@SessionAttributes("user")
public class UserController {

	@Autowired
	private UserService userService;		
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(	@Valid @ModelAttribute("user") User user, 
							BindingResult bindingResult) {
		if (!bindingResult.hasErrors())
			this.userService.addUser(user);	
		else
			System.out.println("Error");
		return "login";
	}
	
	@RequestMapping(value = "edit_user/{id}")
	public String editUser(	@PathVariable Integer id,
						Map<String, Object> model) {
		User existingUser = this.userService.getUserById(id);
		User user = new User();
		BeanUtils.copyProperties(existingUser,user);		
		model.put("u",user);
		return "edit_user";
	}	
	
	
	@RequestMapping("edit_user/update_user")	
	public String updateUser(@ModelAttribute("u") User u) {				
		userService.updateUser(u);       
		return "redirect:/home";
	}

	@RequestMapping("/delete/{userId}")
	public String deleteUser(@PathVariable("userId") Integer userId) {
		this.userService.removeUser(userId);
		return "redirect:/home";
	}
	
	@RequestMapping("/show_accs/{userId}")
	public String showUserAccs(	@PathVariable("userId") Integer userId,
								Map<String, Object> model) {
		User user = userService.getUserById(userId);		
		model.put("accList", user.getAccounts()); 
		return "user_accs";
	}
}
