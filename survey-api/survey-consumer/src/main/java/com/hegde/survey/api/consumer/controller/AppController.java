package com.hegde.survey.api.consumer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hegde.survey.api.consumer.exception.InvalidInputException;
import com.hegde.survey.api.consumer.model.Question;
import com.hegde.survey.api.consumer.model.SurveyDetails;
import com.hegde.survey.api.consumer.model.SurveyResponse;
import com.hegde.survey.api.consumer.service.ResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by ghegde on 6/25/17.
 */
@RestController
@RequestMapping(value = "/survey", produces = {APPLICATION_JSON_VALUE})
public class AppController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private ResponseService service ;

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public SurveyDetails getQuestions(@RequestParam("survey-id") int surveyId){
        LOGGER.debug("Request: Fetching questions for survey: {}", surveyId);
        SurveyDetails surveyDetails = service.getQuestions(surveyId);
        LOGGER.debug("Response: Fetched {} questions for survey: {}", surveyDetails.getQuestions()!=null?surveyDetails.getQuestions().size():0, surveyId);
        return surveyDetails;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public void submit(@RequestParam("survey-id") String surveyId,
                         @RequestBody SurveyResponse[] payload,
                         HttpServletResponse response) throws SQLException {
        LOGGER.debug("Request: Submitting response for survey {}", surveyId);

        if(payload.length == 0){
            LOGGER.info("Empty body received for survey response: {}", surveyId);
            throw new InvalidInputException("Empty surveyResponse. Please send a list of survey responses");
        }
        service.submit(Arrays.asList(payload), surveyId);
        response.setStatus(HttpServletResponse.SC_CREATED);
        LOGGER.debug("Response: Submitted response for survey  {}", surveyId);
    }
}
