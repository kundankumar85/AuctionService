package com.cars.auction.advice;

import com.cars.auction.exceptions.ItemNotFoundException;
import com.cars.auction.exceptions.MultipleAuctionExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class AuctionExceptionHandler  {

    @ExceptionHandler(value = {ItemNotFoundException.class,MultipleAuctionExistsException.class})
    public ResponseEntity<Object> handleItemNotFoundException( HttpServletRequest req, ItemNotFoundException ex){
        Map<String,Object> error = new HashMap<>();
        error.put("time", LocalDateTime.now().toString());
        error.put("message",ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(HttpServletRequest req,MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        Map<String,String> errors =fieldErrors.stream().collect(Collectors.toMap(FieldError::getField, error->error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

}
