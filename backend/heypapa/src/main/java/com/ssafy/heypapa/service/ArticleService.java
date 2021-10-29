package com.ssafy.heypapa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.heypapa.entity.Article;
import com.ssafy.heypapa.repository.ArticleRepository;
import com.ssafy.heypapa.repository.UserRepository;
import com.ssafy.heypapa.request.ArticleRequest;

@Service("articleService")
public class ArticleService implements IArticleService {
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Article createArticle(ArticleRequest articleRequest) {
		Article article = new Article();
//		article.setUser(userRepository.findById(articleRequest.getUser_id()).get());
		article.setContent(articleRequest.getContent());
		article.setImg(articleRequest.getImg());
		article.setCreated_at(articleRequest.getCreated_at());
		article.setUpdated_at(articleRequest.getCreated_at());
		return articleRepository.save(article);
	}
}
