package com.ssafy.heypapa.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

/**
 * 모델 간 공통 사항 정의.
 */

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
	// 공통 속성 id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false,updatable = false)
	private Long id;
}
