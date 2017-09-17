package com.hegde.survey.api.consumer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by girish hegde on 9/16/17.
 */
@JsonIgnoreProperties(value = "true")
public class Question {
    @JsonProperty("queston-id")
    private String questionId;

    @JsonProperty("question")
    private String questionDesc;

    @JsonProperty("options")
    private Map<String,String> options;

    public Question(){
        super();
    }

    public Question(String questionId, String questionDesc, Map<String,String> options) {
        super();
        this.questionId = questionId;
        this.questionDesc = questionDesc;
        this.options = options;
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

    public Map<String,String> getOptions() {
        return options;
    }

    public void setOptions(Map<String,String> options) {
        this.options = options;
    }
}
