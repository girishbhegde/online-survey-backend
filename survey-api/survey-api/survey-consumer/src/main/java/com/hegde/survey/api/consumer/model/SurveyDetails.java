package com.hegde.survey.api.consumer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by girish hegde on 9/17/17.
 */
public class SurveyDetails {
    @JsonProperty("survey-id")
    private String surveyId;

    @JsonProperty("questions")
    List<Question> questions;

    public SurveyDetails(String surveyId, List<Question> questions) {
        this.surveyId = surveyId;
        this.questions = questions;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
