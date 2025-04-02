package org.example.ccms.handler;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.example.ccms.exception.BadRequestExceptionCcms;
import org.example.ccms.exception.CcmsException;
import org.example.ccms.exception.InternalExceptionCcms;
import org.example.ccms.exception.NotFoundExceptionCcms;
import org.example.ccms.exception.ValidationExceptionCcms;
import org.example.ccms.model.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ValidationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Throwable.class)
    public ResponseEntity<ResponseDto> handleException(Throwable ex) {

        CcmsException e;
        int status = INTERNAL_SERVER_ERROR.value();

        if (ex instanceof CcmsException) {
            e = (CcmsException) ex;

            if (ex instanceof NotFoundExceptionCcms) {
                status = NOT_FOUND.value();
            } else if (ex instanceof BadRequestExceptionCcms) {
                status = BAD_REQUEST.value();
            }
        } else if (ex instanceof MethodArgumentNotValidException ||
                ex instanceof MethodArgumentTypeMismatchException ||
                ex instanceof MissingRequestValueException ||
                ex instanceof ValidationException ||
                ex.getCause() instanceof MismatchedInputException) {
            e = new ValidationExceptionCcms(ex.getMessage());
            status = BAD_REQUEST.value();
            } else {
            e = new InternalExceptionCcms(ex.getMessage());
        }

        HttpStatus httpStatus = e.getHttpStatus();
        ResponseDto response = ResponseDto.builder().status((long) status).message(e.getMessage()).build();

        return new ResponseEntity<>(response, httpStatus);
    }
}
