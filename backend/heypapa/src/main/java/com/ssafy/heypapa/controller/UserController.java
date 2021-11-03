package com.ssafy.heypapa.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.heypapa.auth.PapaUserDetails;
import com.ssafy.heypapa.entity.User;
import com.ssafy.heypapa.request.EmailRequest;
import com.ssafy.heypapa.request.RegistRequest;
import com.ssafy.heypapa.request.UserModifyRequest;
import com.ssafy.heypapa.request.UserRequest;
import com.ssafy.heypapa.response.UserResponse;
import com.ssafy.heypapa.service.IUserService;
import com.ssafy.heypapa.util.BaseResponseBody;
import com.ssafy.heypapa.util.CookieUtil;
import com.ssafy.heypapa.util.JwtTokenUtil;
import com.ssafy.heypapa.util.RedisUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

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
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	final String SUCCESS_MESSAGE = "success";
	
	@PostMapping("/login")
	@ApiOperation(value = "로그인", notes = "로그인 성공 시 회원 정보 반환")
	public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest,
				HttpServletRequest req, HttpServletResponse res) {
			
		final User user = userService.getUserByEmail(userRequest.getEmail());
		
		if(!passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
			return new ResponseEntity<UserResponse>(HttpStatus.BAD_REQUEST);
		}
		
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
		
	}
	
	@PostMapping("/regist")
	@ApiOperation(value = "회원가입", notes = "회원가입을 한다.")
	public ResponseEntity<BaseResponseBody> regist(@RequestBody RegistRequest req) {
		
		User user = userService.createUser(req);
		
		if(user == null) {
			return new ResponseEntity<BaseResponseBody>(HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.status(200).body(new BaseResponseBody(200, SUCCESS_MESSAGE));
	}
	
	@PostMapping("/email")
	@ApiOperation(value = "이메일 중복 확인")
	public ResponseEntity<BaseResponseBody> emailVerify(@RequestBody EmailRequest email) {
		User user = userService.getUserByEmail(email.getEmail());
		if(user == null) {
			return ResponseEntity.status(200).body(new BaseResponseBody(200, SUCCESS_MESSAGE));
		}
		
		return new ResponseEntity<BaseResponseBody>(HttpStatus.BAD_REQUEST);
	}
	
//	@GetMapping("/logout")
//	@ApiOperation(value = "로그아웃")
//	public ResponseEntity<BaseResponseBody> logout() {
//		redisUtil.deleteData();
//	}
	
//	@PutMapping("/")
//	@ApiOperation(value = "회원 정보 수정")
//	public ResponseEntity<BaseResponseBody> modify(@RequestBody UserModifyRequest req, 
//			@ApiIgnore Authentication authentication) {
//		
//		try {
//			PapaUserDetails userDetails = (PapaUserDetails) authentication.getDetails();
//			User user = userService.getUserByNickname(userDetails.getUsername());
//			System.out.println(user.getEmail());
//			if(userService.putUser(user, req)) {
//				return ResponseEntity.status(200).body(new BaseResponseBody(200, SUCCESS_MESSAGE));
//			}
//			
//			return new ResponseEntity<BaseResponseBody>(HttpStatus.BAD_REQUEST);
//		} catch(Exception e) {
//			return new ResponseEntity<BaseResponseBody>(HttpStatus.BAD_REQUEST);
//		}	
//	}
}
