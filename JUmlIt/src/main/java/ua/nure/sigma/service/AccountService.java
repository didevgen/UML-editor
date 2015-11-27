package ua.nure.sigma.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ua.nure.sigma.constants.Roles;
import ua.nure.sigma.dao.UserDao;
import ua.nure.sigma.dao.impl.UserDAOImpl;
import ua.nure.sigma.db_entities.User;
import ua.nure.sigma.db_entities.UserRole;
import ua.nure.sigma.exceptions.AccountException;
import ua.nure.sigma.model.UserDetails;
import ua.nure.sigma.util.Encrypter;

public class AccountService {

	private UserDao dao = new UserDAOImpl();

	public AccountService() {
	}

	public boolean checkUserExisting(String login) {
		return dao.userExists(login) >= 1;
	}

	public User insertUser(User user) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, Roles.ROLE_USER));
		user.setUserRoles(userRoles);
		try {
			return dao.addUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AccountException("Cannot save user in the database");
		}
	}

	public User getUser(User userData) {
		User user;
		try {
			user = dao.getUserByLoginAndPassword(userData.getEmail(),
					new Encrypter().encryptIt(userData.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new AccountException("Cannot encrypt user's password");
		}
		if (user == null) {
			throw new AccountException("No such user");
		}
		return user;
	}

	public User getUserById(long id) {
		User user;
		try {
			user = dao.getUserById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AccountException("Cannot retrieve user with id " + id);
		}
		if (user == null) {
			throw new AccountException("No such user");
		}
		return user;
	}

	public UserDetails getUserDiagrams(long id) {
		UserDetails userDetails = dao.getUserDiagrams(id);
		if (userDetails == null) {
			throw new AccountException("No user details for the user id " + id);
		}
		return userDetails;
	}

	public Collection<User> getUsersByPartOfEmail(String email) {
		return dao.getUsersByEmail(email);
	}
	
	public User getUserByLogin(String login) {
		return dao.getUserByLogin(login);
	}

}
