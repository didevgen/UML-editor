package ua.nure.sigma.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.nure.sigma.controller.requests.RegisterRequest;
import ua.nure.sigma.db_entities.User;
import ua.nure.sigma.exceptions.AccountException;
import ua.nure.sigma.model.UserDetails;
import ua.nure.sigma.service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	private HttpSession session;

	private AccountService service = new AccountService();

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public User registerUser(@RequestBody RegisterRequest request) {
		boolean result = service.checkUserExisting(request.getEmail());
		if (result) {
			throw new AccountException("User with such email already exists.");
		}
		User user = new User(request.getFullname(), request.getEmail(), request.getPassword());
		user.setRegistrationDate(new DateTime(System.currentTimeMillis()));
		user.setLastAvailable(new DateTime(System.currentTimeMillis()));
		return service.insertUser(user);
	}

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public User getAccount(ModelMap model, Principal principal) {
		session.setAttribute("sessionId", 1L);
		return service.getUserByLogin(principal.getName());
	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.POST)
	public User getUserById(@PathVariable long id) throws SQLException {
		return service.getUserById(id);
	}

	@RequestMapping(value = "/account/{id}/details", method = RequestMethod.POST)
	public UserDetails getUserDetails(@PathVariable long id) throws SQLException {
		return service.getUserDiagrams(id);
	}
	
	@RequestMapping(value = "/account/email/{email}", method = RequestMethod.POST)
	public Collection<User> getUserByEmail(@PathVariable("email") String email) throws SQLException {
		return service.getUsersByPartOfEmail(email);
	}
	
	@RequestMapping(value = "/account/{id}/update", method = RequestMethod.POST)
	public User updateUser(@RequestBody User user,@PathVariable long id) throws SQLException {
		User prince = service.getUserById(id);
		
		if (service.getUserByLogin(user.getEmail())!=null) {
				throw new AccountException("User with such email already exists.");
		}
		if (!user.getEmail().equals("")) {
			prince.setEmail(user.getEmail());
		}
		prince.setFullname(user.getFullname());
		return service.updateUser(prince);
	}

}
