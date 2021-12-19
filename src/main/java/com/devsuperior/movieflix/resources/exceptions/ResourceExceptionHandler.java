package com.devsuperior.movieflix.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.movieflix.services.exceptions.ForbiddenException;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<OAuthCustomError> unauthorizedException(UnauthorizedException exception, HttpServletRequest request){
		OAuthCustomError err = new OAuthCustomError("Unauthorized", exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}
	
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<OAuthCustomError> forbiddenException(ForbiddenException exception, HttpServletRequest request){
		OAuthCustomError err = new OAuthCustomError("Forbidden", exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
}
