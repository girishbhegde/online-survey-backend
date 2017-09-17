package com.hegde.survey.api.manager.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hegde.survey.api.manager.exception.MissingDataException;
import com.hegde.survey.api.manager.model.*;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by girish hegde on 9/16/17.
 */
@Service
public class SurveyManagerDaoImpl implements SurveyManagerDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public List<Question> getQuestions(String surveyId) {
        String sql = "SELECT question_id, question, options FROM survey_details where survey_id = (?)";
        List<Question> questions = (List<Question>) getJdbcTemplate().query(sql, new Object[]{Integer.parseInt(surveyId)}, new QuestionsRowMapper());
        return questions;
    }

    @Override
    public void addQuestions(String surveyId, List<Question> questions) {
        String sql = "INSERT INTO survey_details (survey_id, question, options) VALUES (? ,?, ?::JSON)";
        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i)
                    throws SQLException {
                try {
                    Question question = questions.get(i);
                    ps.setInt(1, Integer.parseInt(surveyId));
                    ps.setString(2, question.getQuestionDesc());
                    String json = new ObjectMapper().writeValueAsString(question.getOptions());
                    ps.setObject(3, json);
                }catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public int getBatchSize() {
                return questions.size();
            }
        });
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void deleteQuestion(String questionId) {
        String sql1 = "DELETE FROM survey_response WHERE question_id = (?)";
        getJdbcTemplate().update(sql1, new Object[]{Integer.parseInt(questionId)});

        String sql2 = "DELETE FROM survey_details WHERE question_id = (?)";
        getJdbcTemplate().update(sql2, new Object[]{Integer.parseInt(questionId)});
    }

    @Override
    public void updateQuestion(String questionId, Question question) throws SQLException {
        PGobject jsonObject = new PGobject();
        try {
            String json = new ObjectMapper().writeValueAsString(question.getOptions());
            jsonObject.setType("json");
            jsonObject.setValue(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String sql = "UPDATE survey_details SET question_id = (?), question = (?), options = (?) WHERE question_id = (?)";
        getJdbcTemplate().update(sql, new Object[]{Integer.parseInt(questionId), question.getQuestionDesc(), jsonObject, Integer.parseInt(questionId)});
    }

    @Override
    public QuestionResponse getResponses(String questionId) {
        String sql1 = "SELECT options from survey_details WHERE question_id=(?)";
        List<Map<String, String>> optionsMapArray = (List<Map<String, String>>) getJdbcTemplate().query(sql1, new Object[]{Integer.parseInt(questionId)}, new OptionsRowMapper());

        if(optionsMapArray == null || optionsMapArray.size()!= 1){
            throw new RuntimeException("A question should not have more than one option json. Question " + questionId + " has " + optionsMapArray.size());
        }

        Map<String, String> optionsMap = optionsMapArray.get(0);

        String sql2 = "SELECT survey_response.question_id, survey_details.question, survey_response.response from " +
                "survey_response INNER JOIN survey_details ON survey_response.question_id=survey_details.question_id " +
                "WHERE survey_response.question_id=(?)";
        List<ResponseItem> response = getJdbcTemplate().query(sql2, new Object[]{Integer.parseInt(questionId)}, new QuestionResponseRowMapper());

        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setQuestionId(questionId);

        if(response == null || response.isEmpty()){
            throw new MissingDataException("No data found");
        }

        questionResponse.setQuestionDesc(response.get(0).getQuestionDesc());
        List<String> responseList = new ArrayList<>();
        response.forEach(responseItem -> responseList.add(optionsMap.get(responseItem.getResponse())));
        questionResponse.setResponses(responseList);
        return questionResponse;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public int createSurvey(List<Question> questions, String userId) {
        //insert to surveys table
        String sql = "INSERT INTO surveys (username) VALUES (?) RETURNING survey_id";
        Integer surveyId = getJdbcTemplate().queryForObject(sql,
                                        new Object[]{userId},
                                        (resultSet, i) -> resultSet.getInt("survey_id"));

        //insert questions to survey_details table
        addQuestions(Integer.toString(surveyId), questions);
        return surveyId;
    }
}
