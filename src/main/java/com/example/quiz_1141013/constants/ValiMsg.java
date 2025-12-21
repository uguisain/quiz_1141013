package com.example.quiz_1141013.constants;

public class ValiMsg {

	// 加上 final 是因為在 @Validation 中的 message 限制
	// 加上 static 是為了方便透過 ValiMsg. 來呼叫此常數變數
	public static final String TITLE_ERROR = "Title error!";

	public static final String DESCRIPTION_ERROR = "Description error!";

	public static final String START_DATE_ERROR = "Start Date error!";

	public static final String END_DATE_ERROR = "End Date error!";
	
	public static final String QUESTION_ERROR = "Question error!";
	
	public static final String TYPE_ERROR = "Type error!";
	
	public static final String QUIZ_ID_ERROR = "Quiz id error!!!!!!!!!";
	public static final String QUESTION_ID_ERROR = "Question id error!";
	public static final String ANSWERVO_IS_EMPTY = "AnswerVo is empty!";
	
	public static final String USER_NAME_IS_EMPTY = "User name is empty!";
	public static final String EMAIL_IS_EMPTY = "Email is empty!";
	public static final String AGE_IS_ERROR = "User age error!";

}
