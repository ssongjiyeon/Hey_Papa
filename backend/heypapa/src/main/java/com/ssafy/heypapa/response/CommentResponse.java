package com.ssafy.heypapa.response;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CommentResponse")
public class CommentResponse {

	private Long id;
	private Long user_id;
	private String nickname;
	private String user_img;
	private String content;
	private Date created_at;
	private String calculateTime;
	
}
