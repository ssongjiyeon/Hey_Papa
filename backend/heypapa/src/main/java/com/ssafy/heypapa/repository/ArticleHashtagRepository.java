package com.ssafy.heypapa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.heypapa.entity.ArticleHashtag;

public interface ArticleHashtagRepository extends JpaRepository<ArticleHashtag, Long>{
	List<ArticleHashtag> findByArticleId(Long id);
}
