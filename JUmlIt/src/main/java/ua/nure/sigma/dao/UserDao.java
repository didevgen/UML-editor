package ua.nure.sigma.dao;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import ua.nure.sigma.db_entities.User;

public interface UserDao {
	/**
	 * must return User with id.
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public User addUser(User user) throws SQLException;

	public void updateUser(User user) throws SQLException;

	public void deleteStudent(User user) throws SQLException;

	public User getUserById(BigInteger id) throws SQLException;

	public List<User> getAllUsers() throws SQLException;
	/**
	 * @param login
	 * @return 0 if user doesn't exist; 1 - user exists
	 */
	public int getUserByLogin(String login);
	/**
	 * 
	 * @param login
	 * @param password
	 * @return user information besides password. 
	 */
	public User getUserByLoginAndPassword(String login, String password);
	
}
