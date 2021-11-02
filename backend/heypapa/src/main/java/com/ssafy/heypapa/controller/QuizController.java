package com.ssafy.heypapa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.heypapa.entity.Comment;
import com.ssafy.heypapa.entity.QuizTypeEnum;
import com.ssafy.heypapa.request.CommentRequest;
import com.ssafy.heypapa.request.QuizRequest;
import com.ssafy.heypapa.response.QuizResponse;
import com.ssafy.heypapa.service.QuizService;
import com.ssafy.heypapa.util.BaseResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "퀴즈 api", tags = { "Quiz" })
@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuizService quizService;
	
	@GetMapping("/{type}")
	@ApiOperation(value = "퀴즈 전체 목록", notes = "<strong>퀴즈 전체 리스트</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<QuizRequest>> getAllQuiz(Pageable pageable) {
		List<QuizRequest> quizList = quizService.getAllQuiz(pageable);
		return ResponseEntity.status(200).body(quizList);
	}
	
	@GetMapping("/{quizId}")
	@ApiOperation(value = "퀴즈 상세", notes = "<strong>하나의 퀴즈 보기</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<QuizResponse> getOneQuiz(@PathVariable(name = "quizId") Long id) {
		QuizResponse quiz = quizService.getoneQuiz(id);
		return ResponseEntity.status(200).body(quiz);
	}
	
	// 퀴즈 댓글
	@PostMapping("/{quizId}")
	@ApiOperation(value = "퀴즈 댓글 작성", notes = "<strong>퀴즈 하나당 댓글 보기</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<BaseResponseBody> createComment(@PathVariable(name = "quizId") Long id, 
			@RequestBody CommentRequest comment) {
		Comment comments = quizService.createComment(id, comment);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@DeleteMapping("/{quizId}/{commentId}")
	@ApiOperation(value = "퀴즈 댓글 삭제", notes = "<strong>퀴즈 댓글 삭제 하기</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<BaseResponseBody> deleteComment(@PathVariable(name = "quizId") Long qId,
			@PathVariable(name = "commentId") Long cId) {
		quizService.deleteComment(qId, cId);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@GetMapping("/아내")
	@ApiOperation(value = "아내 종류 퀴즈", notes = "<strong>아내 종류 퀴즈 리스트</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<QuizRequest>> getWifeQuiz(String type) {
		List<QuizRequest> quizList = quizService.getWifeQuiz(type);
		return ResponseEntity.status(200).body(quizList);
	}
	
}
