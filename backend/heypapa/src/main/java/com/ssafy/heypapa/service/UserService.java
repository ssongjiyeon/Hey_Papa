package com.ssafy.heypapa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.heypapa.entity.User;
import com.ssafy.heypapa.repository.UserRepository;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;

	public User getUserByNickname(String username) {
		Optional<User> user = userRepository.findByNickname(username);
		
		if(user.isPresent()) {
			return user.get();
		}
		
		return null;
	}

}
