package com.ssafy.heypapa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.heypapa.entity.Article;
import com.ssafy.heypapa.request.ArticleRequest;
import com.ssafy.heypapa.service.ArticleService;
import com.ssafy.heypapa.util.BaseResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "게시판 api", tags = { "Article" })
@RestController
@RequestMapping("/heypapa/article")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@PostMapping()
	@ApiOperation(value = "게시글 생성", notes = "<strong>게시글 생성하기</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<BaseResponseBody> createArticle(@RequestBody ArticleRequest articleRequest) {
		Article article = articleService.createArticle(articleRequest);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

}
