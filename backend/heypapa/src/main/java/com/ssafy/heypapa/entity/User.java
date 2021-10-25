package com.ssafy.heypapa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 유저 모델 정의.
 */

@Entity
@Getter
@Setter
public class User extends BaseEntity {

	@Column(nullable = false)
	private String email; // 이메일
	
	@Column(nullable = false)
	private String password; // 비밀번호
	
	@Column(nullable = false)
	private String nickname; // 닉네임
	
	private int week; // 주차
	private Date d_day; // 출산예정일
	private String img; // 프로필 사진
	
}
