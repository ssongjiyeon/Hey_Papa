package com.ssafy.heypapa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.heypapa.entity.ArticleHashtag;

public interface ArticleHashtagRepository extends JpaRepository<ArticleHashtag, Long>{
	List<ArticleHashtag> findByArticleId(Long id);
	List<Long> findByArticle_id(Long article_id);
	Optional<ArticleHashtag> findByHashtagId(Long hashtag_id);
}
