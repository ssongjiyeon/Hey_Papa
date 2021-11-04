package com.ssafy.heypapa.service;

import com.ssafy.heypapa.entity.User;
import com.ssafy.heypapa.request.RegistRequest;
import com.ssafy.heypapa.request.UserModifyRequest;
import com.ssafy.heypapa.request.UserRequest;
import com.ssafy.heypapa.response.ProfileResponse;

public interface IUserService {

	User getUserByNickname(String username);
	User getUserByEmail(String email);
	User createUser(RegistRequest req);
	boolean putUser(long userId, UserModifyRequest req);
	ProfileResponse getProfile(long userId);
	
}
