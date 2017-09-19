package com.hegde.survey.api.consumer.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by girish hegde on 19-09-2017.
 * Exception handler for rest exceptions
 */
@ControllerAdvice
public class SurveyExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SurveyExceptionHandler.class);

    @ExceptionHandler
    protected ResponseEntity<Object> handleServiceException(RuntimeException e, WebRequest request) {
        LOGGER.error(e.getMessage(), e);
        Map<String, String> errorResp = new HashMap<>();
        errorResp.put("error-message",e.getMessage());

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if(e instanceof NoDataException){
            httpStatus = HttpStatus.NOT_FOUND;
        }else if (e instanceof InvalidInputException){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        errorResp.put("error-code", Integer.toString(httpStatus.value()));
        return handleExceptionInternal(e, errorResp, headers, httpStatus, request);
    }

}
