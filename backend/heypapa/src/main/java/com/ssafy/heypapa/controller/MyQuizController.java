package com.ssafy.heypapa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.heypapa.entity.MyQuiz;
import com.ssafy.heypapa.request.MyQuizRequest;
import com.ssafy.heypapa.service.MyQuizService;
import com.ssafy.heypapa.util.BaseResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "나의 퀴즈 api", tags = { "MyQuiz" })
@RestController
@RequestMapping("/myquiz")
public class MyQuizController {

	@Autowired
	private MyQuizService myquizService;
	
//	@SuppressWarnings("unused")
//	@PostMapping()
//	@ApiOperation(value = "나의 퀴즈", notes = "<strong>나의 퀴즈로 담기</strong>")
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "성공"),
//        @ApiResponse(code = 401, message = "토큰 인증 실패"),
//        @ApiResponse(code = 500, message = "서버 오류")
//	})
//	public ResponseEntity<BaseResponseBody> createMyQuiz(@RequestBody MyQuizRequest myquizRequest) {
//		MyQuiz myquiz = myquizService.createMyQuiz(myquizRequest);
//		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
//	}
//	
//	@DeleteMapping("/{MyQuizId}")
//	@ApiOperation(value = "나의 퀴즈 취소", notes = "<strong>나의 퀴즈로 담기 취소하기</strong>")
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "성공"),
//        @ApiResponse(code = 401, message = "토큰 인증 실패"),
//        @ApiResponse(code = 500, message = "서버 오류")
//	})
//	public ResponseEntity<BaseResponseBody> deleteMyQuiz(@PathVariable(name = "myquizId") Long id) {
//		myquizService.deleteMyQuiz(id);
//		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
//	}
	
}
