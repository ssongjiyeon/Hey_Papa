package com.ssafy.heypapa.response;

import java.util.List;

import com.ssafy.heypapa.request.CommentRequest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("QuizResponse")
public class QuizResponse {

	@ApiModelProperty(name = "퀴즈 번호", example = "Long")
	private Long id;
	@ApiModelProperty(name = "질문")
	private String question;
	@ApiModelProperty(name = "답번호")
	private int answer;
	@ApiModelProperty(name = "답")
	private String answer2;
	@ApiModelProperty(name = "퀴즈 종류")
	private String type;
	@ApiModelProperty(name = "보기")
	private String candidate;
	@ApiModelProperty(name = "설명")
	private String description;
	@ApiModelProperty(name = "이미지")
	private String img;
	@ApiModelProperty(name = "찜")
	private boolean like;
	
	@ApiModelProperty(name = "퀴즈 댓글 목록들")
	private List<CommentResponse> comments;
	
}
