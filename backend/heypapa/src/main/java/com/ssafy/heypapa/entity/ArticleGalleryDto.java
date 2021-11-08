package com.ssafy.heypapa.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ArticleGalleryDto {

	private Long id;
	private String filePath;
	private String imgFullPath;
	private Article article;
	
	public ArticleGallery toEntity(){
        ArticleGallery build = ArticleGallery.builder()
                .id(id)
                .filePath(filePath)
                .article(article)
                .build();
        return build;
    }
	
	@Builder
    public ArticleGalleryDto(Long id, String filePath, String imgFullPath, Article article) {
        this.id = id;
        this.filePath = filePath;
        this.imgFullPath = imgFullPath;
        this.article = article;
    }

	public void setArticle(Long article_id) {
		// TODO Auto-generated method stub
		
	}
	
}
