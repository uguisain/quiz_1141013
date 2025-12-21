package com.example.quiz_1141013.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.quiz_1141013.Dao.FillinDao;
import com.example.quiz_1141013.Dao.QuestionDao;
import com.example.quiz_1141013.constants.ResMessage;
import com.example.quiz_1141013.entity.Question;
import com.example.quiz_1141013.request.FillinReq;
import com.example.quiz_1141013.response.BasicRes;
import com.example.quiz_1141013.vo.AnswerVo;
import com.example.quiz_1141013.vo.Options;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FillinService {

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private FillinDao fillinDao;

	@Autowired
	private QuestionDao questionDao;

	public BasicRes fillin(FillinReq req) throws Exception {
		// TODO 使用email取得user資料
		/* 使用quizId取得所有問題 */
		List<Question> questionList = questionDao.getByQuizId(req.getQuizId());
		// CollectionUtils.isEmpty 檢查List是否為null
		if (CollectionUtils.isEmpty(questionList)) {
			return new BasicRes(ResMessage.QWESTION_NOT_FOUND.getCode(), //
					ResMessage.QWESTION_NOT_FOUND.getMessage());
		}
		// Map<問題編號, 所有選項編號和選項>
		Map<Integer, List<Options>> questionMap = new HashMap<>();
		for (Question item : questionList) {
			/* 把字串 option 轉成物件 Options */
			try {
				List<Options> opList = mapper.readValue(item.getOptions(), new TypeReference<>() {
				});
				/* 把 問題編號 和 List<Options> 放入 Map */
				questionMap.put(item.getQuestionId(), opList);
			} catch (Exception e) {
				throw e;
			}
		}
		/* 比對選項看答案是否一樣 */
		List<AnswerVo> answerVoList = req.getAnswerVoList();
		for(AnswerVo vo: answerVoList) {
			
		}
		
		

		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

}
