package cinema.controllers;

import cinema.exceptions.BusinessException;
import cinema.models.ErrorDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ErrorControllerAdvice {
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler
    ErrorDTO alreadyPurchasedHandler(BusinessException ex){
        return new ErrorDTO(ex.getMessage());
    }
}
