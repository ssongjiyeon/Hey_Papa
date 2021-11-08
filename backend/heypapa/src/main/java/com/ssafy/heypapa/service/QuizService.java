package com.ssafy.heypapa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
import com.ssafy.heypapa.response.QuizResponse;

@Service("QuizService")
public class QuizService implements IQuizService {

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
	public QuizResponse getoneQuiz(Long id) {
		Quiz quiz = quizRepository.findById(id).get();
		QuizResponse q = new QuizResponse();
		q.setId(quiz.getId());
		q.setQuestion(quiz.getQuestion());
		q.setAnswer(quiz.getAnswer());
		q.setAnswer2(quiz.getAnswer2());
		q.setCandidate(quiz.getCandidate());
		q.setDescription(quiz.getDescription());
		q.setType(quiz.getType());
		
		List<Comment> clist = commentRepository.findByQuiz_id(quiz.getId()).get();
		if(clist != null) {
			List<CommentRequest> comments = new ArrayList<>();
			for(Comment c : clist) {
				CommentRequest cr = new CommentRequest();
				cr.setNickname(c.getUser().getNickname());
				cr.setContent(c.getContent());
				comments.add(cr);
			}
			q.setComments(comments);
		}
		return q;
	}

//	@Override
//	public List<QuizRequest> getAllQuiz(Pageable pageable) {
//		List<Quiz> qlist = quizRepository.findAll(pageable).getContent();
//		List<QuizRequest> copy = new ArrayList<>();
//		QuizRequest qreq;
////		String quiztype = quizRepository.findByType(type);
//		for(Quiz q : qlist) {
//			qreq = new QuizRequest();
//			if(q.getType().name().equals("아내")) {
//				qreq.setId(q.getId());
//				qreq.setQuestion(q.getQuestion());
//				qreq.setCandidate(q.getCandidate());
//				copy.add(qreq);
//			}
//		}
//		for(Quiz q : qlist) {
//			qreq = new QuizRequest();
//			qreq.setType(quiztype);
//			qreq.setId(q.getId());
//			qreq.setQuestion(q.getQuestion());
//			qreq.setCandidate(q.getCandidate());
//			copy.add(qreq);
//		}
//		for(Quiz q : qlist) {
//			qreq = new QuizRequest();
//			qreq.setType(quiztype);
//			qreq.setId(q.getId());
//			qreq.setQuestion(q.getQuestion());
//			qreq.setCandidate(q.getCandidate());
//			copy.add(qreq);
//		}
//		for(Quiz q : qlist) {
//			qreq = new QuizRequest();
//			qreq.setType(quiztype);
//			qreq.setId(q.getId());
//			qreq.setQuestion(q.getQuestion());
//			qreq.setCandidate(q.getCandidate());
//			copy.add(qreq);
//		}
//		return copy;
//	}

	@Override
	public Comment createComment(Long id, CommentRequest comment) {
		Comment com = new Comment();
		com.setUser(userRepository.findByNickname(comment.getNickname()).get());
		Quiz quiz = quizRepository.getOne(id);
		com.setQuiz(quiz);
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
				qres.setCandidate(q.getCandidate());
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
				qres.setCandidate(q.getCandidate());
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
				qres.setCandidate(q.getCandidate());
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
				qres.setCandidate(q.getCandidate());
				copy.add(qres);
			} 
		}
		return copy;
	}

	@Override
	public void myQuiz(MyQuizRequest myquizRequest, Long id) {
		Quiz quiz = quizRepository.findById(id).get();
		User user = userRepository.findById(myquizRequest.getUser_id()).get();
		MyQuiz myquiz = new MyQuiz();
		myquiz.setQuizcheck(myquizRequest.getQuizcheck());
		myquiz.setQuizlike(myquizRequest.getQuizlike());
		myquiz.setQuiz(quiz);
		myquiz.setUser(user);
		myquizRepository.save(myquiz);
	}

//	public Optional<Quiz> getQuizImg(Long id) {
//		return quizRepository.findByIdAndImg(id);
//	}
}
