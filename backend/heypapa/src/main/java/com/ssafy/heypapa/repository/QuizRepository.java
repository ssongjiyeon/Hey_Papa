package com.ssafy.heypapa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.heypapa.entity.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
