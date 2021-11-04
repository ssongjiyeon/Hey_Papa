package com.ssafy.heypapa.response;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ProfileResponse")
public class ProfileResponse {
	
	@ApiModelProperty(name = "nickname", example = "쑥쑥이")
	private String nickname;
	
	@ApiModelProperty(name = "img", example = "사진 주소")
	private String img;
	
	@ApiModelProperty(name = "week", example = "7")
	private int week;
	
	@ApiModelProperty(name = "d_day", example = "20211109")
	private Date d_day;
	
	@ApiModelProperty(name = "region", example = "봉명동")
	private String region;
}
