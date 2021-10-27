package com.ssafy.heypapa.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

/**
 * 게시글 좋아요 모델 정의.
 */

@Entity
@Getter
@Setter
public class Like extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user; // 유저 id
	
	@ManyToOne
	@JoinColumn(name = "article_id")
	private Article article; // 게시글 id
	
}
