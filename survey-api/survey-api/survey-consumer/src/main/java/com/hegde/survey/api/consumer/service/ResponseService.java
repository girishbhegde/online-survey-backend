package com.hegde.survey.api.consumer.service;

import com.hegde.survey.api.consumer.model.Question;
import com.hegde.survey.api.consumer.model.SurveyDetails;
import com.hegde.survey.api.consumer.model.SurveyResponse;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by girish hegde on 9/16/17.
 */
public interface ResponseService {

    /**
     * Get a list of questions for a surveyId
     * @param surveyId
     * @return
     */
    SurveyDetails getQuestions(int surveyId);

    /**
     * Respond to a survey based on a surveyId
     * @param responses
     * @param surveyId
     */
    void submit(List<SurveyResponse> responses, String surveyId) throws SQLException;
}
