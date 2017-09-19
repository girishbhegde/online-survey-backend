package com.hegde.survey.api.manager.service;

import com.hegde.survey.api.manager.model.Question;
import com.hegde.survey.api.manager.model.QuestionResponse;
import com.hegde.survey.api.manager.model.ResponseChart;
import com.hegde.survey.api.manager.model.User;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by girish hegde on 9/16/17.
 */
public interface SurveyAdminService {
    /**
     * Add a new user
     * @param user
     * @return
     */
    void addUser(User user);

    /**
     * Get all non sensitive User details
     * @param username
     */
    User getUserDetails(String username);
}
