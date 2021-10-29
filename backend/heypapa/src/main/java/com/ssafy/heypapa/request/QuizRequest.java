package com.ssafy.heypapa.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("QuizRequest")
public class QuizRequest {

	@ApiModelProperty(name = "퀴즈 번호", example = "Long")
	private Long id;
	@ApiModelProperty(name = "질문")
	private String question;
	@ApiModelProperty(name = "답")
	private int answer;
	@ApiModelProperty(name = "퀴즈 종류")
	private String type;
	@ApiModelProperty(name = "보기")
	private String candidate;
	@ApiModelProperty(name = "설명")
	private String description;
	@ApiModelProperty(name = "이미지")
	private String img;
	
}
