package com.ssafy.heypapa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.heypapa.entity.ArticleGallery;

@Repository
public interface ArticleGalleryRepository extends JpaRepository<ArticleGallery, Long> {

	List<ArticleGallery> findAll();
	ArticleGallery findByArticleId(Long aid);
	Optional<ArticleGallery> findByArticle_id(Long aid);
}
