package com.example.quiz_1141013.request;

import java.util.List;

import com.example.quiz_1141013.constants.ValiMsg;
import com.example.quiz_1141013.vo.AnswerVo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class FillinReq {

	@NotBlank(message = ValiMsg.USER_NAME_IS_EMPTY)
	private String name;
	
	private String phone;
	
	@NotBlank(message = ValiMsg.EMAIL_IS_EMPTY)
	private String email;
	
	@Min(value = 18, message = ValiMsg.AGE_IS_ERROR)
	private int age;

	@Min(value = 1, message = ValiMsg.QUIZ_ID_ERROR)
	private int quizId;
	
	@Min(value = 1, message = ValiMsg.QUESTION_ID_ERROR)
	private int questionId;
	
	@NotEmpty(message = ValiMsg.ANSWERVO_IS_EMPTY)
	private List<AnswerVo> answerVoList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public List<AnswerVo> getAnswerVoList() {
		return answerVoList;
	}

	public void setAnswerVoList(List<AnswerVo> answerVoList) {
		this.answerVoList = answerVoList;
	}

}
