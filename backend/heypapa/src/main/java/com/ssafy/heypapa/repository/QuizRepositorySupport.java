package com.ssafy.heypapa.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.heypapa.entity.QQuiz;
import com.ssafy.heypapa.entity.Quiz;

@Repository
public class QuizRepositorySupport {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QQuiz qQuiz = QQuiz.quiz;
	
	public Optional<Quiz> findQuizByQuizid(Long quiz_id) {
		Quiz quiz = jpaQueryFactory.select(qQuiz).from(qQuiz)
				.where(qQuiz.id.eq(quiz_id)).fetchOne();
		if(quiz == null) return Optional.empty();
		return Optional.ofNullable(quiz);
	}
	
}
