package com.ssafy.heypapa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.heypapa.request.QuizRequest;
import com.ssafy.heypapa.service.QuizService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "유저 api", tags = { "User" })
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private QuizService quizService;
	
	@GetMapping("/{type}/all")
	@ApiOperation(value = "종류별 퀴즈 전체 목록", notes = "<strong>퀴즈 봉류 별 전체 리스트</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<QuizRequest>> getAllQuiz(Pageable pageable) {
		List<QuizRequest> quizList = quizService.getAllQuiz(pageable);
		return ResponseEntity.status(200).body(quizList);
	}
}
