package com.ssafy.heypapa.service;

import com.ssafy.heypapa.entity.User;

public interface IUserService {

	User getUserByNickname(String username);
	
}
