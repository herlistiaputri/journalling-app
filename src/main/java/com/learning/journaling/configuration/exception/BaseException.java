package com.learning.journaling.configuration.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{
    private static final long serialVersionUID = 8429125181915626226L;
    private Integer status;
    private String message;
    private String errorMessage;

    public BaseException(Integer status, String message, String errorMessage) {
        this.status = status;
        this.message = message;
        this.errorMessage = errorMessage;
    }
}
