package hu.bme.sch.bss.webcentral.controller;

import hu.bme.sch.bss.webcentral.controller.domain.ErrorDetails;

import java.util.Date;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerMapping;

@ControllerAdvice
public final class ExceptionHandlerController {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorDetails> noSuchElementExceptionHandler(final Exception exception, final HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(), HttpStatus.NOT_FOUND, exception.getMessage(), request.getRemoteAddr(),
                (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE)
                );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

}
