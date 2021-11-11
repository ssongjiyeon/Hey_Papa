package com.ssafy.heypapa.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.heypapa.entity.Article;
import com.ssafy.heypapa.entity.ArticleDto;
import com.ssafy.heypapa.entity.Review;
import com.ssafy.heypapa.request.ArticleLikeRequest;
import com.ssafy.heypapa.request.ArticleRequest;
import com.ssafy.heypapa.request.ReviewRequest;
import com.ssafy.heypapa.response.ArticleResponse;
import com.ssafy.heypapa.response.ReviewResponse;
import com.ssafy.heypapa.service.ArticleService;
import com.ssafy.heypapa.service.S3Service;
import com.ssafy.heypapa.util.BaseResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "게시판 api", tags = { "Article" })
@RestController
@RequestMapping("/article")
public class ArticleController {
	
	private S3Service s3Service;
	
	@Autowired
	private ArticleService articleService;
	
	@PostMapping()
	@ApiOperation(value = "게시글 생성", notes = "<strong>게시글 생성하기</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<BaseResponseBody> createArticle(@ApiParam(value = "게시글정보", required = true) ArticleRequest articleRequest,
			@RequestPart(value = "article_thumbnail", required = false) MultipartFile articleThumbnail) {
		Article article = articleService.createArticle(articleRequest, articleThumbnail);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@GetMapping("/all/{user_id}")
	@ApiOperation(value = "게시글 전체 목록", notes = "<strong>게시글 전체 리스트</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<ArticleResponse>> getAllArticle(Pageable pageable, @PathVariable("user_id") long userId) {
		List<ArticleResponse> articleList = articleService.getAllArticle(pageable, userId);
		return ResponseEntity.status(200).body(articleList);
	}

	@PostMapping("/{article_id}")
	@ApiOperation(value = "댓글 작성", notes = "<strong>댓글 작성하기</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<BaseResponseBody> createReview(@RequestBody ReviewRequest reviewRequest, @PathVariable Long article_id) {
		Review review = articleService.createReview(reviewRequest, article_id);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@GetMapping("/{article_id}")
	@ApiOperation(value = "댓글 보기", notes = "<strong>댓글 보기</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<ReviewResponse>> getReview(@PathVariable Long article_id) {
		List<ReviewResponse> reviewList = articleService.getReview(article_id);
		return ResponseEntity.status(200).body(reviewList);
		
	}
	
	@PutMapping("/{article_id}")
	@ApiOperation(value = "게시글 수정", notes = "<strong>게시글 수정하기</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<BaseResponseBody> updateArticle(@RequestBody ArticleRequest articleRequest, @PathVariable Long article_id) {
		Article article = articleService.updateArticle(articleRequest, article_id);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
		
	@DeleteMapping("/{article_id}")
	@ApiOperation(value = "게시글 삭제", notes = "<strong>게시글 삭제하기</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<BaseResponseBody> deleteArticle(@PathVariable Long article_id) {
		articleService.deleteArticle(article_id);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@PostMapping("/{article_id}/like")
	@ApiOperation(value = "좋아요", notes = "<strong>좋아요 하기</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<BaseResponseBody> likeArticle(@RequestBody ArticleLikeRequest articleLikeRequest, @PathVariable Long article_id) {
		articleService.likeArticle(articleLikeRequest, article_id);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
}
