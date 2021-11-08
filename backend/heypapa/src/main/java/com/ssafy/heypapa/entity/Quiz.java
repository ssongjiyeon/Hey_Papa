package com.ssafy.heypapa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 퀴즈 모델 정의.
 */

@Entity
@Getter
@Setter
public class Quiz extends BaseEntity {

	@Column(nullable = false)
	private String question; // 질문
	
	@Column(nullable = false)
	private int answer; // 답-int
	
	@Column(nullable = false)
	private String answer2; // 답-string
	
	@Column(nullable = false)
//	private Enum<QuizTypeEnum> type; 
	private String type; // 문제 타입
	
	@Column(nullable = false)
	private String candidate; // 보기
	
	@Column(columnDefinition = "LONGTEXT")
	private String description; // 설명
	
//	@Column(columnDefinition = "TEXT")
//	private String img; // 문제 설명 이미지
	
}
