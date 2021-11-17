package com.ssafy.heypapa.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserRequest")
public class UserRequest {
	
	@ApiModelProperty(name = "email", example = "ssafy@ssafy.com")
	private String email;
	
	@ApiModelProperty(name = "password", example = "1234")
	private String password;
	
	public UsernamePasswordAuthenticationToken toAuthentication() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}
}
