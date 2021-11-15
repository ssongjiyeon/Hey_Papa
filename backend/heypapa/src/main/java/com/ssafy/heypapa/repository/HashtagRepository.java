package com.ssafy.heypapa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.heypapa.entity.Hashtag;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long>{
	public Optional<Hashtag> findById(Long id);
	public Optional<Hashtag> findByName(String name);
	List<Hashtag> findByNameLike(String name);
}
