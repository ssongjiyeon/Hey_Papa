package com.ssafy.heypapa.response;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ReviewResponse")
public class ReviewResponse {
	@ApiModelProperty(name = "리뷰id", example = "Long")
	private Long id;
	@ApiModelProperty(name = "유저id", example = "Long")
	private Long user_id;
	@ApiModelProperty(name = "유저닉네임")
	private String nickname;
	@ApiModelProperty(name = "유저프로필")
	private String user_img;
	@ApiModelProperty(name = "리뷰내용")
	private String content;
	@ApiModelProperty(name = "작성일자")
	private Date created_at;	
}
