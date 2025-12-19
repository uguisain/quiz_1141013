package com.example.quiz_1141013.response;

import java.util.List;

import com.example.quiz_1141013.entity.Question;

public class GetQuestionRes extends BasicRes {

	private List<Question> questionList;

	public GetQuestionRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetQuestionRes(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public GetQuestionRes(int code, String message, List<Question> questionList) {
		super(code, message);
		this.questionList = questionList;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

}
