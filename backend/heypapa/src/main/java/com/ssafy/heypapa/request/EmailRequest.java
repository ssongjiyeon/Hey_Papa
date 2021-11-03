package com.ssafy.heypapa.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("EmailRequest")
public class EmailRequest {

	@ApiModelProperty(name = "email", example = "ssafy@ssafy.com")
	private String email;
}
