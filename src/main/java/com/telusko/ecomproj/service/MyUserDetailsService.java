package com.telusko.ecomproj.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


/*
@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	public UserRepo rep;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		Users user = rep.findByusername(username);
		
		if(user==null)
		{
			System.out.println("Username not found");
			throw new UsernameNotFoundException("Username not found");
		}
		else
			return new UserPrincipal(user);
	}


}
*/