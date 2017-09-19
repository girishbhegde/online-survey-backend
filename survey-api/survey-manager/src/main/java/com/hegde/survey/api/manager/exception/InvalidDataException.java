package com.hegde.survey.api.manager.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by girish hegde on 19-09-2017.
 */
public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message){
        super(message);
    }

    public InvalidDataException(Exception e){
        super(e);
    }

    public InvalidDataException(String message, JsonProcessingException e) {
        super(message,e);
    }
}
