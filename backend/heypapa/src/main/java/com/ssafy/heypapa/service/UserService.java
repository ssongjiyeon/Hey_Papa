package com.ssafy.heypapa.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.heypapa.entity.User;
import com.ssafy.heypapa.repository.UserRepository;
import com.ssafy.heypapa.request.RegistRequest;
import com.ssafy.heypapa.request.UserModifyRequest;
import com.ssafy.heypapa.request.UserRequest;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	final String[] preNickname = new String[] 
			{"예쁜 ", "멋진 ", "우아한 ", "활발한 ", "고상한 ", "귀여운 ", "다정한 ", "대담한 ", "잘생긴 ", "따뜻한 ", "매력적인 ",
			  "명량한 ", "성실한 ", "신중한 ", "용감한 ", "수줍은 " };

	public User getUserByNickname(String username) {
		Optional<User> user = userRepository.findByNickname(username);
		if(user.isPresent()) {
			System.out.println("service " + user.get().getEmail());
		}
		return user.orElse(null);
	}

	@Override
	public User getUserByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
	
		return user.orElse(null);
	}

	@Override
	public User createUser(RegistRequest req) {
		User user = new User();
		user.setD_day(req.getDDay());
		user.setEmail(req.getEmail());
		user.setRegion(req.getRegion());
		user.setPassword(passwordEncoder.encode(req.getPassword()));
		user.setNickname(makeNickname(req.getNickname()));

		user.setWeek(req.getWeek());
		
		// 이미지 저장
		user.setImg("img");
		userRepository.save(user);
		
		return user;
	}

	@Override
	public boolean putUser(User user, UserModifyRequest req) {
		
		try {
			user.setD_day(req.getDDay());
			user.setRegion(req.getRegion());
			user.setPassword(passwordEncoder.encode(req.getPassword()));
			System.out.println(user.getNickname() + " " + req.getNickname());
			if(!user.getNickname().equals(req.getNickname())) {
				user.setNickname(makeNickname(req.getNickname()));
			}
			
			user.setWeek(req.getWeek());
			userRepository.save(user);
			
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public String makeNickname(String nickname) {
		// 닉네임 중복 처리
		
		String nowNickname = nickname;
		String newNickname = nickname;
		Optional<User> dUser = userRepository.findByNickname(nowNickname);
		Random rand = new Random();
		while(dUser.isPresent()) {
			newNickname = preNickname[rand.nextInt(16)] + nowNickname;
			dUser = userRepository.findByNickname(newNickname);
		}
		
		return newNickname;
	}

}
