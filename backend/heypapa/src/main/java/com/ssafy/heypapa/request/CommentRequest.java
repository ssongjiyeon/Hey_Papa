package com.ssafy.heypapa.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CommentRequest")
public class CommentRequest {

	private String nickname; // 유저 닉네임
	private Long id;
	private String content;
	
}
