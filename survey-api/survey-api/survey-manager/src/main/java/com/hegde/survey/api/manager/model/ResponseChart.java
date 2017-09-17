package com.hegde.survey.api.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by girish hegde on 9/16/17.
 */
public class ResponseChart {
    @JsonProperty("question-id")
    private String questionId;

    @JsonProperty("question")
    private String question;

    @JsonProperty("response-count")
    private int responseCount;

    @JsonProperty("distribution")
    private Map<String, Float> distribution;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getResponseCount() {
        return responseCount;
    }

    public void setResponseCount(int responseCount) {
        this.responseCount = responseCount;
    }

    public Map<String, Float> getDistribution() {
        return distribution;
    }

    public void setDistribution(Map<String, Float> distribution) {
        this.distribution = distribution;
    }
}
