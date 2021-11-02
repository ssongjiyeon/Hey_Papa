package com.ssafy.heypapa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 정보 모델 정의.
 */

@Entity
@Getter
@Setter
public class Info extends BaseEntity {

	@Column(nullable = false)
	private String month; // 월
	@Column(columnDefinition = "LONGTEXT")
	private String baby; // 아기정보
	@Column(columnDefinition = "LONGTEXT")
	private String mom; // 엄마정보
	private String baby_img; // 아기이미지
	private String mom_img; // 엄마이미지
	
}
