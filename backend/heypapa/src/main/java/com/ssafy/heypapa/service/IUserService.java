package com.ssafy.heypapa.service;

import com.ssafy.heypapa.entity.User;
import com.ssafy.heypapa.request.UserRequest;

public interface IUserService {

	User getUserByNickname(String username);
	User login(UserRequest userRequest);
	
}
