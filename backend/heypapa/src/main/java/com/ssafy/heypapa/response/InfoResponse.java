package com.ssafy.heypapa.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("InfoResponse")
public class InfoResponse {

	@ApiModelProperty(name = "정보 번호", example = "Long")
	private Long id;
	@ApiModelProperty(name = "아기 정보")
	private String baby; 
	@ApiModelProperty(name = "엄마 정보")
	private String mom; 
	@ApiModelProperty(name = "아기 사진")
	private String baby_img; 
	@ApiModelProperty(name = "엄마 사진")
	private String mom_img;
	
}
