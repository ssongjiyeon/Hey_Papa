package com.ssafy.heypapa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.heypapa.entity.Article;
import com.ssafy.heypapa.request.ArticleRequest;
import com.ssafy.heypapa.response.ArticleResponse;
import com.ssafy.heypapa.service.ArticleService;
import com.ssafy.heypapa.util.BaseResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "게시판 api", tags = { "Article" })
@RestController
@RequestMapping("/article")
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
	
	@GetMapping()
	@ApiOperation(value = "게시글 전체 목록", notes = "<strong>게시글 전체 리스트</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<ArticleResponse>> getAllArticle(Pageable pageable) {
		List<ArticleResponse> articleList = articleService.getAllArticle(pageable);
		return ResponseEntity.status(200).body(articleList);
	}
	
	@PutMapping("/{article_id}")
	@ApiOperation(value = "게시글 수정", notes = "<strong>게시글 수정하기</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<BaseResponseBody> updateArticle(@RequestBody ArticleRequest articleRequest, @PathVariable Long id) {
		Article article = articleService.updateArticle(articleRequest, id);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@PostMapping("/{article_id}/like")
	@ApiOperation(value = "좋아요", notes = "<strong>좋아요 하기</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<BaseResponseBody> likeArticle(@RequestParam("flag") Boolean flag, @PathVariable Long id) {
		articleService.likeArticle(flag, id);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

}
