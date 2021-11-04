package com.ssafy.heypapa.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ssafy.heypapa.entity.Article;
import com.ssafy.heypapa.entity.ArticleHashtag;
import com.ssafy.heypapa.entity.ArticleLike;
import com.ssafy.heypapa.entity.Hashtag;
import com.ssafy.heypapa.entity.Review;
import com.ssafy.heypapa.entity.User;
import com.ssafy.heypapa.repository.ArticleHashtagRepository;
import com.ssafy.heypapa.repository.ArticleLikeRepository;
import com.ssafy.heypapa.repository.ArticleRepository;
import com.ssafy.heypapa.repository.HashtagRepository;
import com.ssafy.heypapa.repository.ReviewRepository;
import com.ssafy.heypapa.repository.UserRepository;
import com.ssafy.heypapa.request.ArticleLikeRequest;
import com.ssafy.heypapa.request.ArticleRequest;
import com.ssafy.heypapa.request.ReviewRequest;
import com.ssafy.heypapa.response.ArticleResponse;
import com.ssafy.heypapa.response.ReviewResponse;

@Service("articleService")
public class ArticleService implements IArticleService {
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	HashtagRepository hashtagRepository;
	
	@Autowired
	ArticleHashtagRepository articleHashtagRepository;
	
	@Autowired
	ArticleLikeRepository articleLikeRepository;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Override
	public Article createArticle(ArticleRequest articleRequest) {		
		Article article = new Article();
		User user = userRepository.findById(articleRequest.getUser_id()).get();
		article.setUser(user);
		article.setContent(articleRequest.getContent());
		article.setImg(articleRequest.getImg());
		article.setCreated_at(new Date());
		article.setUpdated_at(new Date());
		
		// hashtag 처리
		// 요청에서 들어온 해시태그 스트링 배열을 순회하면서
		// 그 이름을 가진 해시태그 객체가 있는지 확인
		for (String t : articleRequest.getHashtag()) {
			Optional<Hashtag> tag = hashtagRepository.findByName(t);
			// 해시태그 내에 이미 존재한다면
			if(tag.isPresent()) {
				// ArticleHashtag에 게시글 객체와 해시태그 객체를 담아서 ArticleHashtag repo에 추가
				Hashtag hashtag = tag.get();
				ArticleHashtag articleHashtag = new ArticleHashtag();
				articleHashtag.setArticle(article);
				articleHashtag.setHashtag(hashtag);
				articleHashtagRepository.save(articleHashtag);	
			} 
			// 해시태그 내에 없다면
			else {
				// 새로운 객체로 해시태그 repo에 저장
				Hashtag hashtag = new Hashtag();
				hashtag.setName(t);
				hashtagRepository.save(hashtag);
				// ArticleHashtag에 게시글 객체와 해시태그 객체를 담아서 ArticleHashtag repo에 추가
				ArticleHashtag articleHashtag = new ArticleHashtag();
				articleHashtag.setArticle(article);
				articleHashtag.setHashtag(hashtag);
				articleHashtagRepository.save(articleHashtag);	
			}	
		}
		return articleRepository.save(article);
	}
	
	@Override
	public List<ArticleResponse> getAllArticle(Pageable pageable) {
		List<Article> list = articleRepository.findAll(pageable).getContent();
		List<ArticleResponse> copy = new ArrayList<>();
		ArticleResponse res;
		for(Article a : list) {
			res = new ArticleResponse();
			res.setId(a.getId());
			res.setContent(a.getContent());
			res.setImg(a.getImg());
			res.setCreated_at(a.getCreated_at());
			// 해시태그 처리
			List<String> hash = new ArrayList<>();
			List<ArticleHashtag> hastag = articleHashtagRepository.findByArticleId(a.getId());
			for(ArticleHashtag ah : hastag) {
				hash.add(ah.getHashtag().getName());
			}
			String[] str = new String[hash.size()];
			str = (String[]) hash.toArray(str);
			res.setHashtag(str);
			// like_cnt 처리
			List<ArticleLike> al = articleLikeRepository.findByArticleId(a.getId());
			res.setLike_cnt(al.size());
			// comment_cnt 처리
			List<Review> r = reviewRepository.findByArticleId(a.getId());
			res.setComment_cnt(r.size());
			//user 닉네임, 프로필이미지 처리
			res.setNickname(a.getUser().getNickname());
			res.setUser_img(a.getUser().getImg());
		}
		return copy;
	}
	
	@Override
	public Article updateArticle(ArticleRequest articleRequest, Long id) {		
		Article article = articleRepository.findById(id).get();
		article.setContent(articleRequest.getContent());
		article.setImg(articleRequest.getImg());
		article.setUpdated_at(new Date());
		// 수정 요청받은 해시태그 스트링 배열을 List로 변환
		// 이유는 아래에서 하나씩 삭제할건데 스트링 배열은 그게 안된데... List로 바꾸래..
		String[] htarr = articleRequest.getHashtag();
		List<String> list = new ArrayList<>(Arrays.asList(htarr));
		
		// 해당 게시글의 ArticleHashtag 가져오기
		List<ArticleHashtag> articleHashtag = articleHashtagRepository.findByArticleId(id);
		
		// ArticleHashtag가 list(요청받은 새로운 해시태그 리스트)에 있으면 hashtag를 list(요청받은 해시태그 리스트)에서 제거
		// => list(요청받은 새로운 해시태그 리스트)에 있는 애들은 따로 처리할게 없으니까 목록에서 지우고, 남은 애들은 새로 추가해주기
		// ArticleHashtag가 list(요청받은 새로운 해시태그 리스트)에 없으면 articlehashtag에서 entity 제거
		// => 요청받은 해시태그 리스트에 없으니까 지워주기
		for (ArticleHashtag ah : articleHashtag) {
			String h = ah.getHashtag().getName();
			if(list.contains(h)) {
				list.remove(h);
			} else {
				articleHashtagRepository.delete(ah);
			}
		}
		
		// 나머지 hashtag 처리
		// createAticle 해시태그 처리와 동일
		for (String t : list) {
			Optional<Hashtag> tag = hashtagRepository.findByName(t);
			if(tag.isPresent()) {
				// ArticleHashtag에 추가
				Hashtag hashtag = tag.get();
				ArticleHashtag newArticleHashtag = new ArticleHashtag();
				newArticleHashtag.setArticle(article);
				newArticleHashtag.setHashtag(hashtag);
				articleHashtagRepository.save(newArticleHashtag);	
			} else {
				Hashtag hashtag = new Hashtag();
				hashtag.setName(t);
				hashtagRepository.save(hashtag);
				ArticleHashtag newArticleHashtag = new ArticleHashtag();
				newArticleHashtag.setArticle(article);
				newArticleHashtag.setHashtag(hashtag);
				articleHashtagRepository.save(newArticleHashtag);	
			}	
		}
		return articleRepository.save(article);
	}
	
	@Override
	public void likeArticle(ArticleLikeRequest articleLikeRequest, Long id) {
		// (좋아요) like가 안되어 있으면 해당 게시글 객체와 요청보낸 유저 객체를 articleLike에 담아서 repo에 저장 
		if(articleLikeRequest.getCheck()==false) {
			Article article = articleRepository.findById(id).get();
			User user = userRepository.findById(articleLikeRequest.getUser_id()).get();
			ArticleLike like = new ArticleLike();
			like.setArticle(article);
			like.setUser(user);
			articleLikeRepository.save(like);
		} 
		// (좋아요 취소) like가 되어 있으면 게시글id와 유저id값으로 articlelike 객체를 찾고, repo에서 삭제 
		else {
			Long userId = articleLikeRequest.getUser_id();
			ArticleLike like = articleLikeRepository.findByArticleIdAndUserId(id, userId).get();
			articleLikeRepository.delete(like);
		}
	}
	
	@Override
	public void deleteArticle(Long id) {
		Article article = articleRepository.findById(id).get();
		articleRepository.delete(article);
	}
	
	@Override
	public Review createReview(ReviewRequest reviewRequest, Long id) {
		Review review = new Review();
		Article article = articleRepository.findById(id).get();
		Long userId = reviewRequest.getUser_id();
		User user = userRepository.findById(userId).get();
		review.setUser(user);
		review.setArticle(article);
		review.setContent(reviewRequest.getContent());
		review.setCreated_at(new Date());
		return reviewRepository.save(review);
	}
	
	@Override
	public List<ReviewResponse> getReview(Long id) {
		List<Review> list = reviewRepository.findByArticleId(id);
		List<ReviewResponse> copy = new ArrayList<>();
		ReviewResponse review;
		for(Review r : list) {
			User user = r.getUser();
			review = new ReviewResponse();
			review.setUser_id(user.getId());
			review.setUser_img(user.getImg());
			review.setNickname(user.getNickname());
			review.setContent(r.getContent());
			review.setCreated_at(r.getCreated_at());
		}
		return copy;
	}
}
