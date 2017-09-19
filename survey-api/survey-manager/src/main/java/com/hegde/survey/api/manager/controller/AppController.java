package com.hegde.survey.api.manager.controller;

import com.hegde.survey.api.manager.exception.InvalidDataException;
import com.hegde.survey.api.manager.model.Question;
import com.hegde.survey.api.manager.model.QuestionResponse;
import com.hegde.survey.api.manager.model.ResponseChart;
import com.hegde.survey.api.manager.model.User;
import com.hegde.survey.api.manager.service.SurveyAdminService;
import com.hegde.survey.api.manager.service.SurveyManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by ghegde on 6/25/17.
 */
@RestController
@RequestMapping(produces = {APPLICATION_JSON_VALUE})
public class AppController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);
    @Autowired
    private SurveyManagerService managerService;

    @Autowired
    private SurveyAdminService adminService ;


    /**
     * This method will give list of questions in a survey
     * @param surveyId
     * @return
     */
        @RequestMapping(value = "/manager/questions", method = RequestMethod.GET)
    public List<Question> getQuestions(@RequestParam(value = "survey-id") String surveyId){
        return managerService.getQuestions(surveyId);
    }

    @RequestMapping(value = "/manager/questions", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public void addQuestions(@RequestParam(value = "survey-id") String surveyId,
                                       @RequestBody Question[] payload,
                                       HttpServletResponse response){
        if(payload.length == 0){
            LOGGER.info("Empty body received for add questions for survey: " + surveyId);
            throw new InvalidDataException("Empty Body. Please provide a list of questions");
        }
        managerService.addQuestions(surveyId, Arrays.asList(payload));
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @RequestMapping(value = "/manager/question", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public void updateQuestion(@RequestParam(value = "question-id") String questionId,
                             @RequestBody Question payload,
                             HttpServletResponse response) throws SQLException {
        managerService.updateQuestion(questionId, payload);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @RequestMapping(value = "/manager/question", method = RequestMethod.DELETE)
    public void deleteQuestion(@RequestParam(value = "question-id") String questionId,
                               HttpServletResponse response){
        managerService.deleteQuestion(questionId);
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
    }

    @RequestMapping(value = "/manager/responses", method = RequestMethod.GET)
    public QuestionResponse getResponse(@RequestParam(value = "question-id") String questionId){
        return managerService.getResponses(questionId);
    }

    @RequestMapping(value = "/manager/responses/analyze", method = RequestMethod.GET)
    public ResponseChart getResponseChart(@RequestParam(value = "question-id") String questionId){
        return managerService.getResponseChart(questionId);
    }

    @RequestMapping(value = "/manager/survey", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public Map<String, String> createSurvey(@RequestParam(value = "username") String username,
                                            @RequestBody Question[] payload,
                                            HttpServletResponse response){
        if(payload.length == 0){
            LOGGER.info("Empty body received for create survey for user: {}", username);
            throw new InvalidDataException("Empty list. Please provide list of questions in body");
        }
        int res = managerService.createSurvey(Arrays.asList(payload), username);
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("survey-id",Integer.toString(res));
        response.setStatus(HttpServletResponse.SC_CREATED);
        return responseMap;
    }

    @RequestMapping(value = "/admin/user", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody User payload,
                        HttpServletResponse response){
        adminService.addUser(payload);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @RequestMapping(value = "/admin/user", method = RequestMethod.GET)
    public User getUserDetails(@RequestParam(value = "username") String username){
        return adminService.getUserDetails(username);
    }
}
