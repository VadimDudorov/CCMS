package org.example.ccms.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class ValidationExceptionCcms extends CcmsException {

    public ValidationExceptionCcms(String message) {
        super(BAD_REQUEST, message);
    }
}
