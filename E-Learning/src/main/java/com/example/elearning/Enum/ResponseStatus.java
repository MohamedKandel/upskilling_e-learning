package com.example.elearning.Enum;

public enum ResponseStatus {
    SUCCESS,
    CREATED,
    ACCEPTED,
    CONFLICT, //409: When there's a duplication so to speak: Email already exists 409
    INTERNAL_SERVER_ERROR,
    BAD_REQUEST,
    NOT_FOUND,
    OK,
    UNAUTHORIZED   //401
}
