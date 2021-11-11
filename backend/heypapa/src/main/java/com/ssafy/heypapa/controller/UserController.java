package com.ssafy.heypapa.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.heypapa.auth.PapaUserDetails;
import com.ssafy.heypapa.entity.User;
import com.ssafy.heypapa.request.EmailRequest;
import com.ssafy.heypapa.request.RegistRequest;
import com.ssafy.heypapa.request.UserModifyRequest;
import com.ssafy.heypapa.request.UserRequest;
import com.ssafy.heypapa.response.MyArticleResponse;
import com.ssafy.heypapa.response.MyQuizResponse;
import com.ssafy.heypapa.response.ProfileResponse;
import com.ssafy.heypapa.response.UserResponse;
import com.ssafy.heypapa.service.IUserService;
import com.ssafy.heypapa.util.BaseResponseBody;
import com.ssafy.heypapa.util.CookieUtil;
import com.ssafy.heypapa.util.JwtTokenUtil;
import com.ssafy.heypapa.util.RedisUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
	final String FAIL_MESSAGE = "error";
	
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
	
	@GetMapping("/logout")
	@ApiOperation(value = "로그아웃")
	public ResponseEntity<BaseResponseBody> logout(HttpServletRequest req, HttpServletResponse res) {
		
		// refresh token 처리
		Cookie refreshToken = cookieUtil.getCookie(req, JwtTokenUtil.REFRESH_TOKEN_NAME);

		if(refreshToken != null) {
			String refreshJwt = refreshToken.getValue();
			
			if(refreshJwt != null) {
				redisUtil.deleteData(refreshJwt);
			}
		}
		
		Cookie accessToken = new Cookie(JwtTokenUtil.ACCESS_TOKEN_NAME, null);
		refreshToken = new Cookie(JwtTokenUtil.REFRESH_TOKEN_NAME, null);
		accessToken.setMaxAge(0);
		refreshToken.setMaxAge(0);
		res.addCookie(accessToken);
		res.addCookie(refreshToken);


		return ResponseEntity.status(200).body(new BaseResponseBody(200, SUCCESS_MESSAGE)); 
	}
	
	@PutMapping("/{user_id}")
	@ApiOperation(value = "회원 정보 수정")
	public ResponseEntity<BaseResponseBody> modify(@RequestBody UserModifyRequest req, 
			@PathVariable("user_id") long userId) {
		
		if(userService.putUser(userId, req)) {
			return ResponseEntity.status(200).body(new BaseResponseBody(200, SUCCESS_MESSAGE));
		}
		
		return new ResponseEntity<BaseResponseBody>(HttpStatus.BAD_REQUEST);
			
	}
	
	@GetMapping("/{user_id}")
	@ApiOperation(value = "회원 정보 조회")
	public ResponseEntity<ProfileResponse> getProfile(@PathVariable("user_id") long userId) {
		
		ProfileResponse res = userService.getProfile(userId);
		
		return ResponseEntity.status(200).body(res);
	}
	
	@GetMapping("/article/{user_id}")
	@ApiOperation(value = "회원 게시글 전체 조회")
	public ResponseEntity<List<MyArticleResponse>> getArticle(@PathVariable("user_id") long userId) {
		List<MyArticleResponse> res = userService.getArticle(userId);
		return ResponseEntity.status(200).body(res);
	}
	
	@GetMapping("/quiz/{user_id}")
	@ApiOperation(value = "회원 찜 퀴즈 전체 조회")
	public ResponseEntity<List<MyQuizResponse>> getQuiz(@PathVariable("user_id") long userId) {
		List<MyQuizResponse> res = userService.getQuiz(userId);
		return ResponseEntity.status(200).body(res);
	}
	
	@GetMapping("/like/{user_id}")
	@ApiOperation(value = "회원 좋아요한 글 전체 조회")
	public ResponseEntity<List<MyArticleResponse>> getLikeArticle(@PathVariable("user_id") long userId) {
		List<MyArticleResponse> res = userService.getLikeArticle(userId);
		return ResponseEntity.status(200).body(res);
	}
	
	@PutMapping("/profile/{user_id}")
	@ApiOperation(value = "회원 프로필 사진 수정")
	public ResponseEntity<BaseResponseBody> modifyImg(@PathVariable("user_id") long userId,
			@RequestPart(value = "user_thumbnail", required = true) MultipartFile userThumbnail) {
		if(userService.putUserImg(userId, userThumbnail)) {
			return ResponseEntity.status(200).body(new BaseResponseBody(200, SUCCESS_MESSAGE));
		}
		
		return new ResponseEntity<BaseResponseBody>(HttpStatus.BAD_REQUEST);
	}
}
