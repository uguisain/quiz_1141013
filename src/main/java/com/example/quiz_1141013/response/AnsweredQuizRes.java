package com.example.quiz_1141013.response;

import java.util.List;

import jakarta.persistence.Column;

public class AnsweredQuizRes extends BasicRes {

	private List<Integer> quizIdList;

	public List<Integer> getQuizIdList() {
		return quizIdList;
	}

	public void setQuizIdList(List<Integer> quizIdList) {
		this.quizIdList = quizIdList;
	}

	public AnsweredQuizRes(int code, String message, List<Integer> quizIdList) {
		super(code, message);
		this.quizIdList = quizIdList;
	}

	public AnsweredQuizRes() {
		super();
	}

	public AnsweredQuizRes(int code, String message) {
		super(code, message);
	}

}
