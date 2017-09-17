package com.hegde.survey.api.manager.controller;

import com.hegde.survey.api.manager.exception.MissingDataException;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ghegde on 6/25/17.
 */
@RestController
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
    public List<Question> getQuestions(@RequestParam("survey-id") String surveyId,
                                       HttpServletResponse response){
        List<Question> questions = managerService.getQuestions(surveyId);
        if(questions==null || questions.isEmpty()){
            LOGGER.error("Couldn't find any questions for survey: {}", surveyId);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return questions;
    }

    @RequestMapping(value = "/manager/questions", method = RequestMethod.PUT)
    public void addQuestions(@RequestParam("survey-id") String surveyId,
                                       @RequestBody Question[] payload,
                                       HttpServletResponse response){
        if(payload==null || payload.length == 0){
            LOGGER.info("Empty body received for add questions for survey: {}", surveyId);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        try {
            managerService.addQuestions(surveyId, Arrays.asList(payload));
            response.setStatus(HttpServletResponse.SC_CREATED);
        }catch (Exception e){
            LOGGER.error("Failed to add questions for survey {}", surveyId, e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/manager/question", method = RequestMethod.POST)
    public void updateQuestion(@RequestParam("question-id") String questionId,
                             @RequestBody Question payload,
                             HttpServletResponse response){
        if(payload==null){
            LOGGER.info("Empty body received for update question for question id: {}", questionId);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        try {
            managerService.updateQuestion(questionId, payload);
            response.setStatus(HttpServletResponse.SC_CREATED);
        }catch (Exception e){
            LOGGER.error("Failed to update question for survey {}", questionId, e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/manager/question", method = RequestMethod.DELETE)
    public void deleteQuestion(@RequestParam("question-id") String questionId,
                               HttpServletResponse response){
        if(questionId==null){
            LOGGER.info("Empty question-id received for delete question for question id: {}", questionId);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        try {
            managerService.deleteQuestion(questionId);
            response.setStatus(HttpServletResponse.SC_CREATED);
        }catch (Exception e){
            LOGGER.error("Failed to update question for survey {}", questionId, e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/manager/responses", method = RequestMethod.GET)
    public QuestionResponse getResponse(@RequestParam("question-id") String questionId,
                               HttpServletResponse response){
        if(questionId==null){
            LOGGER.info("Empty question-id received for get response for survey: {}", questionId);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        QuestionResponse questionResponse = null;
        try {
            questionResponse = managerService.getResponses(questionId);
        }catch (MissingDataException e){
            LOGGER.error("No response found for question id: {}", questionId, e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }catch (Exception e){
            LOGGER.error("Failed to update question for survey {}", questionId, e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return questionResponse;
    }

    @RequestMapping(value = "/manager/responses/analyze", method = RequestMethod.GET)
    public ResponseChart getResponseChart(@RequestParam("question-id") String questionId,
                                        HttpServletResponse response){
        if(questionId==null){
            LOGGER.info("Empty question-id received for get response analysis for survey: {}", questionId);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        ResponseChart responseChart = null;
        try {
            responseChart = managerService.getResponseChart(questionId);
        }catch (MissingDataException e){
            LOGGER.error("No response found for question id: {}", questionId, e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }catch (Exception e){
            LOGGER.error("Failed to update question for survey {}", questionId, e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return responseChart;
    }

    @RequestMapping(value = "/manager/survey", method = RequestMethod.PUT)
    public Map<String, String> addSurvey(@RequestParam("username") String userId,
                             @RequestBody Question[] payload,
                             HttpServletResponse response){
        int res = 0;
        if(payload==null || payload.length == 0){
            LOGGER.info("Empty body received for create survey for user: {}", userId);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        try {
            res = managerService.createSurvey(Arrays.asList(payload), userId);
        }catch (Exception e){
            LOGGER.error("Failed to add questions for survey {}", userId, e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("survey-id",Integer.toString(res));
        return responseMap;
    }

    @RequestMapping(value = "/admin/user", method = RequestMethod.PUT)
    public void addUser(@RequestBody User payload,
                        HttpServletResponse response){
        if(payload==null){
            LOGGER.info("Empty body received for add user");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        try {
            adminService.addUser(payload);
            response.setStatus(HttpServletResponse.SC_CREATED);
        }catch (Exception e){
            LOGGER.error("Failed to add new user: {}", payload.getUsername(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin/user", method = RequestMethod.GET)
    public User getUserDetails(@RequestParam String username,
                        HttpServletResponse response){
        User user = null;
        if(username==null){
            LOGGER.info("Missing username for get user");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        try {
            user = adminService.getUserDetails(username);
        }catch (Exception e){
            LOGGER.error("Failed to get user details for username {}", username, e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return user;
    }
}
