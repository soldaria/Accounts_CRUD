package ru.sld.transactions.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ru.sld.transactions.model.Account;
import ru.sld.transactions.model.User;
import ru.sld.transactions.service.AccountService;
import ru.sld.transactions.service.UserService;

@Controller
@SessionAttributes("user")
public class AccountController {

	@Autowired
	private AccountService accService;		
	
	@RequestMapping(value = "/add-acc",method = RequestMethod.POST)	
	public String addAccount(@ModelAttribute("acc") Account acc,
							HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
	    acc.setOwner(user); 
		this.accService.addAccount(acc);	
		return "redirect:/home";		
	}
	
	@RequestMapping("delete_acc/{accId}")
	public String deleteAccount(	@PathVariable("accId") Integer accId,
									HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		Account acc = this.accService.getAccountById(accId);
		user.getAccounts().remove(acc);
		acc.setOwner(null);
		this.accService.removeAccount(accId);		
		return "redirect:/home";
	}
	
	@RequestMapping(value = "edit_acc/{id}")
	public String edit(	@PathVariable Integer id,
						Map<String, Object> model) {
		Account existingAccount = this.accService.getAccountById(id);
		Account acc = new Account();
		BeanUtils.copyProperties(existingAccount,acc);		
		model.put("acc",acc);
		return "edit_acc";
	}		

	@RequestMapping("edit_acc/update_acc")
	public String update(@ModelAttribute("acc") Account acc,
						HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		acc.setOwner(user);
		this.accService.updateAccount(acc);
		return "redirect:/home";
	}
	
	@RequestMapping("transaction_page")
	public String goToTransaction(	Map<String, Object> model,
									HttpServletRequest request) {	
		 User user = (User)request.getSession().getAttribute("user");  
		 model.put("accList", user.getAccounts());
		return "transaction_page";
	}
	
	@RequestMapping("transact")
	public String transact(	HttpServletRequest request) {	
		Account debitedAcc = this.accService.getAccountById(Integer.parseInt(request.getParameter("debitedAcc")));
		Account proceedsAcc = this.accService.getAccountByNum(request.getParameter("proceedsAcc"));
	    Integer amount = Integer.parseInt(request.getParameter("amount"));	    
	    this.accService.transaction(debitedAcc,proceedsAcc,amount);
		return "redirect:/home";
	}
	
}
