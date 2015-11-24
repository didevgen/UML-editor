package ua.nure.sigma.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ua.nure.sigma.dao.DiagramDAO;
import ua.nure.sigma.dao.UserDao;
import ua.nure.sigma.db_entities.User;
import ua.nure.sigma.model.UserDetails;
import ua.nure.sigma.util.HibernateUtil;;

public class UserDAOImpl implements UserDao {
	public User addUser(User user) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.saveOrUpdate(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return user;
	}

	@Override
	public void updateUser(User user) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "������ I/O", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public User getUserById(long id) throws SQLException {
		Session session = null;
		User user = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			user = (User) session.load(User.class, id);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "������ I/O", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() throws SQLException {
		Session session = null;
		List<User> users = new ArrayList<User>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			users = session.createCriteria(User.class).list();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "������ I/O", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return users;
	}

	@Override
	public void deleteStudent(User user) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "������ I/O", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public int getUserByLogin(String login) {
		Session session = null;
		List<User> users = new ArrayList<User>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			users = session.createCriteria(User.class).add(Restrictions.eq("email", login)).list();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "������ I/O", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return users.size();
	}

	@Override
	public User getUserByLoginAndPassword(String login, String password) {
		Session session = null;
		List<User> users = new ArrayList<User>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			users = session.createCriteria(User.class).add(Restrictions.eq("email", login))
					.add(Restrictions.eq("password", password)).list();
		} catch (Exception e) {
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		try {
			User user = users.get(0);
			user.setPassword("");
			return user;
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public UserDetails getUserDiagrams(long id) {
		Session session = null;
		DiagramDAO diagrDao = new DiagramDAOImpl();
		UserDetails details = new UserDetails();
		try {
			details.setOwnDiagrams(diagrDao.getUsersDiagrams(id));
			details.setCollabDiagrams(diagrDao.getUsersCollaborationDiagram(id));
			
		} catch (Exception e) {
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return details;
	}

	@Override
	public User getUserByEmail(String email) {
		Session session = null;
		List<User> users = new ArrayList<User>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			users = session.createCriteria(User.class).add(Restrictions.ilike("email", email+"%")).list();
		} catch (Exception e) {
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		try {
			User user = users.get(0);
			user.setPassword("");
			return user;
		} catch (Exception ex) {
			return null;
		}
	}

}
