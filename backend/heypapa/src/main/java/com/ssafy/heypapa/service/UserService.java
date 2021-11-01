package com.ssafy.heypapa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.heypapa.entity.User;
import com.ssafy.heypapa.repository.UserRepository;
import com.ssafy.heypapa.request.UserRequest;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public User getUserByNickname(String username) {
		Optional<User> user = userRepository.findByNickname(username);
		
		if(user.isPresent()) {
			return user.get();
		}
		
		return null;
	}

	@Override
	public User login(UserRequest userRequest) {
		System.out.println(passwordEncoder.encode(userRequest.getPassword()));
		
		Optional<User> user = userRepository.findByEmail(userRequest.getEmail());
		
		if(user.isPresent() && passwordEncoder.matches(userRequest.getPassword(), user.get().getPassword())) {
			return user.get();
		}
		
		return null;
	}

}
