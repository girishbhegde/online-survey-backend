package com.hegde.survey.api.consumer.service;

import com.hegde.survey.api.consumer.dao.SurveyDao;
import com.hegde.survey.api.consumer.model.Question;
import com.hegde.survey.api.consumer.model.SurveyDetails;
import com.hegde.survey.api.consumer.model.SurveyResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by girish hegde on 19-09-2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ResponseServiceTest {
    @Configuration
    static class ResponseServiceTestConfig{
        @Bean
        public SurveyDao getSurveyDao(){
            return Mockito.mock(SurveyDao.class);
        }

        @Bean
        public ResponseService getResponseService(){
            return new ResponseServiceImpl();
        }
    }

    @Autowired
    private ResponseService responseService;

    @Autowired
    private SurveyDao surveyDao;

    @Test
    public void getQuestions() {
        int surveyId = 1;
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        SurveyDetails surveyDetails = new SurveyDetails("1", questions);
        when(surveyDao.getQuestions(surveyId)).thenReturn(questions);
        SurveyDetails result = responseService.getQuestions(surveyId);
        assertEquals(surveyDetails.getSurveyId(), result.getSurveyId());
        assertEquals(surveyDetails.getQuestions().size(), result.getQuestions().size());
        verify(surveyDao).getQuestions(surveyId);
    }

    @Test
    public void submit() throws Exception {
        SurveyResponse surveyResponse = new SurveyResponse();
        List<SurveyResponse> responses = new ArrayList<>();
        responses.add(surveyResponse);
        String surveyId = "1";
        doNothing().when(surveyDao).insertAnswers(responses);
        responseService.submit(responses, surveyId);
        verify(surveyDao).insertAnswers(responses);
    }
}
