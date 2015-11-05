package ua.nure.sigma.controller;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ua.nure.sigma.db_entities.User;
import ua.nure.sigma.enums.ErrorObject;

@Controller
@EnableWebMvc
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/account/register", method = RequestMethod.POST)
	public @ResponseBody ErrorObject registerUser(@RequestBody User user) throws SQLException {
		return new ErrorObject("Huy");
	}
	@RequestMapping(value = "/account/logout", method = RequestMethod.GET)
	public @ResponseBody ErrorObject returnSomething() throws NoSuchAlgorithmException {
		return new ErrorObject("Huyyy");
	}

	@RequestMapping(value = "/account/login", method = RequestMethod.POST)
	public @ResponseBody User loginUser() throws NoSuchAlgorithmException {
		return null;
	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.POST)
	public @ResponseBody User getUserById(@PathVariable long id) throws SQLException {
		return null;
	}

}
