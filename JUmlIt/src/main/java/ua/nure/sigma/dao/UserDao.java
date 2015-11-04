package ua.nure.sigma.dao;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import ua.nure.sigma.db_entities.User;

public interface UserDao {
	public void addUser(User user) throws SQLException;
	public void updateUser(User user) throws SQLException;
	public void deleteStudent(User user) throws SQLException;
    public User getUserById(BigInteger id) throws SQLException;    
    public List<User> getAllUsers() throws SQLException;    
}
