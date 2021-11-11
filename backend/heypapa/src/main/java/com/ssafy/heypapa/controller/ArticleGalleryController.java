package com.ssafy.heypapa.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.heypapa.entity.ArticleGallery;
import com.ssafy.heypapa.entity.ArticleGalleryDto;
import com.ssafy.heypapa.service.ArticleGalleryService;
import com.ssafy.heypapa.service.S3Service;
import com.ssafy.heypapa.util.BaseResponseBody;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@Api(value = "aws+s3 연동 게시글 파일 처리 API", tags = { "ArticleGallery" })
@RestController
@RequestMapping("/ArticleGallery")
public class ArticleGalleryController {

	private S3Service s3Service;
	
	private ArticleGalleryService articlegalleryService;
	
//	@GetMapping("/articlegallery")
//    public String dispWrite(Model model) {
//        List<ArticleGalleryDto> articlegalleryDtoList = articlegalleryService.getList();
//
//        model.addAttribute("articlegalleryList", articlegalleryDtoList);
//
//        return "/articlegallery";
//    }
//	
//	@PostMapping("/saveInDB")
//	public ArticleGallery createGallery(@RequestBody ArticleGalleryDto articlegalleryDto, Long aId) {
//		SimpleDateFormat date = new SimpleDateFormat("yyyymmddHHmmss");
//		String fileName = articlegalleryDto.getFilePath() + "-" + date.format(new Date());
//		articlegalleryDto.setFilePath(fileName);
//		articlegalleryDto.setArticle(aId);
//
//		// DB에 저장한 객체를 반환한다. 이 객체를 반환받아, 다시 S3에 파일로 저장하는 걸 진행해야 한다.
//		ArticleGallery result = articlegalleryService.savePost(articlegalleryDto);
//
//		return result;
//	}
//	
//	// 게시글 이미지 업로드
//	@PostMapping("/{article_id}/articleUpload")
//	public String profileUpload(@RequestBody MultipartFile file,
//								@PathVariable Long article_id) throws IOException  {
//		SimpleDateFormat date = new SimpleDateFormat("yyyymmddHHmmss");
//		String fileName = file.getOriginalFilename() + "-" + date.format(new Date());
//
//		ArticleGalleryDto articlegalleryDto = new ArticleGalleryDto();
//
//		// 이전에 등록한 이미지가 있는지를 확인해야 한다.
//		Optional<ArticleGallery> result = articlegalleryService.getArtiImg(article_id);
//		if (result.isPresent() == true) {
//			// 기존의 객체가 존재한다면
//			articlegalleryDto.setId(result.get().getId());
//		}
//
//		// gallery에 저장해야 한다.
//		articlegalleryDto.setFilePath(fileName);
//		articlegalleryDto.setArticle(article_id);
//		articlegalleryService.savePost(articlegalleryDto);
//
//		s3Service.upload(fileName, file);
//		return fileName;
//	}
//	
//	// 게시글 이미지 가져오기
//	@PostMapping("/{article_id}/getArticleImg")
//	public String getPropImg(@PathVariable Long article_id) {
//		// 갤러리에서 aId 이용해서  이미지 찾기.
//		Optional<ArticleGallery> result = articlegalleryService.getArtiImg(article_id);
//		if (result.isPresent() == false) {
//			// 등록한 객체가 없는 경우
//			return "등록이미지 없음";
//		}
//		else {
//			return result.get().getFilePath();
//		}
//	}
//	
//	@PostMapping("/{articlegalleryId}/S3Upload")
//	public ResponseEntity<BaseResponseBody> execWrite(@RequestBody MultipartFile file, @PathVariable("articlegalleryId") Long gId) throws IOException {
//		System.out.println(file.toString());
//
//		ArticleGallery articlegallery = articlegalleryService.getGallery(gId).get();
//		ArticleGalleryDto articlegalleryDto = new ArticleGalleryDto();
//		articlegalleryDto.setArticle(articlegallery.getArticle().getId());
//		articlegalleryDto.setId(articlegallery.getId());
//		articlegalleryDto.setFilePath(articlegallery.getFilePath());
//
//		// s3Service는 AWS S3의 비즈니스 로직을 담당하며, 파일을 조작
//		String imgPath = s3Service.upload(articlegalleryDto.getFilePath(), file);
//		articlegalleryDto.setFilePath(imgPath);
//		
//		// galleryService는 DB에 데이터를 조작하기 위한 서비스
//		articlegalleryService.savePost(articlegalleryDto);
//
//		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "성공적으로 파일을 업로드하였습니다."));
//	}
}
