package com.hegde.survey.api.manager.dao;

import com.hegde.survey.api.manager.model.Question;
import com.hegde.survey.api.manager.model.QuestionResponse;
import com.hegde.survey.api.manager.model.ResponseItem;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by girish hegde on 9/16/17.
 */
public interface SurveyManagerDao {
    /**
     * Fetch list of questions from DB
     * @param surveyId
     * @return
     */
    List<Question> getQuestions(String surveyId);

    /**
     * Add questions to a survey in DB
     * @param surveyId
     */
    void addQuestions(String surveyId, List<Question> questions);

    /**
     * Delete a questionId
     * @param questionId
     */
    void deleteQuestion(String questionId);

    /**
     * update a question
     * @param questionId
     * @param question
     */
    void updateQuestion(String questionId, Question question) throws SQLException;

    /**
     * get responses for a question
     * @param questionId
     * @return
     */
    QuestionResponse getResponses(String questionId);

    /**
     * Creates a new survey with questions
     * @param questions
     * @param userId
     * @return
     */
    int createSurvey(List<Question> questions, String userId);

}
