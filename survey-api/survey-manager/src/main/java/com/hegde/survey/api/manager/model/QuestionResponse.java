package com.hegde.survey.api.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Created by girish hegde on 9/16/17.
 */
@JsonIgnoreProperties(value = "true")
public class QuestionResponse {
    @JsonProperty("queston-id")
    private String questionId;

    @JsonProperty("question")
    private String questionDesc;

    @JsonProperty("responses")
    private List<String> responses;

    public QuestionResponse(){
        super();
    }

    public QuestionResponse(String questionId, String questionDesc, List<String> responses) {
        super();
        this.questionId = questionId;
        this.questionDesc = questionDesc;
        this.responses = responses;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

    public List<String> getResponses() {
        return responses;
    }

    public void setResponses(List<String> responses) {
        this.responses = responses;
    }
}
