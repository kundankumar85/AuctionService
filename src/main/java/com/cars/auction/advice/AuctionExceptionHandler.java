package com.cars.auction.advice;

import com.cars.auction.exceptions.AuctionNotFoundException;
import com.cars.auction.exceptions.BidNotValidException;
import com.cars.auction.exceptions.ItemNotFoundException;
import com.cars.auction.exceptions.MultipleAuctionExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
@Slf4j
public class AuctionExceptionHandler  {

    private static final String MESSAGE = "message";
    private static final String TIME = "time";
    private static final String EXCEPTION_MSG = "Exception found.";

    @ExceptionHandler(value = {ItemNotFoundException.class,MultipleAuctionExistsException.class})
    public ResponseEntity<Object> handleItemNotFoundException( HttpServletRequest req, RuntimeException ex){
        Map<String,Object> error = new HashMap<>();
        error.put(TIME, LocalDateTime.now().toString());
        error.put(MESSAGE,ex.getMessage());
        log.error(EXCEPTION_MSG, ex);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value = {BidNotValidException.class})
    public ResponseEntity<Object> handleBidNotValidException( HttpServletRequest req, BidNotValidException ex){
        Map<String,Object> error = new HashMap<>();
        error.put(TIME, LocalDateTime.now().toString());
        error.put(MESSAGE,ex.getMessage());
        log.error(EXCEPTION_MSG, ex);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }

    @ExceptionHandler(value = { AuctionNotFoundException.class})
    public ResponseEntity<Object> handleAuctionNotFoundException( HttpServletRequest req, RuntimeException ex){
        Map<String,Object> error = new HashMap<>();
        error.put(TIME, LocalDateTime.now().toString());
        error.put(MESSAGE,ex.getMessage());
        log.error(EXCEPTION_MSG, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(HttpServletRequest req,MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        Map<String,String> errors =fieldErrors.stream().collect(Collectors.toMap(FieldError::getField, error->error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(value = { Exception.class})
    public ResponseEntity<Object> handleException( HttpServletRequest req, Exception ex){
        Map<String,Object> error = new HashMap<>();
        error.put(TIME, LocalDateTime.now().toString());
        error.put(MESSAGE,ex.getMessage());
        log.error(EXCEPTION_MSG, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
