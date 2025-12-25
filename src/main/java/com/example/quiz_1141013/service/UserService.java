package com.example.quiz_1141013.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.quiz_1141013.Dao.UserDao;
import com.example.quiz_1141013.constants.ResMessage;
import com.example.quiz_1141013.entity.User;
import com.example.quiz_1141013.request.LoginReq;
import com.example.quiz_1141013.response.BasicRes;
import com.example.quiz_1141013.response.LoginRes;
import com.example.quiz_1141013.response.UserUpdateRes;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	// 新增用戶
	public BasicRes addInfo(User infoList) {
		userDao.addUserInfo(infoList.getName(), infoList.getPassword(), infoList.getPhone(), //
				infoList.getEmail(), infoList.getAge());
		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	// 更新用戶資料
	public UserUpdateRes updateInfo(User info) {
		String name = info.getName();
		if (StringUtils.hasText(name)) {
			name = null;
		}
		String phone = info.getPhone();
		if (StringUtils.hasText(phone)) {
			phone = null;
		}
		int age = info.getAge();
		if (age < 18 || age > 120) {
			age = 0;
		}
		userDao.updataInfo(name, phone, age, info.getEmail());
		return new UserUpdateRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(), //
				info.getName(), info.getEmail(), info.getPhone(), info.getAge());
	}

	// 登入功能
	public LoginRes login(LoginReq req) {

		User user = userDao.getUser(req.getEmail());
		// 沒有找到該用戶
		if (user == null) {
			return new LoginRes(ResMessage.USER_NOT_FOUND.getCode(), ResMessage.USER_NOT_FOUND.getMessage());
		}
		// 密碼錯誤
		if (!user.getPassword().equals(req.getPassword())) {
			return new LoginRes(ResMessage.PASSWORD_ERROR.getCode(), ResMessage.PASSWORD_ERROR.getMessage());
		}

		return new LoginRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(), //
				user.getName(), user.getEmail(), user.getPhone(), user.getAge());
	}

}
