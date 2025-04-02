package org.example.ccms.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class NotFoundExceptionCcms extends CcmsException {

    public NotFoundExceptionCcms(String message) {
        super(NOT_FOUND, message);
    }
}
