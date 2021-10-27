package com.ssafy.heypapa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import lombok.Getter;
import lombok.Setter;

/**
 * 게시판 댓글 모델 정의.
 */

@Entity
@Getter
@Setter
public class Review extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "article_id")
	private Article article; // 게시글 id
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user; // 유저 id
	
	@Column(nullable = false)
	private String content; // 내용
	
	private Date created_at; // 작성날짜
	
	@PrePersist
	public void createdAt() {
		this.created_at = new Date();
	}
	
}
