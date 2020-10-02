package com.prasetyanurangga.ServerMe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class UserExpectionController : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [(UserNotFoundException::class)])
    fun handleUserNotFound(ex: UserNotFoundException,request: WebRequest): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }
}
