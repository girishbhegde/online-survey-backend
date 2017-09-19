package com.hegde.survey.api.consumer.controller;

/**
 * Created by girish hegde on 19-09-2017.
 */

import com.hegde.survey.api.consumer.model.Question;
import com.hegde.survey.api.consumer.model.SurveyDetails;
import com.hegde.survey.api.consumer.model.SurveyResponse;
import com.hegde.survey.api.consumer.service.ResponseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AppControllerTest {
    @Configuration
    static class AppControllerTestConfig{
        @Bean
        public ResponseService getResponseService(){
            return Mockito.mock(ResponseService.class);
        }

        @Bean
        public AppController getAppController(){
            return new AppController();
        }
    }

    @Autowired
    private AppController appController;

    @Autowired
    ResponseService responseService;

    @Test
    public void testGetQuestions(){
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        SurveyDetails surveyDetails = new SurveyDetails("1", questions);
        when(responseService.getQuestions(1)).thenReturn(surveyDetails);
        SurveyDetails response = appController.getQuestions(1);
        assertEquals(surveyDetails.getSurveyId(), response.getSurveyId());
        assertEquals(surveyDetails.getQuestions().size(), response.getQuestions().size());
        verify(responseService).getQuestions(1);
    }

    @Test
    public void testSubmit() throws Exception{
        SurveyResponse surveyResponse = new SurveyResponse();
        SurveyResponse[] responses = {surveyResponse};
        doNothing().when(responseService).submit(Arrays.asList(responses), "1");
        appController.submit("1", responses, mock(HttpServletResponse.class));
        verify(responseService).submit(Arrays.asList(responses), "1");
    }
}
