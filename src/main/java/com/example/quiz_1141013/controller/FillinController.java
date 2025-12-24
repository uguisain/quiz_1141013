package com.example.quiz_1141013.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_1141013.request.FillinReq;
import com.example.quiz_1141013.response.BasicRes;
import com.example.quiz_1141013.service.FillinService;

import jakarta.validation.Valid;

@RestController
public class FillinController {

	@Autowired
	private FillinService fillinService;

	// 寫入答案
	@PostMapping("quiz/fillin")
	public BasicRes fillin(@Valid @RequestBody FillinReq req) throws Exception {
		return fillinService.fillin(req);
	}

	// 取得填寫過的quizId
	@GetMapping("quiz/get_answered")
	public BasicRes getAnswered(@RequestParam("email") String email) {
		return fillinService.getAnswered(email);
	}
}