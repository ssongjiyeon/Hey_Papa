package com.ssafy.heypapa.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ReviewRequest")
public class ReviewRequest {
	private String content;
}
