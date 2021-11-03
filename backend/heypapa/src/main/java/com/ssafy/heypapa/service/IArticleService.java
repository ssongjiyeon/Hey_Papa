package com.ssafy.heypapa.service;

import com.ssafy.heypapa.entity.Article;
import com.ssafy.heypapa.request.ArticleRequest;

public interface IArticleService {
	
	Article createArticle(ArticleRequest articleRequest);
	
//	List<ArticleResponse> getAllArticle(Pageable pageable);
	
	Article updateArticle(ArticleRequest articleRequest, Long id);
	
	void likeArticle(Boolean flag, Long id);

}
