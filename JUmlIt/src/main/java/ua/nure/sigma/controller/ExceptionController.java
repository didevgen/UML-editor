package ua.nure.sigma.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.nure.sigma.enums.ErrorObject;

@Controller
public class ExceptionController {

	@RequestMapping(value = "/account/error", method = RequestMethod.POST)
	public @ResponseBody ErrorObject registerUser() throws SQLException {
		return new ErrorObject("Такой логин уже существует");
	}
}
