package com.ssafy.heypapa.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("userResponse")
public class UserResponse {
	
	@ApiModelProperty(name = "id", example = "1")
	private Long id;
	
	@ApiModelProperty(name = "nickname", example = "쑥쑥이")
	private String nickname;
	
	@ApiModelProperty(name = "img", example = "사진 주소")
	private String img;
	
	@ApiModelProperty(name = "week", example = "7")
	private int week;
	
	@ApiModelProperty(name = "token", example = "토큰 정보")
	private TokenInfo token;
}