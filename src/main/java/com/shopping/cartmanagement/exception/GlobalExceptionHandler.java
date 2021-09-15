package com.shopping.cartmanagement.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDto errorDto = new ErrorDto();
        e.getBindingResult().getAllErrors().stream().limit(1).forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorDto.setErrorCode("400");
            errorDto.setFieldName(fieldName);
            errorDto.setMessage(errorMessage);
        });
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(ItemNotFoundException.class)
    protected ResponseEntity<ErrorDto> handleItemNotFoundException(ItemNotFoundException e) {
        ErrorDto dto = new ErrorDto();
        dto.setMessage(e.getMessage());
        dto.setErrorCode("404");
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(CartNotFoundException.class)
    protected ResponseEntity<ErrorDto> handleCartNotFoundException(CartNotFoundException e){
        ErrorDto dto = new ErrorDto();
        dto.setMessage(e.getMessage());
        dto.setErrorCode("404");
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

}
