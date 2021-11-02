package com.ssafy.heypapa.response;

import java.util.List;

import com.ssafy.heypapa.request.CommentRequest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CarecenterResponse")
public class CarecenterResponse {

	@ApiModelProperty(name = "산후조리원 번호", example = "Long")
	private Long id;
	@ApiModelProperty(name = "시도")
	private String sido; 
	@ApiModelProperty(name = "시군구")
	private String sigungu; 
	@ApiModelProperty(name = "이름")
	private String name; 
	@ApiModelProperty(name = "주소")
	private String address;
	@ApiModelProperty(name = "전화번호")
	private String phoneNumber; 
	@ApiModelProperty(name = "일반실")
	private String generalRoom; 
	@ApiModelProperty(name = "특실")
	private String specialRoom; 
	@ApiModelProperty(name = "경도")
	private Double longitude; 
	@ApiModelProperty(name = "위도")
	private Double latitude; 
	
}
