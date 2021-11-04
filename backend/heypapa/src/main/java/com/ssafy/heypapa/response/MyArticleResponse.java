package com.ssafy.heypapa.response;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("MyArticleResponse")
public class MyArticleResponse {

	@ApiModelProperty(name = "id", example = "2")
	private Long id;
	
	@ApiModelProperty(name = "content", example = "내용")
	private String content;
	
	@ApiModelProperty(name = "like_cnt", example = "3")
	private int like_cnt;
	
	@ApiModelProperty(name = "comment_cnt", example = "2")
	private int comment_cnt;
	
	@ApiModelProperty(name = "created_at", example = "날짜")
	private Date created_at;
	
	@ApiModelProperty(name = "img", example = "이미지")
	private String img;
}
