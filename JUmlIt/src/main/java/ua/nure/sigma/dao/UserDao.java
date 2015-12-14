package ua.nure.sigma.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import ua.nure.sigma.db_entities.User;
import ua.nure.sigma.model.UserDetails;

public interface UserDao {
	/**
	 * must return User with id.
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public User addUser(User user) throws SQLException;

	public User updateUser(User user);

	public void deleteStudent(User user) throws SQLException;

	public User getUserById(long id) throws SQLException;

	public List<User> getAllUsers() throws SQLException;
	/**
	 * @param login
	 * @return 0 if user doesn't exist; 1 - user exists
	 */
	public int userExists(String login);
	
	public User getUserForAuth(String login);
	
	public User getUserByLogin(String login);
	/**
	 * 
	 * @param login
	 * @param password
	 * @return user information besides password. 
	 */
	public User getUserByLoginAndPassword(String login, String password);
	
	UserDetails getUserDiagrams(long id);

	Collection<User> getUsersByEmail(String email);
	
}
