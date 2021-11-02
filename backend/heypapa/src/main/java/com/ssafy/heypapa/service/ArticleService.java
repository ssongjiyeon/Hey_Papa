package com.ssafy.heypapa.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.heypapa.entity.Article;
import com.ssafy.heypapa.entity.ArticleHashtag;
import com.ssafy.heypapa.entity.Hashtag;
import com.ssafy.heypapa.repository.ArticleHashtagRepository;
import com.ssafy.heypapa.repository.ArticleRepository;
import com.ssafy.heypapa.repository.HashtagRepository;
import com.ssafy.heypapa.repository.UserRepository;
import com.ssafy.heypapa.request.ArticleRequest;

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
	
	@Override
	public Article createArticle(ArticleRequest articleRequest) {		
		Article article = new Article();
//		article.setUser(userRepository.findById(articleRequest.getUser_id()).get());
		article.setContent(articleRequest.getContent());
		article.setImg(articleRequest.getImg());
		
		// hashtag 처리
		for (String t : articleRequest.getHashtag()) {
			Optional<Hashtag> tag = hashtagRepository.findByName(t);
			
			if(tag.isPresent()) {
				// ArticleHashtag에 추가
				Hashtag hashtag = tag.get();
				ArticleHashtag articleHashtag = new ArticleHashtag();
				articleHashtag.setArticle(article);
				articleHashtag.setHashtag(hashtag);
				articleHashtagRepository.save(articleHashtag);	
				
			} else {
				Hashtag hashtag = new Hashtag();
				hashtag.setName(t);
				hashtagRepository.save(hashtag);
				ArticleHashtag articleHashtag = new ArticleHashtag();
				articleHashtag.setArticle(article);
				articleHashtag.setHashtag(hashtag);
				articleHashtagRepository.save(articleHashtag);	
			}	
		}
		return articleRepository.save(article);
	}
	
//	@Override
//	public List<ArticleRequest> getAllArticle(Pageable pageable) {
//		
//	}
	
	@Override
	public Article updateArticle(ArticleRequest articleRequest, Long id) {		
		Article article = articleRepository.findById(id).get();
//		article.setUser(userRepository.findById(articleRequest.getUser_id()).get());
		article.setContent(articleRequest.getContent());
		article.setImg(articleRequest.getImg());
		String[] htarr = articleRequest.getHashtag();
		List<String> list = new ArrayList<>(Arrays.asList(htarr));
		
		// ArticleHashtag 가져오기
		List<ArticleHashtag> articleHashtag = articleHashtagRepository.findByArticleId(id);
		
		// ArticleHashtag가 list에 있으면 hashtag를 list에서 제거
		// 없으면 articlehashtag에서 entity 제거
		for (ArticleHashtag ah : articleHashtag) {
			String h = ah.getHashtag().getName();
			if(list.contains(h)) {
				list.remove(h);
			} else {
				articleHashtagRepository.delete(ah);
			}
		}
		
		// 나머지 hashtag 처리
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
	
}
