package com.hegde.survey.api.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by girish hegde on 9/16/17.
 */
@JsonIgnoreProperties(value = "true")
public class ResponseItem {
    @JsonProperty("queston-id")
    private String questionId;

    @JsonProperty("question")
    private String questionDesc;

    @JsonProperty("response")
    private String response;

    public ResponseItem(){
        super();
    }

    public ResponseItem(String questionId, String questionDesc, String response) {
        super();
        this.questionId = questionId;
        this.questionDesc = questionDesc;
        this.response = response;
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

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
