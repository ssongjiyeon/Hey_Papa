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
//	@ApiModelProperty(name = "유저 ID")
//	private Long user_id;
	@ApiModelProperty(name = "퀴즈 ID")
	private Long quiz_id;
	@ApiModelProperty(name = "퀴즈 정답 확인용")
//	private boolean quizcheck;
//	@ApiModelProperty(name = "퀴즈 좋아요")
//	private boolean quizlike;
//	@ApiModelProperty(name = "퀴즈 질문")
	private String question;
	@ApiModelProperty(name = "문제 타입")
	private String type;
	@ApiModelProperty(name = "문제 답번호")
	private int answer;
	@ApiModelProperty(name = "문제 답")
	private String answer2;
	@ApiModelProperty(name = "보기")
	private String candidate;
	@ApiModelProperty(name = "설명")
	private String description;
	@ApiModelProperty(name = "이미지")
	private String img;
	
}
