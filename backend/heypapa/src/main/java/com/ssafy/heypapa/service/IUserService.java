package com.ssafy.heypapa.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.heypapa.entity.User;
import com.ssafy.heypapa.request.RegistRequest;
import com.ssafy.heypapa.request.UserModifyRequest;
import com.ssafy.heypapa.request.UserRequest;
import com.ssafy.heypapa.response.MyArticleResponse;
import com.ssafy.heypapa.response.MyQuizResponse;
import com.ssafy.heypapa.response.ProfileResponse;

public interface IUserService {

	User getUserByNickname(String username);
	User getUserByEmail(String email);
	User createUser(RegistRequest req, MultipartFile userThumbnail);
	boolean putUser(long userId, UserModifyRequest req);
	ProfileResponse getProfile(long userId);
	List<MyArticleResponse> getArticle(long userId);
	List<MyQuizResponse> getQuiz(long userId);
	List<MyArticleResponse> getLikeArticle(long userId);
}
