package com.hegde.survey.api.manager.exception;

/**
 * Created by girish hegde on 9/17/17.
 */
public class MissingDataException extends RuntimeException{
    public MissingDataException(){
        super();
    }

    public MissingDataException(String error){
        super(error);
    }

    public MissingDataException(String error, Exception e){
        super(error, e);
    }
}
