package com.ssafy.heypapa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.heypapa.entity.Article;
import com.ssafy.heypapa.entity.User;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{
	public Optional<Article> findById(Long id);
	public List<Article> findAll();
	public List<Article> findByUser(User user);
	public Optional<Article> findByImg(Long article_id);
}
