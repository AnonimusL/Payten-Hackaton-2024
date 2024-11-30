package com.payten.hacka.auth_service.security.filter;

import com.payten.hacka.auth_service.security.api_response.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ApiErrorResponse {

    private ZonedDateTime time;
    private String message;
    private ErrorCode errorCode;
    public ApiErrorResponse(ZonedDateTime now, String message, ErrorCode errorCode) {
        this.time = now;
        this.message = message;
        this.errorCode = errorCode;
    }
}
