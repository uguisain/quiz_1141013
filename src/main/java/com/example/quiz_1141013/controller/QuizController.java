package com.example.quiz_1141013.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_1141013.request.QuizIdReq;
import com.example.quiz_1141013.request.QuizUpdateReq;
import com.example.quiz_1141013.request.QuizCreateReq;
import com.example.quiz_1141013.response.BasicRes;
import com.example.quiz_1141013.response.GetListRes;
import com.example.quiz_1141013.response.GetQuestionRes;
import com.example.quiz_1141013.response.GetQuestionRes2;
import com.example.quiz_1141013.service.QuizService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
public class QuizController {

	@Autowired
	private QuizService quizService;

	@PostMapping("quiz/create")
	public BasicRes create(@Valid @RequestBody QuizCreateReq req) throws Exception {
		return quizService.create(req);
	}

	@PostMapping("quiz/update")
	public BasicRes update(@Valid @RequestBody QuizUpdateReq req) throws Exception {
		return quizService.update(req);
	}

	// 首頁清單
	@GetMapping("quiz/get_all")
	public GetListRes getAll() {
		return quizService.getAll();
	}

	// 首頁清單(課堂版)
	@GetMapping("quiz/get_fillter_data")
	public GetListRes getAll(@RequestParam("keyWord") String keyWord, //
			@RequestParam("StartDate") LocalDate StartDate, //
			@RequestParam("endDate") LocalDate endDate) {
		return quizService.getAll(keyWord, StartDate, endDate);
	}

	// 表單用問卷資訊
	@GetMapping("quiz/get_quiz/{id}")
	public GetListRes getQuiz(@PathVariable("id") int id) {
		return quizService.getQuiz(id);
	}

	// 表單用問題
	@GetMapping("quiz/get_question/{id}")
	public GetQuestionRes getQuestion(@PathVariable("id") int id) {
		return quizService.getQuestion(id);
	}

	// 課堂(quiz/get_question2?quizId=1)
	@GetMapping("quiz/get_question2")
	public GetQuestionRes2 getQuestionById(@RequestParam("quizId") int quizId) throws Exception {
		return quizService.getQuestionById(quizId);
	}

}
