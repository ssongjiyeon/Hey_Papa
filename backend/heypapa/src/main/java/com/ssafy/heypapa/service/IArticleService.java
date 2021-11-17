package com.ssafy.heypapa.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.heypapa.entity.Article;
import com.ssafy.heypapa.entity.Review;
import com.ssafy.heypapa.request.ArticleLikeRequest;
import com.ssafy.heypapa.request.ArticleRequest;
import com.ssafy.heypapa.request.ReviewRequest;
import com.ssafy.heypapa.response.ArticleResponse;
import com.ssafy.heypapa.response.ReviewResponse;

public interface IArticleService {
	
	Article createArticle(ArticleRequest articleRequest, MultipartFile articleThumbnail);
	
	List<ArticleResponse> getAllArticle(Pageable pageable, long userId);
	
	List<ArticleResponse> hashtagSearch(String hashtag);
	
	Article updateArticle(ArticleRequest articleRequest, Long id);
	
	void likeArticle(ArticleLikeRequest articleLikeRequest, Long id);
	
	void deleteArticle(Long id);
	
	Review createReview(ReviewRequest reviewRequest, Long id);
	
	Review updateReview(ReviewRequest reviewRequest, Long aId, Long rId);
	
	void deleteReview(Long aId, Long rId);
	
	List<ReviewResponse> getReview(Long id);

}
