package com.ssafy.heypapa;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.google.gson.Gson;
import com.ssafy.heypapa.controller.UserController;
import com.ssafy.heypapa.request.UserRequest;
import com.ssafy.heypapa.service.UserService;

@SpringBootTest(classes = UserController.class)
public class UserControllerTest {
	
	private MockMvc mvc;
	
	@MockBean
	UserService userService;
	
	@Autowired
	private WebApplicationContext ctx;
	
	@BeforeAll
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.ctx).addFilter(new CharacterEncodingFilter("UTF-8", true)).build();
	}
	
	@Test
	@DisplayName("로그인 테스트")
	void loginTest() throws Exception {
		UserRequest userRequest = new UserRequest();
		userRequest.setEmail("ssafy@ssafy.com");
		userRequest.setPassword("1234");
		Gson gson = new Gson();
		String content = gson.toJson(userRequest);
		
		mvc.perform(post("/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(content))
				.andDo(print())
				.andExpect(status().isOk());
		
				
	}
}
