package com.ssafy.heypapa.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.heypapa.entity.User;
import com.ssafy.heypapa.request.UserRequest;
import com.ssafy.heypapa.response.UserResponse;
import com.ssafy.heypapa.service.IUserService;
import com.ssafy.heypapa.util.BaseResponseBody;
import com.ssafy.heypapa.util.CookieUtil;
import com.ssafy.heypapa.util.JwtTokenUtil;
import com.ssafy.heypapa.util.RedisUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "유저 api", tags = { "User" })
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private JwtTokenUtil jwtUtil;
	
	@Autowired
	private CookieUtil cookieUtil;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@PostMapping("/login")
	@ApiOperation(value = "로그인", notes = "로그인 성공 시 회원 정보 반환")
	public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest,
				HttpServletRequest req, HttpServletResponse res) {
		
		try {
			System.out.println(userRequest.getEmail() + " " + userRequest.getPassword());
			final User user = userService.login(userRequest);
			final String token = jwtUtil.generateToken(user);
			final String rToken = jwtUtil.generateRefreshToken(user);
			
			Cookie accessToken = cookieUtil.createCookie(JwtTokenUtil.ACCESS_TOKEN_NAME, token);
			Cookie refreshToken = cookieUtil.createCookie(JwtTokenUtil.REFRESH_TOKEN_NAME, rToken);
			
			redisUtil.setDataExpire(rToken, user.getNickname(), JwtTokenUtil.REFRESH_TOKEN_VALIDATION_SECOND);
			res.addCookie(accessToken);
			res.addCookie(refreshToken);
			
			UserResponse userResponse = new UserResponse();
			userResponse.setImg(user.getImg());
			userResponse.setNickname(user.getNickname());
			userResponse.setWeek(user.getWeek());
			userResponse.setId(user.getId());
		
			return ResponseEntity.status(200).body(userResponse);
			
		} catch(Exception e) {
			return new ResponseEntity<UserResponse>(HttpStatus.BAD_REQUEST);
		}
	}
}
