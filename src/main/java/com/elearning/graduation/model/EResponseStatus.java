package com.elearning.graduation.model;

import org.springframework.http.HttpStatus;

public enum EResponseStatus {

    CREATED(HttpStatus.CREATED),
    ACCEPTED(HttpStatus.OK),
    INVALID(HttpStatus.BAD_REQUEST),
    BAD_REQUEST(HttpStatus.BAD_REQUEST),
    NOT_FOUND(HttpStatus.NOT_FOUND),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED),
    FORBIDDEN(HttpStatus.FORBIDDEN),
    CONFLICT(HttpStatus.CONFLICT);

    private final HttpStatus httpStatus;

    EResponseStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}