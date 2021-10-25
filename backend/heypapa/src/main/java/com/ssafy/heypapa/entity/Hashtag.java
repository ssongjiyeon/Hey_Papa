package com.ssafy.heypapa.entity;

import javax.persistence.Column;

public class Hashtag extends BaseEntity {

	@Column(nullable = false)
	private String content; // 해시태그 이름
	
}
