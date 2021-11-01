package com.ssafy.heypapa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ssafy.heypapa.service.UserService;

@SpringBootTest
public class PasswordEncoderTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	@DisplayName("패스워드 암호화 테스트")
	void passwordEncode() {
		// given
		String rawPassword = "1234";
		
		// when
		String encodePassword = passwordEncoder.encode(rawPassword);
		
		// then
		assertAll(
				() -> assertNotEquals(rawPassword, encodePassword),
				() -> assertTrue(passwordEncoder.matches(rawPassword, encodePassword))
		);
	}
	
}
