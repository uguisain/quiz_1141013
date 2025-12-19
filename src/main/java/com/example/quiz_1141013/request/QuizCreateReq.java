package com.example.quiz_1141013.request;

import java.time.LocalDate;
import java.util.List;

import com.example.quiz_1141013.constants.ValiMsg;
import com.example.quiz_1141013.vo.QuestionVo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class QuizCreateReq {

	// 問卷
	@NotBlank(message = ValiMsg.TITLE_ERROR)
	private String title;
	
	@NotBlank(message = ValiMsg.DESCRIPTION_ERROR)
	private String description;
	
	@NotNull(message = ValiMsg.START_DATE_ERROR)
	private LocalDate startDate;
	
	@NotNull(message = ValiMsg.END_DATE_ERROR)
	private LocalDate endDate;
	
	private boolean published;
	
	// 問題
	@Valid
	private List<QuestionVo> questionVoList;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public List<QuestionVo> getQuestionVoList() {
		return questionVoList;
	}

	public void setQuestionVoList(List<QuestionVo> questionVoList) {
		this.questionVoList = questionVoList;
	}

}
