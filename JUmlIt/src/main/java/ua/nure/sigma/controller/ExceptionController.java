package ua.nure.sigma.controller;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ua.nure.sigma.enums.ErrorObject;

@ControllerAdvice
public class ExceptionController {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public @ResponseBody ErrorObject handleInternalServerError(HttpServletRequest req, Exception e) {
		e.printStackTrace();
		return new ErrorObject(e.getMessage());
	}
}
