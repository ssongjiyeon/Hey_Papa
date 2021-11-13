package com.ssafy.heypapa.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;
import lombok.Setter;

/**
 * 나의 퀴즈 모델 정의.
 */

@Entity
@Getter
@Setter
public class MyQuiz extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quiz_id")
	private Quiz quiz; // 퀴즈 id
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user; // 유저 id
	
//	@ColumnDefault("0")
//	private boolean quizcheck; // 퀴즈 확인용
//	
//	@ColumnDefault("0")
//	private boolean quizlike; // 퀴즈 좋아요
	
}
