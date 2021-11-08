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
	public ResponseEntity<BaseResponseBody> createArticle(@RequestBody ArticleRequest articleRequest) {
		Article article = articleService.createArticle(articleRequest);
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
	
//	@GetMapping("/file")
//	public String dispWrite(Model model) {
//		List<ArticleDto> articleDtoList = articleService.getList();
//		model.addAttribute("articleList", articleDtoList);
//		return "/file";
//	}
//	
//	@PostMapping("/file/saveInDB") 
//	public Article createArticleFile(@RequestBody ArticleDto articleDto) {
//		SimpleDateFormat date = new SimpleDateFormat("yyyymmddHHmmss");
//		String fileName = articleDto.getImg() + "-" + date.format(new Date());
//		articleDto.setImg(fileName);
//			
//		// DB에 저장한 객체를 반환한다. 이 객체를 반환받아, 다시 S3에 파일로 저장하는 걸 진행해야 한다.
//		Article result = articleService.savePost(articleDto);
//			
//		return result;
//	}
//	
//	// 게시글 파일 업로드
//	@PostMapping("/{article_id}/fileUpload")
//	public String fileUpload(@RequestBody MultipartFile file, @PathVariable("article_id") Long article_id) throws IOException {
//		SimpleDateFormat date = new SimpleDateFormat("yyyymmddHHmmss");
//		String fileName = file.getOriginalFilename() + "-" + date.format(new Date());
//		ArticleDto articleDto = new ArticleDto();
//		
//		// 이전에 등록한 이미지가 있는지 확인
//		Optional<Article> result = articleService.getImg(article_id);
//		if(result.isPresent() == true) {
//			// 기존의 객체가 존재한다면
//			articleDto.setId(result.get().getId());
//		}
//		
//		articleDto.setImg(fileName);
////		articleService.savePost(articleDto);
//		s3Service.upload(fileName, file);
//		
//		return fileName;
//	}
//	
//	// 게시글 이미지 가져오기
//	@PostMapping("/{article_id}/getImg")
//	public String getImg(@PathVariable Long article_id) {
//		Optional<Article> result = articleService.getImg(article_id);
//		String res = result.get().getImg();
//		if(res == null) {
//			return "등록이미지 없음";
//		} else {
//			return result.get().getImg();
//		}
//	}
//	
//	@PostMapping("/{article_id}/s3Upload")
//	public ResponseEntity<BaseResponseBody> execWrite(@RequestBody MultipartFile file,
//			@PathVariable("article_id") Long aId) throws IOException {
//		System.out.println(file.toString());
//		
//		Article article = articleService.getArticle(aId).get();
//		ArticleDto articleDto = new ArticleDto();
//		articleDto.setId(article.getId());
//		articleDto.setImg(article.getImg());
//		
//		// s3Service는 AWS S3의 비즈니스 로직을 담당하며, 파일을 조작
//		String imgPath = s3Service.upload(articleDto.getImg(), file);
//		articleDto.setImg(imgPath);
//		
//		// DB에 데이터를 조작하기 위한 서비스
//		articleService.savePost(articleDto);
//		
//		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "성공적으로 파일 업로드 완료!"));
//	}
	
}
