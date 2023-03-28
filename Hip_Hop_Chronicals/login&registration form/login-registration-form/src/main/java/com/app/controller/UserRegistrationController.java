package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.dto.UserRegistrationDto;
import com.app.services.UserServices;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	private UserServices userServices;

	public UserRegistrationController(UserServices userServices) {
		super();
		this.userServices = userServices;
	}
	
	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}
		
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
		
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("User")UserRegistrationDto registrationDto) {
		userServices.save(registrationDto);
		return "redirect:/registration?success";
	}
}
