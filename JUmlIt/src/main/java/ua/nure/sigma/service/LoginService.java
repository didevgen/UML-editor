package ua.nure.sigma.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import ua.nure.sigma.dao.UserDao;
import ua.nure.sigma.dao.impl.UserDAOImpl;
import ua.nure.sigma.db_entities.User;
import ua.nure.sigma.messengers.Messenger;
import ua.nure.sigma.model.UserDetails;
import ua.nure.sigma.util.Encrypter;

public class LoginService {

	private UserDao dao = new UserDAOImpl();

	public LoginService() {
	}

	public boolean checkUserExisting(String login) {
		return dao.getUserByLogin(login) >= 1;
	}
	
	public Messenger insertUser(User user) {
		try {
			user.setPassword(new Encrypter().encryptIt(user.getPassword()));
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
			return new Messenger(false, "error", null);
		}
		try {
			return new Messenger(true, "ok",  dao.addUser(user));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Messenger(false, "error", null);
	}

	public Messenger getUser(User user) {
		try {
			User u = dao.getUserByLoginAndPassword(user.getEmail(), 
					new Encrypter().encryptIt(user.getPassword()));
			return new Messenger(u!=null, "", u);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new Messenger(false, "error", null);
		}
	}
	
	public Messenger getUserById(long id) {
		try {
			User u = dao.getUserById(id);
			return new Messenger(u!=null, "", u);
		} catch (SQLException e) {
			e.printStackTrace();
			return new Messenger(false, "error", null);
		}
	}
	
	public UserDetails getUserDiagrams(long id) {
		return dao.getUserDiagrams(id);
	}

}
