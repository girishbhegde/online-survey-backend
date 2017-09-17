package com.hegde.survey.api.manager.service;

import com.hegde.survey.api.manager.dao.SurveyManagerDao;
import com.hegde.survey.api.manager.dao.UserDaoImpl;
import com.hegde.survey.api.manager.model.Question;
import com.hegde.survey.api.manager.model.QuestionResponse;
import com.hegde.survey.api.manager.model.ResponseChart;
import com.hegde.survey.api.manager.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by girish hegde on 9/16/17.
 */
@Service
public class SurveyAdminServiceImpl implements SurveyAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SurveyAdminServiceImpl.class);

    @Autowired
    private UserDaoImpl userDao;


    @Override
    public void addUser(User user) throws UnsupportedEncodingException {
        LOGGER.debug("Request to add new user {}", user.getUsername());
        userDao.insertNewUser(user);
        LOGGER.debug("Processed request to add new user {}", user.getUsername());
    }

    @Override
    public User getUserDetails(String username) {
        LOGGER.debug("Request to get user info for username: {}", username);
        User user = userDao.getUserDetails(username);
        LOGGER.debug("Processed request to get user info for username: {}", username);
        return user;
    }
}
