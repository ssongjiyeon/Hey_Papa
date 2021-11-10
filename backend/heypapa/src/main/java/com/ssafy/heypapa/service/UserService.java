package com.ssafy.heypapa.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.heypapa.entity.Article;
import com.ssafy.heypapa.entity.ArticleLike;
import com.ssafy.heypapa.entity.MyQuiz;
import com.ssafy.heypapa.entity.Quiz;
import com.ssafy.heypapa.entity.Review;
import com.ssafy.heypapa.entity.User;
import com.ssafy.heypapa.repository.ArticleLikeRepository;
import com.ssafy.heypapa.repository.ArticleRepository;
import com.ssafy.heypapa.repository.MyQuizRepository;
import com.ssafy.heypapa.repository.QuizRepository;
import com.ssafy.heypapa.repository.ReviewRepository;
import com.ssafy.heypapa.repository.UserRepository;
import com.ssafy.heypapa.request.RegistRequest;
import com.ssafy.heypapa.request.UserModifyRequest;
import com.ssafy.heypapa.request.UserRequest;
import com.ssafy.heypapa.response.MyArticleResponse;
import com.ssafy.heypapa.response.MyQuizResponse;
import com.ssafy.heypapa.response.ProfileResponse;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	ArticleLikeRepository articleLikeRepository;
	
	@Autowired
	QuizRepository quizRepository;
	
	@Autowired
	MyQuizRepository myquizRepository;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	final String[] preNickname = new String[] 
			{"예쁜 ", "멋진 ", "우아한 ", "활발한 ", "고상한 ", "귀여운 ", "다정한 ", "대담한 ", "잘생긴 ", "따뜻한 ", "매력적인 ",
			  "명량한 ", "성실한 ", "신중한 ", "용감한 ", "수줍은 " };
	
	final String BASE_PATH = "/home/ubuntu/img/";

	public User getUserByNickname(String username) {
		Optional<User> user = userRepository.findByNickname(username);
		return user.orElse(null);
	}

	@Override
	public User getUserByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
	
		return user.orElse(null);
	}

	private boolean isSave(String email, MultipartFile userThumbnail, String path) {
		
        try {
        	// 이미지 저장
            File dest = new File(BASE_PATH + path);
            
            userThumbnail.transferTo(dest);

	        if(!dest.exists()) {
	            return false;
	        }
	        
	        return true;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return false;
	}

	@Override
	public User createUser(RegistRequest req) {
		User user = new User();
		
		try {
			user.setD_day(changeDate(req.getDDay()));
			
			user.setEmail(req.getEmail());
			user.setRegion(req.getRegion());
			user.setPassword(passwordEncoder.encode(req.getPassword()));
			user.setNickname(makeNickname(req.getNickname()));

			user.setWeek(req.getWeek());
			user.setImg("NULL");
//			// 이미지 저장
//			String newPath = "user/" + req.getEmail() + "-" + userThumbnail.getOriginalFilename();
//            File dest = new File(BASE_PATH + newPath);
//            userThumbnail.transferTo(dest);
//
//            if(!dest.exists()) {
//                System.out.println("파일 업로드 실패");
//            }else {
//            	user.setImg(newPath);
//            }

			userRepository.save(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

	public Date changeDate(String date) throws ParseException {
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		Date res = fm.parse(date);
		return res;
	}
	
	@Override
	public boolean putUser(long userId, UserModifyRequest req) {
		
		try {
			
			Optional<User> user = userRepository.findById(userId);
			
			if(!user.isPresent()) {
				return false;
			}
			
			user.get().setD_day(changeDate(req.getDDay()));
			user.get().setRegion(req.getRegion());
			user.get().setPassword(passwordEncoder.encode(req.getPassword()));

			if(!user.get().getNickname().equals(req.getNickname())) {
				user.get().setNickname(makeNickname(req.getNickname()));
			}
			
			user.get().setWeek(req.getWeek());
			userRepository.save(user.get());
			
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public String makeNickname(String nickname) {
		// 닉네임 중복 처리
		
		String nowNickname = nickname;
		String newNickname = nickname;
		Optional<User> dUser = userRepository.findByNickname(nowNickname);
		Random rand = new Random();
		while(dUser.isPresent()) {
			newNickname = preNickname[rand.nextInt(16)] + nowNickname;
			dUser = userRepository.findByNickname(newNickname);
		}
		
		return newNickname;
	}

	@Override
	public ProfileResponse getProfile(long userId) {
		ProfileResponse res = new ProfileResponse();
		Optional<User> user = userRepository.findById(userId);
		
		if(!user.isPresent()) {
			return null;
		}
		
		res.setD_day(user.get().getD_day());
		res.setImg(user.get().getImg());
		res.setNickname(user.get().getNickname());
		res.setRegion(user.get().getRegion());
		res.setWeek(user.get().getWeek());
		
		return res;
	}

	@Override
	public List<MyArticleResponse> getArticle(long userId) {
		List<MyArticleResponse> res = new ArrayList<>();
		Optional<User> user = userRepository.findById(userId);
		
		if(user == null) return null;
		
		List<Article> articles = articleRepository.findByUser(user.get());
		for(Article article : articles) {
			MyArticleResponse mArticle = new MyArticleResponse();
			
			mArticle.setContent(article.getContent());
			mArticle.setId(article.getId());
			mArticle.setImg(article.getImg());
			mArticle.setCreated_at(article.getCreated_at());
			
			List<ArticleLike> like = articleLikeRepository.findByArticleId(article.getId());
			mArticle.setLike_cnt(like.size());
			
			List<Review> review = reviewRepository.findByArticleId(article.getId());
			mArticle.setComment_cnt(review.size());
			res.add(mArticle);
		}
		
		return res;
	}

	@Override
	public List<MyQuizResponse> getQuiz(long userId) {
		List<MyQuizResponse> res = new ArrayList<>();
		Optional<User> user = userRepository.findById(userId);
		
		if(user == null) return null;
		
		List<MyQuiz> myquizs = myquizRepository.findByUser(user.get());
		for(MyQuiz mq : myquizs) {
			MyQuizResponse mQuiz = new MyQuizResponse();
//			mQuiz.setId(mq.getId());
			mQuiz.setQuizcheck(mq.isQuizcheck());
			mQuiz.setQuizlike(mq.isQuizlike());
			mQuiz.setQuiz_id(mq.getQuiz().getId());
			mQuiz.setQuestion(mq.getQuiz().getQuestion());
			mQuiz.setType(mq.getQuiz().getType());
			mQuiz.setAnswer(mq.getQuiz().getAnswer());
			mQuiz.setAnswer2(mq.getQuiz().getAnswer2());
			mQuiz.setCandidate(mq.getQuiz().getCandidate());
			mQuiz.setDescription(mq.getQuiz().getDescription());
//			mQuiz.setImg(mq.getQuiz().getImg());
			
			List<MyQuiz> myquiz = myquizRepository.findByQuizId(mq.getId());
			res.add(mQuiz);
		}
		
		return res;
	}

	@Override
	public List<MyArticleResponse> getLikeArticle(long userId) {
		List<ArticleLike> likes = articleLikeRepository.findByUserId(userId);
		List<MyArticleResponse> res = new ArrayList<>();
		
		for(ArticleLike like : likes) {
			if(userId == like.getArticle().getUser().getId()) continue;
			
			MyArticleResponse lArticle = new MyArticleResponse();
			lArticle.setContent(like.getArticle().getContent());
			lArticle.setId(like.getArticle().getId());
			lArticle.setImg(like.getArticle().getImg());
			lArticle.setCreated_at(like.getArticle().getCreated_at());
			
			List<ArticleLike> likeCnt = articleLikeRepository.findByArticleId(like.getArticle().getId());
			lArticle.setLike_cnt(likeCnt.size());
			
			List<Review> review = reviewRepository.findByArticleId(like.getArticle().getId());
			lArticle.setComment_cnt(review.size());
			res.add(lArticle);
		}
		
		
		return res;
	}

}
