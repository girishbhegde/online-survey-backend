package com.hegde.survey.api.manager.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by girish hegde on 9/17/17.
 */
public class QuestionsRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Question question = new Question();
        try {
            question.setQuestionId(Integer.toString(resultSet.getInt("question_id")));
            question.setQuestionDesc(resultSet.getString("question"));
            String optionJson = resultSet.getString("options");
            ObjectMapper objectMapper = new ObjectMapper();
            question.setOptions(objectMapper.readValue(optionJson, new TypeReference<Map<String,String>>(){}));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return question;
    }
}
