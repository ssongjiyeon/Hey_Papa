package com.ssafy.heypapa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 게시판 모델 정의.
 */

@Entity
@Getter
@Setter
public class Article extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user; // 유저 id
	
	@Column(nullable = false)
	private String content; // 내용
	
	private Date created_at; // 작성날짜
	private Date updated_at; // 수정날짜
	private String calculateTime; // 계산한 시간
	private String img; // 사진
	
//	@Column(name = "like_count")
//	private Integer like; // 좋아요 개수 셀때 사용
	
	@PrePersist
	public void createdAt() {
		this.created_at = new Date();
		this.updated_at = new Date();
//		this.like = 0;
	}
	
}
