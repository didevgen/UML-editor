package ua.nure.sigma.controller;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.nure.sigma.dao.UserDao;
import ua.nure.sigma.dao.impl.UserDAOImpl;
import ua.nure.sigma.db_entities.User;
import ua.nure.sigma.util.Encrypter;
import ua.nure.sigma.validator.Validator;

@Controller
public class LoginController {
	private UserDao dao = new UserDAOImpl();
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/account/register", method = RequestMethod.POST)
	public @ResponseBody User registerUser(@RequestBody User user) throws SQLException {
		Validator validator = new Validator();
		validator.validateUser(user);
		return dao.addUser(user);
	}
	
	@RequestMapping(value = "/account/login", method = RequestMethod.POST)
	public @ResponseBody User loginUser(@RequestBody User user) throws NoSuchAlgorithmException {
		return dao.getUserByLoginAndPassword(user.getEmail(), new Encrypter().encryptIt(user.getPassword()));
	}
	
	@RequestMapping(value = "/account/{id}", method = RequestMethod.POST)
	public @ResponseBody User getUserById(@PathVariable long id) throws SQLException {
		return dao.getUserById(BigInteger.valueOf(id));
	}

}
