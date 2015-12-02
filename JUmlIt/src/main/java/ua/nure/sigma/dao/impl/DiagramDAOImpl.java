package ua.nure.sigma.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.joda.time.DateTime;

import ua.nure.sigma.dao.DiagramDAO;
import ua.nure.sigma.dao.UserDao;
import ua.nure.sigma.db_entities.Diagram;
import ua.nure.sigma.db_entities.User;
import ua.nure.sigma.util.HibernateUtil;

public class DiagramDAOImpl implements DiagramDAO {

	@Override
	public Diagram createDiagram(Diagram diagram) {
		diagram.setCreationDate(new DateTime(System.currentTimeMillis()));
		diagram.setLastUpdated(new DateTime(System.currentTimeMillis()));
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Set<User> fullUsers = new HashSet<>();
			for (User user : diagram.getCollaborators()) {
				fullUsers.add((User) session.load(User.class, user.getUserId()));
			}
			diagram.setCollaborators(fullUsers);
			session.save(diagram);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return diagram;
	}

	@Override
	public Diagram getDiagramById(long id) {
		Session session = null;
		Diagram diagram = null;
		UserDao dao = new UserDAOImpl();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			diagram = (Diagram) session.load(Diagram.class, id);
			Hibernate.initialize(diagram.getClasses());
			Hibernate.initialize(diagram.getCollaborators());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "������ I/O",
					JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return diagram;
	}

	@Override
	public void updateDiagram(Diagram diagram) {
		diagram.setLastUpdated(new DateTime(System.currentTimeMillis()));
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.saveOrUpdate(diagram);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public void deleteDiagram(long id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query q = session.createQuery("delete Diagram where diagram_id = "
					+ id);
			q.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "������ I/O",
					JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public List<Diagram> getUsersDiagrams(long userId) {
		Session session = null;
		List<Diagram> models = new ArrayList<Diagram>();
		List<Diagram> result = new ArrayList<Diagram>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			models = session.createCriteria(Diagram.class).list();
			result.addAll(getUserOwnDiagrams(userId, models));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return result;
	}

	@Override
	public List<Diagram> getUsersCollaborationDiagram(long userId) {
		Session session = null;
		List<Diagram> result = new ArrayList<Diagram>();
		List<Diagram> diagrams = new ArrayList<Diagram>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			diagrams = session.createCriteria(Diagram.class).list();
			result.addAll(getUserCollDiagrams(userId, diagrams));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return result;
	}

	// Additional logic
	private List<Diagram> getUserCollDiagrams(long userId, List<Diagram> models) {
		User user = new User();
		user.setUserId(userId);
		List<Diagram> list = new ArrayList<>();
		for (Diagram diagram : models) {
			if (diagram.getCollaborators().contains(user)) {
				diagram.getCollaborators().forEach(
						collaborator -> collaborator.setPassword(""));
				list.add(diagram);
			}
		}
		return list;
	}

	private List<Diagram> getUserOwnDiagrams(long userId, List<Diagram> models) {
		User user = new User();
		user.setUserId(userId);
		List<Diagram> list = new ArrayList<>();
		for (Diagram diagram : models) {
			if (diagram.getOwner().getUserId() == userId) {
				diagram.getCollaborators().forEach(
						collaborator -> collaborator.setPassword(""));
				list.add(diagram);
			}
		}
		return list;
	}

}
