package com.roi.demos.wfproject.exception;

import com.roi.demos.wfproject.domain.RequestError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestfulExceptions {

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public RequestError runtimeException(RuntimeException ex) {
        // log exception to svc
        return RequestError.builder()
                .code(HttpStatus.NOT_ACCEPTABLE.value())
                .message(ex.getMessage())
                .build();
    }
}
