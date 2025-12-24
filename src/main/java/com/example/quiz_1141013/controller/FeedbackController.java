package com.example.quiz_1141013.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_1141013.response.FeedbackRes;
import com.example.quiz_1141013.response.StatisticsRes;
import com.example.quiz_1141013.service.FeedbackService;

@CrossOrigin
@RestController
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	// 取得表單回饋 TODO
	@GetMapping("quiz/get_feedback/{quizId}")
	public FeedbackRes feedback(@PathVariable("quizId") int quizId) throws Exception {
		return feedbackService.feedback(quizId);
	}

	// 取得表單統計結果
	@GetMapping("quiz/get_statistics/{id}")
	public StatisticsRes statistics(@PathVariable("id") int id) throws Exception {
		return feedbackService.statistics(id);
	}

}
