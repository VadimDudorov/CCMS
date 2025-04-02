package org.example.ccms.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CcmsException extends RuntimeException {

    private final HttpStatus httpStatus;

    public CcmsException(HttpStatus httpStatus, String message){
        super(message);
        this.httpStatus = httpStatus;
    }
}
