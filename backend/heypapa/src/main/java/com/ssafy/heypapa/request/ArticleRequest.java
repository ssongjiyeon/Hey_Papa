package com.ssafy.heypapa.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ArticleRequest")
public class ArticleRequest {
	@ApiModelProperty(name = "user_id")
	private Long user_id;
	@ApiModelProperty(name = "content")
	private String content;
	@ApiModelProperty(name = "hashtag")
	private String[] hashtag;
}
