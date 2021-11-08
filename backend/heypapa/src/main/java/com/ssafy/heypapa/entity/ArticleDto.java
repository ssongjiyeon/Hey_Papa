package com.ssafy.heypapa.entity;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ArticleDto {

	private Long id;
	private User user;
	private String content;
	private Date created_at;
	private Date updated_at;
	private String img;
	private String imgFullPath;
	
	public Article toEntity() {
		Article build = new Article();
		return build;
	}
	
	@Builder
	public ArticleDto(Long id, User user, String content, Date created_at, Date updated_at, String img, String imgFullPath) {
		this.id = id;
		this.user = user;
		this.content = content;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.img = img;
		this.imgFullPath = imgFullPath;
	}
	
}
