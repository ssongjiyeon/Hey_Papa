package com.ssafy.heypapa.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("MyQuizRequest")
public class MyQuizRequest {

	private Long user_id;
	private Long quiz_id;
	
}
