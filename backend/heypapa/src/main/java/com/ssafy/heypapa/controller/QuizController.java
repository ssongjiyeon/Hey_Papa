package com.ssafy.heypapa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(value = "퀴즈 api", tags = { "Quiz" })
@RestController
@RequestMapping("/api/heypapa/quiz")
public class QuizController {

}
