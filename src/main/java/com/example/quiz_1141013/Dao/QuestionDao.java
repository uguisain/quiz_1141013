package com.example.quiz_1141013.Dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.quiz_1141013.entity.Question;
import com.example.quiz_1141013.entity.QuestionId;
import com.example.quiz_1141013.entity.Quiz;
import com.example.quiz_1141013.request.QuizIdReq;

import jakarta.transaction.Transactional;

@Repository
public interface QuestionDao extends JpaRepository<Question, QuestionId> {

	// 新增問題
	@Modifying
	@Transactional
	@Query(value = "insert into question (quiz_id, question_id, question, type, " //
			+ " required, options) values(?1,?2,?3,?4,?5,?6)", nativeQuery = true)
	public void addQuestion(int quizId, int questionId, String question, String type, //
			boolean required, String options);

	// 用Id抓問題
	@Query(value = "select * from question where quiz_id = ?", nativeQuery = true)
	public List<Question> getQuestion(int id);
	
	// 課堂版本(用Id抓問題)
	@Query(value = "select * from question where quiz_id = ?", nativeQuery = true)
	public List<Question> getByQuizId(int id);
	
	// 刪除問題(更新用)
	/** 刪除相同quizId的所有問題 */
	@Modifying
	@Transactional
	@Query(value = "delete from question where quiz_id = ?", nativeQuery = true)
	public void deleteByQuizId(int id);
	
	
	
	

}
