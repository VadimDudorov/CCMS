package org.example.ccms.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class InternalExceptionCcms extends CcmsException {

    public InternalExceptionCcms(String message) {
        super(INTERNAL_SERVER_ERROR, message);
    }
}
