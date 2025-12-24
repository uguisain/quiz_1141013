package com.example.quiz_1141013.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.quiz_1141013.entity.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserDao extends JpaRepository<User, String> {

	// 取得用戶資料(用email)
	@Query(value = "select * from user where email = ?", nativeQuery = true)
	public User getUser(String email);

	// 取得用戶資料(用email) - 2
	@Query(value = "select * from user where email in (?)", nativeQuery = true)
	public List<User> getUsersIn(List<String> emailList);

	// 新增用戶資料
	@Modifying
	@Transactional
	@Query(value = "insert ignore into user values(?1,?2,?3,?4,?5)", nativeQuery = true)
	public void addUserInfo(String name, String password, String phone, String email, int age);

	// 更新所有資料 (case when)
	/** 1. name = case when ?2 is null then p.name else ?2 end 語句的意思是: 如果
	 * ?2(參數列表中第2個位置的值)是 null 的話，我就使用原本資料庫中 name 欄位的值(不更新)， 否則就把該欄位更新為?2的值 2. case
	 * when 相當於 if, 但後面的條件語句判斷是否為 null, 可以用 is null 或是 = null, 不能用 == null 3. 每個
	 * case when 的後面都要有 end 當結尾 */
	@Modifying
	@Transactional
	@Query(value = "update user as p set" //
			+ " name = case when ?1 is null then p.name else ?1 end," //
			+ " phone = case when ?2 is null then p.phone else ?2 end," //
			+ " age = case when ?3 < 18 then p.age else ?3 end where email = ?4", //
			nativeQuery = true)
	public void updataInfo(String name, String phone, int age, String email);

}
