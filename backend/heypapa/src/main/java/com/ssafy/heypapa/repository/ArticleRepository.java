package com.ssafy.heypapa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.heypapa.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{

}
