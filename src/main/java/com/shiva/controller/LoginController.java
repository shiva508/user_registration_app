package com.shiva.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.shiva.formmodel.RegistrationForm;
import com.shiva.formmodel.RoleForm;
import com.shiva.service.RegistrationService;

@Controller
public class LoginController {
	@Autowired
	private RegistrationService registrationService;

	@GetMapping(value = "/")
	public String welcomePage(Model model) {
		RegistrationForm registration = new RegistrationForm();
		model.addAttribute("registration", registration);
		return "welcome";
	}

	@GetMapping("/users")
	public String usersList(Model model) {
		model.addAttribute("users",registrationService.usersList());
		return "usersList";
	}
	@GetMapping("/user/{userid}")
	public String getUser(@PathVariable("userid")Integer userid,Model model) {
		model.addAttribute("registration",registrationService.getUserByUserId(userid));
		return "updateuser";
	}
	@GetMapping("/deleteuser/{userid}")
	public String deleteUser(@PathVariable("userid")Integer userid,Model model) {
		registrationService.deleteUser(userid);
		return "redirect:/users";	
	}
	@GetMapping("/viewuser/{userid}")
	public String viewUser(@PathVariable("userid")Integer userid,Model model) {
		model.addAttribute("user",registrationService.getUserByUserId(userid));
		return "viewUser";	
	}
	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute("registration") RegistrationForm registration, Model model ) {
		registrationService.updateUser(registration);
		return "redirect:/users";
	}
	@PostMapping("/formregistration")
	public String registration(@Valid @ModelAttribute("registration") RegistrationForm registration, Model model,
			BindingResult result) {
		String view = "";
		model.addAttribute("registration", registration);
		Long userid=registrationService.isUserExist(registration.getEmail());
		if(userid>0) {
			view="UserExist";
		}else {
			registration.setRoles(Arrays.asList(new RoleForm("ROLE_USER"),new RoleForm("ROLE_ADMIN")));
			registrationService.saveUser(registration);
			if (result.hasErrors()) {
				view = "welcome";
			} else {
				view = "redirect:/users";
			}	
		}
		
		return view;
	}

	@GetMapping("/customlogin")
	public String loginpage() {
		return "login";
	}

	@GetMapping("/myform")
	public String myform() {
		return "MyForm";
	}

	@GetMapping("/fromexp")
	public String fromexp(HttpServletRequest request, Model model, @RequestParam("myname") String myName) {
		String name1 = request.getParameter("myname");
		model.addAttribute("name1", name1);
		model.addAttribute("myName", myName);
		return "ParamExe";
	}
}
