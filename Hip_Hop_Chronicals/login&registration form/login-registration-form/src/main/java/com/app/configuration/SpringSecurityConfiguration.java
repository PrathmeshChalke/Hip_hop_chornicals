package com.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.app.services.UserServices;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {
	
	@Autowired
	private UserServices userService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
		
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(authenticationProvider());
	}
	
	 @Bean
	 public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests().requestMatchers(
	            		"/registration**",
	            		"/js/**",
	            		"/css/**",
	            		"/img/**").permitAll()
	            .anyRequest().authenticated()
	            .and()
	            .formLogin()
	            .loginPage("/login")
	            .permitAll()
	            .and()
	            .logout()
	            .invalidateHttpSession(true)
	            .clearAuthentication(true)
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            .logoutSuccessUrl("/login?logout")
	            .permitAll();
	        
	 		return http.build();
	            
	    }
	 
	 	

}
