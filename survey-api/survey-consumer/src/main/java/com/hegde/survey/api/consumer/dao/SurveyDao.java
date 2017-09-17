package com.hegde.survey.api.consumer.dao;

import com.hegde.survey.api.consumer.model.Question;
import com.hegde.survey.api.consumer.model.SurveyResponse;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by girish hegde on 9/16/17.
 */
public interface SurveyDao {
    /**
     * Fetch list of questions from DB
     * @param surveyId
     * @return
     */
    List<Question> getQuestions(int surveyId);

    /**
     * Insert answers to a survey
     * @param responses
     */
    void insertAnswers(List<SurveyResponse> responses) throws SQLException;
}
