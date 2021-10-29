package com.ssafy.heypapa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.heypapa.entity.MyQuiz;
import com.ssafy.heypapa.entity.Quiz;
import com.ssafy.heypapa.repository.MyQuizRepository;
import com.ssafy.heypapa.repository.QuizRepository;
import com.ssafy.heypapa.repository.UserRepository;
import com.ssafy.heypapa.request.MyQuizRequest;

@Service("myquizService")
public class MyQuizService implements IMyQuizService {

	@Autowired
	MyQuizRepository myquizRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	QuizRepository quizRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	QuizService quizService;
	
	@Override
	public MyQuiz createMyQuiz(MyQuizRequest myquizRequest) {
		MyQuiz myquiz = new MyQuiz();
//		User user = userService.findByUserId(myquizRequest.getUser_id()); // 유저 서비스에서 해당 유저 객체 찾기
//		myquiz.setUser(user); // 나의 퀴즈에서 유저 셋팅
		Quiz quiz = quizService.findByQuizId(myquizRequest.getQuiz_id()); // 퀴즈 서비스에서 해당 퀴즈 객체 찾기
		myquiz.setQuiz(quiz); // 나의 퀴즈에서 퀴즈 셋팅
//		myquiz.setQuizcheck();
		myquiz.setQuizlike(true);
		myquiz = myquizRepository.save(myquiz);
		return myquiz;
	}

	@Override
	public void deleteMyQuiz(Long id) {
		myquizRepository.deleteById(id);		
	}

}
