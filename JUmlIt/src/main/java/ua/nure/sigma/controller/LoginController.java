package ua.nure.sigma.controller;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import ua.nure.sigma.dao.impl.TokenImpl;
import ua.nure.sigma.db_entities.Token;
import ua.nure.sigma.db_entities.User;
import ua.nure.sigma.messengers.Messenger;
import ua.nure.sigma.service.LoginService;
import ua.nure.sigma.util.Encrypter;

@Controller
@Scope("session")
public class LoginController {

	private LoginService service = new LoginService();

	@RequestMapping(value = "/account/register", method = RequestMethod.POST)
	public @ResponseBody Messenger registerUser(HttpServletRequest request) throws SQLException {
		User user =(User) new Gson().fromJson(request.getParameter("root"), Messenger.class).getObject();
		boolean result = service.checkUserExisting(user.getEmail());
		if (result) {
			return new Messenger(false, "such user already exists", null);
		}
		user.setRegistrationDate(new DateTime(System.currentTimeMillis()));
		user.setLastAvailable(new DateTime(System.currentTimeMillis()));
		return service.insertUser(user);
	}

	@RequestMapping(value = "/account/login", method = RequestMethod.POST)
	public @ResponseBody Messenger loginUser(HttpServletRequest request, HttpSession session)
			throws NoSuchAlgorithmException {
		Messenger mes = new Gson().fromJson(request.getParameter("root"), Messenger.class);
		User userTemp = (User) mes.getObject();
		Messenger user = service.getUser(userTemp);
		if (user.isSuccess()) {
			final User myUser = (User) user.getObject();
			new TokenImpl().deleteToken(myUser.getUserId());
			final Token myToken = new TokenImpl().createToken(new Token(
					new Encrypter().encryptIt(String.valueOf(new Random().nextInt(10000))), myUser.getUserId()));
			user.setObject(new Object() {
				public User user = myUser;
				public Token token = myToken;
			});
			session.setAttribute("user", user.getObject());
		}

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
	
	@RequestMapping(value = "/account/email", method = RequestMethod.POST)
	public @ResponseBody Messenger getUserByEmail(HttpServletRequest request) throws SQLException {
		User user =(User) new Gson().fromJson(request.getParameter("root"), Messenger.class).getObject();
		return service.getUserByPartOfEmail(user.getEmail());
	}

}
