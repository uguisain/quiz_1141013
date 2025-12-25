package com.example.quiz_1141013.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_1141013.entity.User;
import com.example.quiz_1141013.request.LoginReq;
import com.example.quiz_1141013.response.BasicRes;
import com.example.quiz_1141013.response.LoginRes;
import com.example.quiz_1141013.response.UserUpdateRes;
import com.example.quiz_1141013.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	// 新增用戶資訊
	@PostMapping(value = "quiz/add_user")
	public BasicRes addInfo(@RequestBody User infoList) {
		return userService.addInfo(infoList);
	}

	// 更新用戶資訊
	@PostMapping(value = "quiz/update_user")
	public UserUpdateRes updateInfo(@RequestBody User info) {
		return userService.updateInfo(info);
	}

	// 登入
	@PostMapping(value = "quiz/login")
	public LoginRes login(@Valid @RequestBody LoginReq req) {
		return userService.login(req);
	}

}
