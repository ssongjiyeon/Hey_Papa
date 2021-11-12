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
	
	@ApiModelProperty(name = "user_img", example = "유저이미지")
	private String user_img;
	
	@ApiModelProperty(name = "nickname", example = "닉네임")
	private String nickname;
	
	@ApiModelProperty(name = "like", example = "좋아요 여부")
	private boolean like;
	
	@ApiModelProperty(name = "해시태그")
	private String[] hashtag;
}
