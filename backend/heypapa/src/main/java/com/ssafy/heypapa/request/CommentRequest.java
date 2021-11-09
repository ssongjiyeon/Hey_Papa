package com.ssafy.heypapa.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CommentRequest")
public class CommentRequest {

//	private String nickname; // 유저 닉네임
	@ApiModelProperty(name = "유저id")
	private Long user_id;
	private String content;
//	private String calculateTime;
	
}
