package com.hegde.survey.api.consumer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hegde.survey.api.consumer.model.Question;
import com.hegde.survey.api.consumer.model.SurveyDetails;
import com.hegde.survey.api.consumer.model.SurveyResponse;
import com.hegde.survey.api.consumer.service.ResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ghegde on 6/25/17.
 */
@RestController
public class AppController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private ResponseService service ;

    @RequestMapping(value = "/survey/questions", method = RequestMethod.GET)
    public SurveyDetails getQuestions(@RequestParam("survey-id") int surveyId,
                                    HttpServletResponse response){
        LOGGER.debug("Request: Fetching questions for survey: {}", surveyId);

        SurveyDetails surveyDetails = service.getQuestions(surveyId);
        if(surveyDetails==null || surveyDetails.getQuestions()==null || surveyDetails.getQuestions().isEmpty()){
            LOGGER.error("No questions found for survey: {}", surveyId);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        LOGGER.debug("Response: Fetched {} questions for survey: {}", surveyDetails.getQuestions()!=null?surveyDetails.getQuestions().size():0, surveyId);
        return surveyDetails;
    }

    @RequestMapping(value = "/survey/submit", method = RequestMethod.PUT)
    public void submit(@RequestParam("survey-id") String surveyId,
                         @RequestBody SurveyResponse[] payload,
                         HttpServletResponse response){
        LOGGER.debug("Request: Submitting response for survey {}", surveyId);

        if(payload == null || payload.length == 0){
            LOGGER.info("Empty body received for survey response: {}", surveyId);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        try {
            service.submit(Arrays.asList(payload), surveyId);
            response.setStatus(HttpServletResponse.SC_CREATED);
        }catch (Exception e){
            LOGGER.error("Failed to submit response for survey {}", surveyId, e);
            try {
                LOGGER.info("Original Request: {}", new ObjectMapper().writeValueAsString(payload));
            } catch (JsonProcessingException e1) {
                LOGGER.error("Failed to parse input", e);
            }
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        LOGGER.debug("Response: Submitted response for survey  {}", surveyId);
    }
}
