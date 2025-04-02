package org.example.ccms.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class BadRequestExceptionCcms extends CcmsException {

    public BadRequestExceptionCcms(String message) {
        super(BAD_REQUEST, message);
    }
}
