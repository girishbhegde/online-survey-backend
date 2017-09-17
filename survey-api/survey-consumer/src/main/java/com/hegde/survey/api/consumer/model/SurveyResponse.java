package com.hegde.survey.api.consumer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by girish hegde on 9/16/17.
 */
@JsonIgnoreProperties(value = "true")
public class SurveyResponse {
    @JsonProperty("question-id")
    private String questionId;

    @JsonProperty("answer-option")
    private String answerOption;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswerOption() {
        return answerOption;
    }

    public void setAnswerOption(String answerOption) {
        this.answerOption = answerOption;
    }
}
