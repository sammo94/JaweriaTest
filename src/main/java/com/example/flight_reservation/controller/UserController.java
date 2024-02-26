package com.example.flight_reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.flight_reservation.entities.User;
import com.example.flight_reservation.entities.Verify;
import com.example.flight_reservation.repositories.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepo;

	@RequestMapping("/showReg")
	public String showReg() {
		return "login/userRegistration";
	}
	
	@RequestMapping("/showLogin")
	public String showLogin() {
		return "login/login";
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user,@ModelAttribute("verify") Verify verify, ModelMap modelMap) {
		if(verify.getPassword().equals(verify.getConfirmPassword())) {
		userRepo.save(user);
		return "login/login";
		}else {
			modelMap.addAttribute("msg", "Entered Passwords are Mismatching");
			return "login/userRegistration";
		}
	}
	
	@RequestMapping(value = "userLogin", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email,@RequestParam("password") String password, ModelMap modelMap) {
		User user = userRepo.findByEmail(email);
		try {
		if(user.getPassword().equals(password)) {
			return "findFlight";
		}else{
		 modelMap.addAttribute("msg", "Invalid username / password. Please try again!!");
		 return "login/login";
		}
		}catch(Exception e) {
			modelMap.addAttribute("msg", "Invalid username / password. Please try again!!");
			return "login/login";
		}
		
	}
}