package org.example.ccms.exception;

import static org.springframework.http.HttpStatus.CONFLICT;

public class ConflictExceptionCcms extends CcmsException {

    public ConflictExceptionCcms(String message) {
        super(CONFLICT, message);
    }
}
