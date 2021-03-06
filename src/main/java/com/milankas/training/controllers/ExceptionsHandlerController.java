package com.milankas.training.controllers;

import java.sql.SQLException;
import java.util.Date;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.milankas.training.exceptions.ErrorDetails;
import com.milankas.training.exceptions.ResourceNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandlerController {

	@ExceptionHandler(JsonMappingException.class)
	public ResponseEntity<?> handleConverterErrors(JsonMappingException ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Invalid Param", ex.getLocalizedMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Item not found", ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Invalid item", ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
		String errorMessage = "something went wrong";
		String[] arrayException = ex.getMessage().split(";");
		if (arrayException.length > 1) {
			errorMessage = arrayException[arrayException.length-1];
			errorMessage = errorMessage.substring(errorMessage.indexOf("[") + 1, errorMessage.indexOf("]"));
			System.out.print( errorMessage );
		}
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation failed", errorMessage);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> IllegalArgumentExceptionHandler(IllegalArgumentException ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Invalid parameter", ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public  ResponseEntity<?> SQLExceptionHandler(ConstraintViolationException ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "SQL Error", ex.getCause().getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

}
