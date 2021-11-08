package com.ssafy.heypapa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.heypapa.entity.Article;
import com.ssafy.heypapa.entity.ArticleLike;

public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Long>{
	List<ArticleLike> findByArticleId(Long id);
	Optional<ArticleLike> findByArticleIdAndUserId(Long articlrId, Long userId);
	List<ArticleLike> findByUserId(long userId);
	ArticleLike findByUserIdAndArticleId(long userId, Long id);
}
