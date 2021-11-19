package com.ssafy.heypapa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ssafy.heypapa.entity.ArticleGallery;
import com.ssafy.heypapa.entity.ArticleGalleryDto;
import com.ssafy.heypapa.repository.ArticleGalleryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArticleGalleryService {

	private S3Service s3Service;
	
	private ArticleGalleryRepository articlegalleryRepository;
	
	public ArticleGallery savePost(ArticleGalleryDto articlegalleryDto) {
		return articlegalleryRepository.save(articlegalleryDto.toEntity());
	}
	
	public List<ArticleGalleryDto> getList() {
        List<ArticleGallery> articlegalleryEntityList = articlegalleryRepository.findAll();
        List<ArticleGalleryDto> articlegalleryDtoList = new ArrayList<>();

        for (ArticleGallery articlegalleryEntity : articlegalleryEntityList) {
            articlegalleryDtoList.add(convertEntityToDto(articlegalleryEntity));
        }

        return articlegalleryDtoList;
    }

	private ArticleGalleryDto convertEntityToDto(ArticleGallery articlegallery) {
        return ArticleGalleryDto.builder()
                .id(articlegallery.getId())
                .filePath(articlegallery.getFilePath())
//                .imgFullPath("https://" + s3Service.CLOUD_FRONT_DOMAIN_NAME + "/" + articlegallery.getFilePath())
                .build();
    }

	public Optional<ArticleGallery> getGallery(Long aId) {
		return articlegalleryRepository.findById(aId);
	}

	public void deleteGallery(Long id) {
		articlegalleryRepository.deleteById(id);
	}

	public ArticleGallery findByArticleId(Long aId) {
		return articlegalleryRepository.findByArticleId(aId);
	}

	public Optional<ArticleGallery> getArtiImg(Long aId) {
		return articlegalleryRepository.findByArticle_id(aId);
	}

}
