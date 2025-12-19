package com.example.quiz_1141013.entity;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "quiz")
public class Quiz {

	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "start_date")
	private LocalDate startDate;
	
	@Column(name = "end_date")
	private LocalDate endDate;
	
	// 屬性變數名稱，即使資料型態是boolean，也不能用is開頭，會影響到getter方法的命名:
	// published的資料型態是boolean，所以getter的方法名預設是使用isPublished
	// 若是變數名稱是isPublished，正確的getter方法名稱應該是 isIsPublished
	// 但IDE自動產生的getter方法名稱卻也是isPublished
	// 影響的結果會是資料庫中該欄位的值無法被放到變數這個容器中，所以永遠就是預設值 false(0)
	@Column(name = "published")
	private boolean published;

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

}
