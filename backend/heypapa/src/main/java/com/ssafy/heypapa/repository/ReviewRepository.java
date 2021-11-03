package com.ssafy.heypapa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.heypapa.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
	List<Review> findByArticleId(Long id);
}
