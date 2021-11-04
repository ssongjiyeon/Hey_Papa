package com.ssafy.heypapa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.heypapa.entity.MyQuiz;
import com.ssafy.heypapa.entity.Quiz;
import com.ssafy.heypapa.entity.User;

@Repository
public interface MyQuizRepository extends JpaRepository<MyQuiz, Long> {

	List<MyQuiz> findByQuizId(Long id);
	
	Optional<MyQuiz> findByQuizAndUserId(Long quizId, Long userId);
	
	List<MyQuiz> findByUser(User user);

}
