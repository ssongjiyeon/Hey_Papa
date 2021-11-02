package com.ssafy.heypapa.response;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class ArticleResponse {
	
	@ApiModelProperty(name = "게시글id", example = "Long")
	private Long id;
	@ApiModelProperty(name = "유저id", example = "Long")
	private Long user_id;
	@ApiModelProperty(name = "내용")
	private String content;
	@ApiModelProperty(name = "이미지")
	private String img;
	@ApiModelProperty(name = "해시태그")
	private String[] hashtag;
//	@ApiModelProperty(name = "게시글 댓글 목록들")
//	private List<CommentRequest> comments;

}
