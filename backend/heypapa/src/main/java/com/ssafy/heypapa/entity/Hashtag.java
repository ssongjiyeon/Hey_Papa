package com.ssafy.heypapa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 해시태그 모델 정의.
 */

@Entity
@Getter
@Setter
public class Hashtag extends BaseEntity {

	@Column(nullable = false)
	private String name; // 해시태그 이름
	
}
