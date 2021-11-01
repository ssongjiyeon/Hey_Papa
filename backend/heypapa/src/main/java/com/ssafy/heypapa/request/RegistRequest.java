package com.ssafy.heypapa.request;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("RegistRequest")
public class RegistRequest {
	
	@ApiModelProperty(name = "email", example = "ssafy@ssafy.com")
	private String email;
	
	@ApiModelProperty(name = "password", example = "1234")
	private String password;
	
	@ApiModelProperty(name = "nickname", example = "쑥쑥이")
	private String nickname;
	
	@ApiModelProperty(name = "dDay", example = "2021119")
	private Date dDay;
}
