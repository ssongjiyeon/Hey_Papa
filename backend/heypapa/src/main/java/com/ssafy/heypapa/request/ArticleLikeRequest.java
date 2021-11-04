package com.ssafy.heypapa.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ArticleLikeRequest")
public class ArticleLikeRequest {
	@ApiModelProperty(name = "유저id")
	private Long user_id;
	@ApiModelProperty(name = "좋아요 여부")
	private Boolean check;
}
