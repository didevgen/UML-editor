package ua.nure.sigma.service;

import ua.nure.sigma.dao.UserDao;
import ua.nure.sigma.dao.impl.UserDAOImpl;

public class LoginService {

	private UserDao dao = new UserDAOImpl();
	
	public LoginService() {
	}
	
	public boolean checkUserExisting(String login) {
		return dao.getUserByLogin(login)==1;
	}
	

}
