package com.ssafy.heypapa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.heypapa.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	Optional<List<Comment>> findByQuiz_id(Long quiz_id);
	
	List<Comment> findByQuizId(Long quizId);
	
}
