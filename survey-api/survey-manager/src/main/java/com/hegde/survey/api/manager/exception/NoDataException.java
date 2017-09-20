package com.hegde.survey.api.manager.exception;

import org.springframework.dao.EmptyResultDataAccessException;

/**
 * Created by girish hegde on 19-09-2017.
 */
public class NoDataException extends RuntimeException {

    public NoDataException(String message) {
        super(message);
    }

    public NoDataException(String message, EmptyResultDataAccessException e) {
        super(message, e);
    }
}
