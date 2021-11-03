package com.ssafy.heypapa.response;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ArticleResponse")
public class ArticleResponse {
	@ApiModelProperty(name = "게시글id", example = "Long")
	private Long id;
	@ApiModelProperty(name = "유저닉네임")
	private String nickname;
	@ApiModelProperty(name = "유저프로필")
	private String user_img;
	@ApiModelProperty(name = "내용")
	private String content;
	@ApiModelProperty(name = "이미지")
	private String img;
	@ApiModelProperty(name = "작성일자")
	private Date created_at;
	@ApiModelProperty(name = "해시태그")
	private String[] hashtag;
	@ApiModelProperty(name = "좋아요 수")
	private int like_cnt;
	@ApiModelProperty(name = "댓글 수")
	private int comment_cnt;
}
