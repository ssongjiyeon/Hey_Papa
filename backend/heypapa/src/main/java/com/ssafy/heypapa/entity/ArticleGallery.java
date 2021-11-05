package com.ssafy.heypapa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * aws + s3 연동 게시글 파일 업로드 모델 정의.
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
//@Setter
@Entity
public class ArticleGallery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "TEXT")
    private String filePath;
	
	@ManyToOne
	@JoinColumn(name = "article_id")
    private Article article; // 게시글 번호
	
	@Builder
    public ArticleGallery(Long id, String filePath, Article article) {
        this.id = id;
        this.filePath = filePath;
        this.article = article;
    }
	
}
