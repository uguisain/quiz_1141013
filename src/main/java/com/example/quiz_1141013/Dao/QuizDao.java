package com.example.quiz_1141013.Dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.example.quiz_1141013.entity.Question;
import com.example.quiz_1141013.entity.Quiz;
import com.example.quiz_1141013.request.QuizIdReq;

import jakarta.transaction.Transactional;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {

	// 新增問卷
	@Modifying
	@Transactional
	@Query(value = "insert into quiz (title, description, start_date, end_date, published) "
			+ " values(?1,?2,?3,?4,?5)", nativeQuery = true)
	public void addQuiz(String title, String description, LocalDate startDate, //
			LocalDate endDate, boolean published);

	// 更新問卷
	@Modifying
	@Transactional
	@Query(value = "update quiz set title = ?2, description = ?3, start_date= ?4, " //
			+ " end_date= ?5, published = ?6 where id = ?1 ", nativeQuery = true)
	public int update(int quizId, String title, String description, LocalDate startDate, //
			LocalDate endDate, boolean published);

	// 搜尋最大Id (新增問卷用)
	@Query(value = "select max(id) from quiz", nativeQuery = true)
	public int getMaxId();

	// 搜尋全部 (列表用)
	@Query(value = "select * from quiz", nativeQuery = true)
	public List<Quiz> getAll();

	// 搜尋全部 (課堂版)
	@Query(value = "select * from quiz where title like %?1% and start_date >= ?2 and end_date <= ?3", nativeQuery = true)
	public List<Quiz> getAll(String keyWord, LocalDate StartDate, LocalDate endDate);

	// 用Id抓特定問卷資訊
	@Query(value = "select * from quiz where id = ?1", nativeQuery = true)
	public List<Quiz> getQuiz(int id);
	
	// 用Id刪除問卷
	@Modifying
	@Transactional
	@Query(value = "delete from quiz where id = ?1", nativeQuery = true)
	public void deleteById(int id);

}
