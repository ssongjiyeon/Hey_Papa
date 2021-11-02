package com.ssafy.heypapa.request;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ArticleRequest")
public class ArticleRequest {
	
	@ApiModelProperty(name = "유저id")
	private Long user_id;
	@ApiModelProperty(name = "게시글 내용")
	private String content;
	@ApiModelProperty(name = "게시글 사진")
	private String img;
	@ApiModelProperty(name = "해시 태그")
	private String[] hashtag;
}
