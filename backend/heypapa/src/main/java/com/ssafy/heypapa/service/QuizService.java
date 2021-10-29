package com.ssafy.heypapa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ssafy.heypapa.entity.Comment;
import com.ssafy.heypapa.entity.Quiz;
import com.ssafy.heypapa.repository.CommentRepository;
import com.ssafy.heypapa.repository.QuizRepository;
import com.ssafy.heypapa.repository.QuizRepositorySupport;
import com.ssafy.heypapa.repository.UserRepository;
import com.ssafy.heypapa.request.CommentRequest;
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
	
	@Override
	public QuizResponse getoneQuiz(Long id) {
		Quiz quiz = quizRepository.findById(id).get();
		QuizResponse q = new QuizResponse();
		q.setId(quiz.getId());
		q.setQuestion(quiz.getQuestion());
		q.setAnswer(quiz.getAnswer());
		q.setCandidate(quiz.getCandidate());
		q.setDescription(quiz.getDescription());
		q.setType(quiz.getType().name());
		
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

	@Override
	public List<QuizRequest> getAllQuiz(Pageable pageable) {
		List<Quiz> qlist = quizRepository.findAll(pageable).getContent();
		List<QuizRequest> copy = new ArrayList<>();
		QuizRequest qreq;
		for(Quiz q : qlist) {
			qreq = new QuizRequest();
			qreq.setId(q.getId());
			qreq.setQuestion(q.getQuestion());
			qreq.setType(q.getType().name());
			copy.add(qreq);
		}
		return copy;
	}

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

}
