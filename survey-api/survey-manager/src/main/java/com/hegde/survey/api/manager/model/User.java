package com.hegde.survey.api.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by girish hegde on 9/16/17.
 */
public class User {
    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("created-on")
    private String createOn;

    public User(){
        super();
    };

    public User(String username, String email, String name, String createOn) {
        super();
        this.username = username;
        this.email = email;
        this.name = name;
        this.createOn = createOn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }
}
