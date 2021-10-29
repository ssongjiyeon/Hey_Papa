package com.ssafy.heypapa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.heypapa.entity.MyQuiz;

@Repository
public interface MyQuizRepository extends JpaRepository<MyQuiz, Long> {

}
