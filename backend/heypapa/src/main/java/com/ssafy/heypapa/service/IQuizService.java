package com.ssafy.heypapa.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ssafy.heypapa.entity.Comment;
import com.ssafy.heypapa.request.CommentRequest;
import com.ssafy.heypapa.request.QuizRequest;
import com.ssafy.heypapa.response.QuizResponse;

public interface IQuizService {

	QuizResponse getoneQuiz(Long id); // 하나의 퀴즈 보기
	
	List<QuizRequest> getAllQuiz(Pageable pageable); // 타입별로 퀴즈 리스트 보기
	
	Comment createComment(Long id, CommentRequest comment); // 퀴즈 댓글 달기
	
//	Comment updateComment(Long id, Long qId, CommentRequest comment); // 퀴즈 댓글 수정하기
	
	void deleteComment(Long qId, Long cId);
	
	
}
