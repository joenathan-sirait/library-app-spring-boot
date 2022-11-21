package net.springBootApp.apiLibrary.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import net.springBootApp.apiLibrary.exception.custom.CustomBadRequestExc;
import net.springBootApp.apiLibrary.exception.custom.CustomNotFoundExc;
import net.springBootApp.apiLibrary.model.dto.response.ErrorMessage;

@ControllerAdvice
public class CustomExcHandler { 
    private ErrorMessage<Object> errorMessage;

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<Object> handleExceptionGlobal(Exception e) {
    errorMessage = new ErrorMessage<Object>(HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now(),
        e.getMessage(), null);
    return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);
  }

  @ExceptionHandler(value = CustomNotFoundExc.class)
  public ResponseEntity<Object> handleNotFoundException(CustomNotFoundExc e) {
    errorMessage = new ErrorMessage<Object>(HttpStatus.NOT_FOUND.value(), LocalDateTime.now(),
        e.getMessage(), null);
    return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);
  }

  @ExceptionHandler(value = CustomBadRequestExc.class)
  public ResponseEntity<Object> handleBadRequestException(CustomBadRequestExc e) {
    errorMessage = new ErrorMessage<Object>(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(),
        e.getMessage(), null);
    return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);
  }
}