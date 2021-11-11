package com.ssafy.heypapa.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ssafy.heypapa.entity.Comment;
import com.ssafy.heypapa.entity.Quiz;
import com.ssafy.heypapa.entity.QuizTypeEnum;
import com.ssafy.heypapa.request.CommentRequest;
import com.ssafy.heypapa.request.MyQuizRequest;
import com.ssafy.heypapa.request.QuizRequest;
import com.ssafy.heypapa.response.QuizResponse;

public interface IQuizService {

	QuizResponse getoneQuiz(Long id, long userId); // 하나의 퀴즈 보기
	
//	List<QuizRequest> getAllQuiz(Pageable pageable); // 퀴즈 전체 리스트
	
	Comment createComment(Long id, CommentRequest comment); // 퀴즈 댓글 달기
	
//	Comment updateComment(Long id, Long qId, CommentRequest comment); // 퀴즈 댓글 수정하기
	
	void deleteComment(Long qId, Long cId); // 댓글 삭제하기
	
	Quiz findByQuizId(Long quiz_id); // 퀴즈 아이디 찾기
	
	// 퀴즈 타입별 리스트 출력
	List<QuizRequest> getAllWifeQuiz();
	List<QuizRequest> getAllBabyQuiz();
	List<QuizRequest> getAllFoodQuiz();
	List<QuizRequest> getAllSocietyQuiz();
	
	void myQuiz(MyQuizRequest myquizRequest, Long id);
	
}
