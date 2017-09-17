package com.hegde.survey.api.manager.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by girish hegde on 9/17/17.
 */
public class QuestionResponseRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ResponseItem response = new ResponseItem();
        response.setQuestionId(Integer.toString(resultSet.getInt("question_id")));
        response.setQuestionDesc(resultSet.getString("question"));
        response.setResponse(resultSet.getString("response"));
        return response;
    }
}
