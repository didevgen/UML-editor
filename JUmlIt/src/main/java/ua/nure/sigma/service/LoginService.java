package ua.nure.sigma.service;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import ua.nure.sigma.dao.UserDao;
import ua.nure.sigma.dao.impl.UserDAOImpl;
import ua.nure.sigma.db_entities.User;
import ua.nure.sigma.util.Encrypter;

public class LoginService {

	private UserDao dao = new UserDAOImpl();

	public LoginService() {
	}

	public boolean checkUserExisting(String login) {
		return dao.getUserByLogin(login) == 1;
	}
	
	private User returnZeroUser() {
		User user = new User();
		user.setUserId(BigInteger.valueOf(-1));
		return user;
	}
	public User insertUser(User user) {
		try {
			user.setPassword(new Encrypter().encryptIt(user.getPassword()));
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
			return returnZeroUser();
		}
		try {
			return dao.addUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnZeroUser();
	}

	public User getUser(User user) {
		try {
			return dao.getUserByLoginAndPassword(user.getEmail(), 
					new Encrypter().encryptIt(user.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return returnZeroUser();
		}
	}
	
	public User getUserById(long id) {
		try {
			return dao.getUserById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return returnZeroUser();
		}
	}

}
