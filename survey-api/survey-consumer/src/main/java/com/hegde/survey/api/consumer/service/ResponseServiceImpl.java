package com.hegde.survey.api.consumer.service;

import com.hegde.survey.api.consumer.dao.SurveyDao;
import com.hegde.survey.api.consumer.exception.NoDataException;
import com.hegde.survey.api.consumer.model.Question;
import com.hegde.survey.api.consumer.model.SurveyDetails;
import com.hegde.survey.api.consumer.model.SurveyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by girish hegde on 9/16/17.
 */
@Service
public class ResponseServiceImpl implements ResponseService {
    @Autowired
    private SurveyDao surveyDao;

    @Override
    public SurveyDetails getQuestions(int surveyId) {
        SurveyDetails surveyDetails = buildSurveyDetails(surveyDao.getQuestions(surveyId), surveyId);
        return surveyDetails;
    }

    private SurveyDetails buildSurveyDetails(List<Question> questions, int surveyId) {
        if(questions == null || questions.isEmpty()){
            throw new NoDataException("No data found for " + surveyId);
        }
        return new SurveyDetails(Integer.toString(surveyId), questions);
    }

    @Override
    public void submit(List<SurveyResponse> responses, String surveyId) throws SQLException {
        surveyDao.insertAnswers(responses);
    }
}
