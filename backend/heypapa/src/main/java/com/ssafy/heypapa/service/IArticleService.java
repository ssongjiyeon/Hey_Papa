package com.ssafy.heypapa.service;

import com.ssafy.heypapa.entity.Article;
import com.ssafy.heypapa.request.ArticleRequest;

public interface IArticleService {
	
	Article createArticle(ArticleRequest articlereauest);

}
