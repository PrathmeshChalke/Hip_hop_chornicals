package com.app.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.app.dto.UserRegistrationDto;
import com.app.model.User;

public interface UserServices extends UserDetailsService {

	User save(UserRegistrationDto registrationDto);
	
}
