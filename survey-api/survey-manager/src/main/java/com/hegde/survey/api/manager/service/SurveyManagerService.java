package com.hegde.survey.api.manager.service;

import com.hegde.survey.api.manager.model.Question;
import com.hegde.survey.api.manager.model.QuestionResponse;
import com.hegde.survey.api.manager.model.ResponseItem;
import com.hegde.survey.api.manager.model.ResponseChart;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by girish hegde on 9/16/17.
 */
public interface SurveyManagerService {
    /**
     * Get the list of all questions for a survey
     * @param surveyId
     * @return
     */
    List<Question> getQuestions(String surveyId);

    /**
     * Add questions to a survey
     * @param surveyId
     * @param questions
     */
    void addQuestions(String surveyId, List<Question> questions);

    /**
     * Delete a question in a survey
     * @param questionId
     */
    void deleteQuestion(String questionId);

    /**
     * Update a question in a survey
     * @param questionId
     * @param question
     */
    void updateQuestion(String questionId, Question question) throws SQLException;


    /**
     * Get the list of all responses to a question
     * @param questionId
     * @return
     */
    QuestionResponse getResponses(String questionId);

    /**
     * Get the number of responses and distribution in percentage
     * @param questionId
     * @return
     */
    ResponseChart getResponseChart(String questionId);

    /**
     * Create a survey with list of questions
     * @param questions
     * @param userId
     * @return surveyId
     */
    int createSurvey(List<Question> questions, String userId);

    //void deleteSurvey(String surveyId);
}
