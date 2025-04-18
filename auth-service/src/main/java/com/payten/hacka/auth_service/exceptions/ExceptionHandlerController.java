package com.payten.hacka.auth_service.exceptions;


import com.payten.hacka.auth_service.exceptions.error_response.ErrorResponse;
import com.payten.hacka.auth_service.utils.JsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Date;
import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({
           NotFoundException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleCustomExceptions(Exception exception) {
        return getJson(exception);
    }

    @ExceptionHandler({
            Exception.class,
    })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleOtherExceptions(Exception exception) {
        return getJson(exception);
    }

    private ErrorResponse getJson(Exception exception) {
        Map<String, Object> json = new JsonBuilder()
                .put("timestamp", Date.from(Instant.now()).toString())
                .put("error", exception.getMessage())
                .build();
        return new ErrorResponse(json);
    }

}