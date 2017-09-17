package com.hegde.survey.api.manager.dao;

import com.hegde.survey.api.manager.model.User;

import java.io.UnsupportedEncodingException;

/**
 * Created by girish hegde on 9/16/17.
 */
public interface UserDao {
    /**
     * Add a new User into DB
     * @param user
     * @throws UnsupportedEncodingException
     */
    void insertNewUser(User user) throws UnsupportedEncodingException;

    /**
     * Get User details from DB
     * @param username
     * @return
     */
    User getUserDetails(String username);
}
