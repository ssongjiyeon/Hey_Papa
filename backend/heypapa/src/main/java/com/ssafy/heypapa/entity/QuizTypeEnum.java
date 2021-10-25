package com.ssafy.heypapa.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum QuizTypeEnum {

	아내, 아기, 음식, 사회;
	
	@JsonCreator
	public static QuizTypeEnum from(String s) {
		return QuizTypeEnum.valueOf(s);
	}
}
