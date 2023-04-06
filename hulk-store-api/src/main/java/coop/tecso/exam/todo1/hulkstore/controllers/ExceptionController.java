package coop.tecso.exam.todo1.hulkstore.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import coop.tecso.exam.todo1.hulkstore.domain.service.exceptions.BusinessException;
import coop.tecso.exam.todo1.hulkstore.domain.validator.InvalidFieldException;

@ControllerAdvice

public class ExceptionController {
	
	@ExceptionHandler(value = InvalidFieldException.class)
	public ResponseEntity<Map<String, String>> invalidFieldException(InvalidFieldException exp, WebRequest request) {
		
		Map<String, String> response = getResponse("INVALID_FIELD", exp.getMessage());
		
		return ResponseEntity.unprocessableEntity().body(response);
		
	}
	
	@ExceptionHandler(value = BusinessException.class)
	public ResponseEntity<Map<String, String>> businessException(BusinessException exp, WebRequest request) {
		
		Map<String, String> response = getResponse(exp.getCode(), exp.getMessage());
		
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		
	}
	
	@ExceptionHandler(value = BadCredentialsException.class)
	public ResponseEntity<Map<String, String>> badCredentialsException(BadCredentialsException exp, WebRequest request) {
		
		Map<String, String> response = getResponse("BAD_CREDENTIALS", "Wrong username or password");
		
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		
	}
	
	private Map<String, String> getResponse(String code, String message) {
		Map<String, String> response = new HashMap<>();
		response.put("code", code);
		response.put("message", message);
		return response;
	}

}
