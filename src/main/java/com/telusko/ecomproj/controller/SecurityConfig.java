package com.telusko.ecomproj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		return http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
		    .oauth2Login(Customizer.withDefaults()).build();
	}
}
/*
	
	/*@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{	
		return http.csrf(Customizer -> Customizer.disable()) // to disable csrf
			   .authorizeHttpRequests(request -> request.anyRequest().authenticated())// authentication
			   .formLogin(Customizer.withDefaults())// web
			   .httpBasic(Customizer.withDefaults())//for postman
			   .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) ).build();//to generate new session everytime
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	     
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		//provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(userDetailsService); // create own UserDetailsService class implementing this default 
		return provider;
		
	}

*/

