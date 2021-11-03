package com.ssafy.heypapa.request;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserModifyRequest")
public class UserModifyRequest {
	
	@ApiModelProperty(name = "password", example = "1234")
	private String password;
	
	@ApiModelProperty(name = "nickname", example = "쑥쑥이")
	private String nickname;
	
	@ApiModelProperty(name = "dDay", example = "2021119")
	private Date dDay;
	
	@ApiModelProperty(name = "region", example = "봉명동")
	private String region;
	
	@ApiModelProperty(name = "week", example = "7")
	private int week;
}
