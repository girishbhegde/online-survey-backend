package com.hegde.survey.api.consumer.dao;

import com.hegde.survey.api.consumer.model.Question;
import com.hegde.survey.api.consumer.model.QuestionsRowMapper;
import com.hegde.survey.api.consumer.model.SurveyResponse;
import com.hegde.survey.api.consumer.service.ResponseService;
import com.hegde.survey.api.consumer.service.ResponseServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by girish hegde on 19-09-2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SurveyDaoTest {
    @Configuration
    static class SurveyDaoTestConfig{
        @Bean
        public SurveyDao getSurveyDao(){
            return new SurveyDaoImpl();
        }

        @Bean
        public JdbcTemplate getJdbcTemplate(){
            return mock(JdbcTemplate.class);
        }
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SurveyDao surveyDao;

    @Test
    public void getQuestions(){
        Question question = new Question();
        List<Object> questions = new ArrayList<>();
        questions.add(question);
        Mockito.when(jdbcTemplate.query(anyString(),any(Object[].class),any(RowMapper.class))).thenReturn(questions);
        List<Question> result = surveyDao.getQuestions(1);
        assertEquals(questions, result);
        verify(jdbcTemplate).query(anyString(),any(Object[].class),any(RowMapper.class));
    }

    @Test
    public void getInsertAnswers() throws Exception{
        List<SurveyResponse> inputList = new ArrayList<>();
        Mockito.when(jdbcTemplate.batchUpdate(anyString(),any(BatchPreparedStatementSetter.class))).thenReturn(new int[]{1});
        surveyDao.insertAnswers(inputList);
        verify(jdbcTemplate).query(anyString(),any(Object[].class),any(RowMapper.class));
    }
}
