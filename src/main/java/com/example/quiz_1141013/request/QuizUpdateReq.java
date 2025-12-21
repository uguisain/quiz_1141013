package com.example.quiz_1141013.request;

import com.example.quiz_1141013.constants.ValiMsg;

import jakarta.validation.constraints.Min;

public class QuizUpdateReq extends QuizCreateReq {

	// 因為是更新已存在的quiz，因此quizId至少是1
	@Min(value = 1, message = ValiMsg.QUIZ_ID_ERROR)
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
