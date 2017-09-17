package com.hegde.survey.api.consumer.dao;

import com.hegde.survey.api.consumer.model.Question;
import com.hegde.survey.api.consumer.model.QuestionsRowMapper;
import com.hegde.survey.api.consumer.model.SurveyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by girish hegde on 9/16/17.
 */
@Service
public class SurveyDaoImpl implements SurveyDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public List<Question> getQuestions(int surveyId) {
        String sql = "SELECT question_id, question, options FROM survey_details where survey_id = (?)";
        List<Question> questions = (List<Question>) getJdbcTemplate().query(sql, new Object[]{surveyId}, new QuestionsRowMapper());
        return questions;
    }

    @Override
    public void insertAnswers(List<SurveyResponse> responses) throws SQLException {
        String sql = "INSERT INTO survey_response (question_id, response) VALUES (?,?)";

        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i)
                    throws SQLException {
                SurveyResponse surveyResponse = responses.get(i);
                ps.setInt(1, Integer.parseInt(surveyResponse.getQuestionId()));
                ps.setString(2, surveyResponse.getAnswerOption());
            }
            @Override
            public int getBatchSize() {
                return responses.size();
            }
        });
    }
}
