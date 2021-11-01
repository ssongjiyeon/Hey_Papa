package com.ssafy.heypapa.request;

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
}
