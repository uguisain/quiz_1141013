package com.example.quiz_1141013.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.quiz_1141013.Dao.QuestionDao;
import com.example.quiz_1141013.Dao.QuizDao;
import com.example.quiz_1141013.constants.ResMessage;
import com.example.quiz_1141013.constants.Type;
import com.example.quiz_1141013.entity.Question;
import com.example.quiz_1141013.request.QuizIdReq;
import com.example.quiz_1141013.request.QuizUpdateReq;
import com.example.quiz_1141013.request.QuizCreateReq;
import com.example.quiz_1141013.response.BasicRes;
import com.example.quiz_1141013.response.GetListRes;
import com.example.quiz_1141013.response.GetQuestionRes;
import com.example.quiz_1141013.response.GetQuestionRes2;
import com.example.quiz_1141013.vo.Options;
import com.example.quiz_1141013.vo.QuestionVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class QuizService {

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private QuizDao quizDao;

	@Autowired
	private QuestionDao questionDao;

	/* rollbackFor = Exception.class: 表示只要此方法發生了 Exception，寫一半的資料都會回溯 */
	@Transactional(rollbackFor = Exception.class)
	public BasicRes create(QuizCreateReq req) throws Exception {
		BasicRes checkRes = check(req);
		// 方法ckeck只會有2種結果，null和非null(BasicRes)，非null結果表示有錯
		if (checkRes != null) {
			// 把檢查有錯的結果直接return出去
			return checkRes;
		}

		/* 新增問卷 */
		quizDao.addQuiz(req.getTitle(), req.getDescription(), req.getStartDate(), //
				req.getEndDate(), req.isPublished());
		/* 取得最新的 quiz_id 編號 */
		int quizId = quizDao.getMaxId();
		/* 將 Question 寫進DB */
		for (QuestionVo vo : req.getQuestionVoList()) {
			/* 要把 VO 中的List<Options>轉換成字串 */
			try {
				String optionListStr = mapper.writeValueAsString(vo.getOptionsList());
				questionDao.addQuestion(quizId, vo.getQuestionId(), vo.getQuestion(), //
						vo.getType(), vo.isRequired(), optionListStr);
			} catch (Exception e) {
				throw e;
			}
		}

		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	public GetListRes getAll() {
		return new GetListRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(), //
				quizDao.getAll());
	}

	public GetQuestionRes getQuestion(int id) {
		return new GetQuestionRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(), //
				questionDao.getQuestion(id));
	}

	public GetListRes getQuiz(int id) {
		return new GetListRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(), //
				quizDao.getQuiz(id));
	}

	// 課程版本的ID取得問題
	public GetQuestionRes2 getQuestionById(int quizId) throws Exception {
		List<Question> list = questionDao.getByQuizId(quizId);
		List<QuestionVo> questionVoList = new ArrayList<>();
		// 把 Question 中的每個字串 opsions 轉換成自定義物件 Opsions
		for (Question item : list) {
			try {
				List<Options> opList = mapper.readValue(item.getOptions(), new TypeReference<>() {
				});
				// 把 Question 中每個屬性值以及 opList，set到 QuestionVo 對應的屬性位置
				QuestionVo vo = new QuestionVo(quizId, item.getQuestionId(), item.getQuestion(), //
						item.getType(), item.isRequired(), opList);
				// 把每個 vo 加到 questionVoList
				questionVoList.add(vo);
			} catch (Exception e) {
				throw e;
			}
		}
		return new GetQuestionRes2(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(), //
				questionVoList);
	}

	private BasicRes check(QuizCreateReq req) {

		/* 排除開始時間比結束時間晚 或 開始時間比當前早 */
		if (req.getStartDate().isAfter(req.getEndDate()) //
				|| req.getStartDate().isBefore(LocalDate.now())) {
			return new BasicRes(ResMessage.DATE_ERROR.getCode(), ResMessage.DATE_ERROR.getMessage());
		}

		List<QuestionVo> voList = req.getQuestionVoList();
		for (QuestionVo vo : voList) {
			/* 排除非指定3種的type */
			if (!Type.checkType(vo.getType())) { // Type那邊判斷如果是false的情況
				return new BasicRes(ResMessage.TYPE_ERROR.getCode(), //
						ResMessage.TYPE_ERROR.getMessage());
			}
			/* type是選擇題時，選項至少要有1個 */
//			if (Type.isChosenType(vo.getType())) {
//				if (vo.getOptionsList().size() < 1) {
//					return new BasicRes(ResMessage.OPTIONS_SIZE_ERROR.getCode(), //
//							ResMessage.OPTIONS_SIZE_ERROR.getMessage());
//				}
//			} else { // type 是簡答題，不能有選項
//				if (!vo.getOptionsList().isEmpty()) {
//					return new BasicRes(ResMessage.OPTIONS_SIZE_ERROR.getCode(), //
//							ResMessage.OPTIONS_SIZE_ERROR.getMessage());
//				}
//			}
		}
		return null;
	}

	// 更新資料
	/* rollbackFor = Exception.class: 表示只要此方法發生了 Exception，寫一半的資料都會回溯 */
	@Transactional(rollbackFor = Exception.class)
	public BasicRes update(QuizUpdateReq req) throws Exception {
		/*
		 * 方法 check 中的參數資料型態是 QuizCreateReq，對 QuizUpdateReq 來說是父類別， 若把子類別 QuizUpdateReq
		 * 當參數放到 check 中，其資料型態會自動轉型成父類別 QuizCreateReq， 即
		 * check((QuizCreateReq)req)，這樣的結果查別只是在於子類別中的屬性 quizId 都會是預設值 0， 但不影響方法 check
		 * 的檢查，因為沒用到 quizId
		 */
		BasicRes checkRes = check(req);
		// 方法ckeck只會有2種結果，null和非null(BasicRes)，非null結果表示有錯
		if (checkRes != null) {
			// 把檢查有錯的結果直接return出去
			return checkRes;
		}
		/* 檢查quiz_id和QuestionVo中的quiz_id是否一樣 */
		for (QuestionVo vo : req.getQuestionVoList()) {
			if (vo.getQuizId() != req.getId()) {
				return new BasicRes(ResMessage.QUIZ_ID_ERROR.getCode(), //
						ResMessage.QUIZ_ID_ERROR.getMessage());
			}
		}

		// 更新quiz
		int updateRes = quizDao.update(req.getId(), req.getTitle(), req.getDescription(), //
				req.getStartDate(), req.getEndDate(), req.isPublished());
		/* 有找到quizID並更新成功，即使更新的資料與DB中的一樣，都會回傳1(where條件帶的是PK) */
		if (updateRes != 1) {
			return new BasicRes(ResMessage.QUIZ_NOT_FOUND.getCode(), ResMessage.QUIZ_NOT_FOUND.getMessage());
		}

		/* 確定quiz存在，就先刪問題、再新增問題 */
		questionDao.deleteByQuizId(req.getId());
		for (QuestionVo vo : req.getQuestionVoList()) {
			/* 要把 VO 中的List<Options>轉換成字串 */
			try {
				String optionListStr = mapper.writeValueAsString(vo.getOptionsList());
				questionDao.addQuestion(vo.getQuizId(), vo.getQuestionId(), vo.getQuestion(), //
						vo.getType(), vo.isRequired(), optionListStr);
			} catch (Exception e) {
				throw e;
			}
		}

		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	public GetListRes getAll(String keyWord, LocalDate StartDate, LocalDate endDate) {
		if (!StringUtils.hasText(keyWord)) {
			// 把 keyword 是 null(沒有輸入值) 或 空字串 或全空白字串 轉換成空字串
			// 目的是後面在取資料時會使用 like %%，%% 中間是空字串時，也是會撈全部
			keyWord = "";
		}
		if (StartDate == null) {
			StartDate = LocalDate.of(2020, 1, 1);
		}
		if (endDate == null) {
			endDate = LocalDate.of(3000, 1, 1);
		}
		return new GetListRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(), //
				quizDao.getAll(keyWord, StartDate, endDate));
	}

}
