package com.example.quiz_1141013.response;

import java.util.List;
import com.example.quiz_1141013.entity.Question;
import com.example.quiz_1141013.vo.QuestionVo;

public class GetQuestionRes2 extends BasicRes {

	private List<QuestionVo> questionVoList;

	public GetQuestionRes2() {
		super();
	}

	public GetQuestionRes2(int code, String message) {
		super(code, message);
	}

	public GetQuestionRes2(int code, String message, List<QuestionVo> questionVoList) {
		super(code, message);
		this.questionVoList = questionVoList;
	}

	public List<QuestionVo> getQuestionVoList() {
		return questionVoList;
	}

	public void setQuestionVoList(List<QuestionVo> questionVoList) {
		this.questionVoList = questionVoList;
	}

}
