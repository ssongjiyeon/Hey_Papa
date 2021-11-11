package com.ssafy.heypapa.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ssafy.heypapa.entity.ArticleLike;
import com.ssafy.heypapa.entity.Comment;
import com.ssafy.heypapa.entity.MyQuiz;
import com.ssafy.heypapa.entity.Quiz;
import com.ssafy.heypapa.entity.QuizTypeEnum;
import com.ssafy.heypapa.entity.User;
import com.ssafy.heypapa.repository.CommentRepository;
import com.ssafy.heypapa.repository.MyQuizRepository;
import com.ssafy.heypapa.repository.QuizRepository;
import com.ssafy.heypapa.repository.QuizRepositorySupport;
import com.ssafy.heypapa.repository.UserRepository;
import com.ssafy.heypapa.request.CommentRequest;
import com.ssafy.heypapa.request.MyQuizRequest;
import com.ssafy.heypapa.request.QuizRequest;
import com.ssafy.heypapa.response.CommentResponse;
import com.ssafy.heypapa.response.QuizResponse;

@Service("QuizService")
public class QuizService implements IQuizService {

	private TimeService timeService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	QuizRepository quizRepository;
	
	@Autowired
	QuizRepositorySupport quizRepositorySupport;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	MyQuizRepository myquizRepository;
	
	private S3Service s3Service;
	
	@Override
	public QuizResponse getoneQuiz(Long id, long userId) {
		Quiz quiz = quizRepository.findById(id).get();
		QuizResponse q = new QuizResponse();
		q.setId(quiz.getId());
		q.setQuestion(quiz.getQuestion());
		q.setAnswer(quiz.getAnswer());
		q.setAnswer2(quiz.getAnswer2());
		q.setCandidate(quiz.getCandidate());
		q.setDescription(quiz.getDescription());
		q.setType(quiz.getType());
		// 퀴즈 찜 했는지 처리
		MyQuiz isLike = myquizRepository.findByUserIdAndQuizId(userId, q.getId());
		if(isLike == null) {
			q.setLike(false);
		} else {
			q.setLike(true);
		}
		List<Comment> clist = commentRepository.findByQuiz_id(quiz.getId()).get();
		if(clist != null) {
			List<CommentResponse> comments = new ArrayList<>();
			for(Comment c : clist) {
				User user = c.getUser();
				CommentResponse cr = new CommentResponse();
				cr.setUser_id(user.getId());
				cr.setNickname(user.getNickname());
				cr.setUser_img(user.getImg());
				cr.setContent(c.getContent());
				cr.setCreated_at(c.getCreated_at());
				cr.setCalculateTime(timeService.calculateTime(c.getCreated_at()));
				comments.add(cr);
			}
			q.setComments(comments);
		}
		return q;
	}

	@Override
	public Comment createComment(Long id, CommentRequest comment) {
		Comment com = new Comment();
		Quiz quiz = quizRepository.findById(id).get();
		Long userId = comment.getUser_id();
		User user = userRepository.findById(userId).get();
//		com.setUser(userRepository.findByNickname(comment.getNickname()).get());
		com.setUser(user);
		com.setQuiz(quiz);
		com.setContent(comment.getContent());
		com.setCreated_at(new Date());
		return commentRepository.save(com);
	}

	@Override
	public void deleteComment(Long qId, Long cId) {
		commentRepository.deleteById(cId);
	}

	@Override
	public Quiz findByQuizId(Long quiz_id) {
		Quiz quiz = quizRepositorySupport.findQuizByQuizid(quiz_id).get();
		return quiz;
	}

	@Override
	public List<QuizRequest> getAllWifeQuiz() {
//		List<Quiz> list = quizRepository.findAllByType('아내'.toString());
		List<Quiz> list = quizRepository.findAll();
		List<QuizRequest> copy = new ArrayList<>();
		QuizRequest qres;
		for(Quiz q : list) {
			qres = new QuizRequest();
			if(q.getType().equals("아내")) {
				qres.setType(q.getType());
				qres.setId(q.getId());
				qres.setQuestion(q.getQuestion());
				qres.setAnswer(q.getAnswer());
				qres.setAnswer2(q.getAnswer2());
				qres.setCandidate(q.getCandidate());
				qres.setDescription(q.getDescription());
				copy.add(qres);
			}
		}
		return copy;
	}

	@Override
	public List<QuizRequest> getAllBabyQuiz() {
		List<Quiz> list = quizRepository.findAll();
		List<QuizRequest> copy = new ArrayList<>();
		QuizRequest qres;
		for(Quiz q : list) {
			qres = new QuizRequest();
			if(q.getType().equals("아기")) {
				qres.setType(q.getType());
				qres.setId(q.getId());
				qres.setQuestion(q.getQuestion());
				qres.setAnswer(q.getAnswer());
				qres.setAnswer2(q.getAnswer2());
				qres.setCandidate(q.getCandidate());
				qres.setDescription(q.getDescription());
				copy.add(qres);
			} 
		}
		return copy;
	}

	@Override
	public List<QuizRequest> getAllFoodQuiz() {
		List<Quiz> list = quizRepository.findAll();
		List<QuizRequest> copy = new ArrayList<>();
		QuizRequest qres;
		for(Quiz q : list) {
			qres = new QuizRequest();
			if(q.getType().equals("음식")) {
				qres.setType(q.getType());
				qres.setId(q.getId());
				qres.setQuestion(q.getQuestion());
				qres.setAnswer(q.getAnswer());
				qres.setAnswer2(q.getAnswer2());
				qres.setCandidate(q.getCandidate());
				qres.setDescription(q.getDescription());
				copy.add(qres);
			} 
		}
		return copy;
	}

	@Override
	public List<QuizRequest> getAllSocietyQuiz() {
		List<Quiz> list = quizRepository.findAll();
		List<QuizRequest> copy = new ArrayList<>();
		QuizRequest qres;
		for(Quiz q : list) {
			qres = new QuizRequest();
			if(q.getType().equals("사회")) {
				qres.setType(q.getType());
				qres.setId(q.getId());
				qres.setQuestion(q.getQuestion());
				qres.setAnswer(q.getAnswer());
				qres.setAnswer2(q.getAnswer2());
				qres.setCandidate(q.getCandidate());
				qres.setDescription(q.getDescription());
				copy.add(qres);
			} 
		}
		return copy;
	}

	@Override
//	public void myQuiz(MyQuizRequest myquizRequest, Long id) {
//		Quiz quiz = quizRepository.findById(id).get();
//		User user = userRepository.findById(myquizRequest.getUser_id()).get();
//		MyQuiz myquiz = new MyQuiz();
//		if(myquizRequest.getQuizlike()==false) {
//			myquiz.setQuizcheck(myquizRequest.getQuizcheck());
//			myquiz.setQuizlike(myquizRequest.getQuizlike());
//			myquiz.setQuiz(quiz);
//			myquiz.setUser(user);
//			myquizRepository.save(myquiz);
//		} 		
//	}

	public void myQuiz(MyQuizRequest myquizRequest, Long id) {
		if(myquizRequest.getQuizlike()==false) {
			Long userId = myquizRequest.getUser_id();
			Optional<MyQuiz> like = myquizRepository.findByQuizIdAndUserId(id, userId);
			
			if(like.isPresent()) {
				myquizRepository.delete(like.get());
			}
		} else {
			Quiz quiz = quizRepository.findById(id).get();
			User user = userRepository.findById(myquizRequest.getUser_id()).get();
			MyQuiz myquiz = new MyQuiz();
			myquiz.setQuiz(quiz);
			myquiz.setUser(user);
			myquizRepository.save(myquiz);
		}
	}

}
