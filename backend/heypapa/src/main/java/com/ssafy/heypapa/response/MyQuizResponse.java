package com.ssafy.heypapa.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("MyQuizResponse")
public class MyQuizResponse {

	@ApiModelProperty(name = "나의 퀴즈 ID", example = "Long")
	private Long id;
	@ApiModelProperty(name = "유저 ID")
	private Long user_id;
	@ApiModelProperty(name = "나의 퀴즈 ID")
	private Long myquiz_id;
	@ApiModelProperty(name = "퀴즈 정답 확인용")
	private boolean quizcheck;
	@ApiModelProperty(name = "퀴즈 좋아요")
	private boolean quizlike;
	
}
