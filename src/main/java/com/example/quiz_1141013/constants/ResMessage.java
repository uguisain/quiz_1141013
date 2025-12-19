package com.example.quiz_1141013.constants;

public enum ResMessage {

	SUCCESS(200, "Success!"), //
	DATE_ERROR(400, "Date error!"), //
	TYPE_ERROR(400, "Type error!"), //
	OPTIONS_SIZE_ERROR(400, "Option size error!"), //
	QUIZ_ID_ERROR(400, "Quiz_id error!"), //
	QUIZ_NOT_FOUND(404, "Quiz not found!"), //
	PLEASE_LOGIN_FRIST(400, "請先登入!") //
	;

	private int code;

	private String message;

	private ResMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
