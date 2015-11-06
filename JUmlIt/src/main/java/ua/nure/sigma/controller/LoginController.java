package ua.nure.sigma.controller;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import ua.nure.sigma.db_entities.User;
import ua.nure.sigma.service.LoginService;

@Controller
@Scope("session")
public class LoginController {
	
	@Autowired
	private User user;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private LoginService service = new LoginService();
	// @RequestMapping(value = "/account/register", method = RequestMethod.POST)
	// public @ResponseBody User registerUser(HttpServletRequest request) throws
	// SQLException {
	// User user = new Gson().fromJson(request.getParameter("user"),
	// User.class);
	// boolean result = service.checkUserExisting(user.getEmail());
	// if (!result) {
	// return service.insertUser(user);
	// }
	// user.setUserId(-1);
	// return user;
	// }

	@RequestMapping(value = "/account/register", method = RequestMethod.POST)
	public @ResponseBody User  registerUser(@RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("fullname") String fullName) throws SQLException {
		User user = new User();
		user.setEmail(email);
		boolean result = service.checkUserExisting(email);
		if(result) {
			service.returnZeroUser();
		}
		user.setPassword(password);
		user.setFullname(fullName);
		user.setRegistrationDate(new DateTime(System.currentTimeMillis()));
		user.setLastAvailable(new DateTime(System.currentTimeMillis()));
		return service.insertUser(user);
	}

	@RequestMapping(value = "/account/login", method = RequestMethod.POST)
	public  @ResponseBody User loginUser(HttpServletRequest request) throws NoSuchAlgorithmException {
		User userTemp = new Gson().fromJson(request.getParameter("user"), User.class);
		if (userTemp==null) {
			return null;
		}
		user = service.getUser(userTemp);
		return userTemp;
	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.POST)
	public @ResponseBody User getUserById(@PathVariable long id) throws SQLException {
		return service.getUserById(id);
	}

}