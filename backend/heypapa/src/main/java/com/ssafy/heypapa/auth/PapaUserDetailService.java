package com.ssafy.heypapa.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ssafy.heypapa.entity.User;
import com.ssafy.heypapa.service.IUserService;

@Component
public class PapaUserDetailService {//implements UserDetailsService {
//
//	@Autowired
//	IUserService userService;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userService.getUserByNickname(username);
//		
//		if(user != null) {
//			PapaUserDetails userDetails = new PapaUserDetails(user);
//			return userDetails;
//		}
//		
//		return null;
//	}

}
