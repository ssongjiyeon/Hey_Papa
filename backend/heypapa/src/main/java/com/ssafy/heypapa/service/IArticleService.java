package com.ssafy.heypapa.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ssafy.heypapa.entity.Article;
import com.ssafy.heypapa.entity.Review;
import com.ssafy.heypapa.request.ArticleRequest;
import com.ssafy.heypapa.request.ReviewRequest;
import com.ssafy.heypapa.response.ArticleResponse;
import com.ssafy.heypapa.response.ReviewResponse;

public interface IArticleService {
	
	Article createArticle(ArticleRequest articleRequest);
	
	List<ArticleResponse> getAllArticle(Pageable pageable);
	
	Article updateArticle(ArticleRequest articleRequest, Long id);
	
	void likeArticle(Boolean flag, Long id);
	
	void deleteArticle(Long id);
	
	Review createReview(ReviewRequest reviewRequest, Long id);
	
	List<ReviewResponse> getReview(Long id);

}
