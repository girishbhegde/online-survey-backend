package com.hegde.survey.api.manager.service;

import com.hegde.survey.api.manager.dao.SurveyManagerDao;
import com.hegde.survey.api.manager.model.Question;
import com.hegde.survey.api.manager.model.QuestionResponse;
import com.hegde.survey.api.manager.model.ResponseChart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by girish hegde on 9/16/17.
 */
@Service
public class SurveyManagerServiceImpl implements SurveyManagerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SurveyManagerServiceImpl.class);

    @Autowired
    private SurveyManagerDao surveyManagerDao;


    @Override
    public List<Question> getQuestions(String surveyId) {
        return surveyManagerDao.getQuestions(surveyId);
    }

    @Override
    public void addQuestions(String surveyId, List<Question> questions) {
        surveyManagerDao.addQuestions(surveyId, questions);
    }

    @Override
    public void deleteQuestion(String questionId) {
        surveyManagerDao.deleteQuestion(questionId);
    }

    @Override
    public void updateQuestion(String questionId, Question question) throws SQLException {
        surveyManagerDao.updateQuestion(questionId, question);
    }

    @Override
    public QuestionResponse getResponses(String questionId) {
        return surveyManagerDao.getResponses(questionId);
    }

    @Override
    public ResponseChart getResponseChart(String questionId) {
        QuestionResponse questionResp = surveyManagerDao.getResponses(questionId);
        ResponseChart responseChart = new ResponseChart();
        Map<String, Float> responseMap = new HashMap<>();
        responseChart.setQuestionId(questionResp.getQuestionId());
        responseChart.setQuestion(questionResp.getQuestionDesc());
        responseChart.setResponseCount(questionResp.getResponses().size());
        for(String value : questionResp.getResponses()){
            responseMap.put(value, responseMap.get(value)!=null?responseMap.get(value)+1:1);
        }
        responseMap.forEach((key,value)-> responseMap.put(key,(getPercentageValue(value, questionResp.getResponses().size()))));
        responseChart.setDistribution(responseMap);
        return responseChart;
    }

    private float getPercentageValue(Float value, int total){
        DecimalFormat df = new DecimalFormat("##.##");
        float fraction =  (value / total);
        return Float.parseFloat(df.format(fraction*100));
    }

    @Override
    public int createSurvey(List<Question> questions, String userId) {
        return surveyManagerDao.createSurvey(questions, userId);
    }
}
