package ua.nure.sigma.controller;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.nure.sigma.db_entities.User;
import ua.nure.sigma.messengers.Messenger;
import ua.nure.sigma.service.LoginService;

@Controller
@Scope("session")
public class LoginController {
	
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
	public @ResponseBody Messenger  registerUser(@RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("fullname") String fullName) throws SQLException {
		User user = new User();
		user.setEmail(email);
		boolean result = service.checkUserExisting(email);
		if(result) {
			return new Messenger(false, "such user already exists", null);
		}
		user.setPassword(password);
		user.setFullname(fullName);
		System.out.println(fullName);
		user.setRegistrationDate(new DateTime(System.currentTimeMillis()));
		user.setLastAvailable(new DateTime(System.currentTimeMillis()));
		return service.insertUser(user);
	}

	@RequestMapping(value = "/account/login", method = RequestMethod.POST)
	public  @ResponseBody Messenger loginUser(@RequestParam("email") String email,
			@RequestParam("password") String password,HttpServletRequest request, HttpSession session) throws NoSuchAlgorithmException {
		User userTemp = new User();
		userTemp.setEmail(email);
		userTemp.setPassword(password);
		Messenger user = service.getUser(userTemp);
		session.setAttribute("user", user.getObject());
		return user;
	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.POST)
	public @ResponseBody Messenger getUserById(@PathVariable long id) throws SQLException {
		return service.getUserById(id);
	}
	
	@RequestMapping(value = "/account/{id}/details", method = RequestMethod.POST)
	public @ResponseBody Messenger getUserDetails(@PathVariable long id) throws SQLException {
		return service.getUserById(id);
	}


}
