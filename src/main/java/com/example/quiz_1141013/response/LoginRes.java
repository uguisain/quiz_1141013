package com.example.quiz_1141013.response;

public class LoginRes extends BasicRes {

	// 這邊是登入成功時要送回的資料
	private String name;
	private String email;
	private String phone;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LoginRes() {
		super();
	}

	public LoginRes(int code, String message) {
		super(code, message);
	}

	public LoginRes(int code, String message, String name, String email, String phone, int age) {
		super(code, message);
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.age = age;
	}

}
