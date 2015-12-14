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
			session.flush();
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
			session.refresh(diagram);
			Hibernate.initialize(diagram.getClasses());
			Hibernate.initialize(diagram.getCollaborators());
			Hibernate.initialize(diagram.getRelationships());
			Hibernate.initialize(diagram.getHistory());
			Hibernate.initialize(diagram.getComments());
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
	public synchronized void updateDiagram(Diagram diagram) {
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
		List<Diagram> ownDiagrams = new ArrayList<Diagram>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			Query query = session.createQuery("FROM Diagram D WHERE D.owner.userId = :userId");
			query.setParameter("userId", userId);
			ownDiagrams.addAll(query.list());
			
			Hibernate.initialize(ownDiagrams);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return ownDiagrams;
	}

	@Override
	public List<Diagram> getUsersCollaborationDiagram(long userId) {
		Session session = null;
		List<Diagram> collabDiagrams = new ArrayList<Diagram>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			Query query = session.createQuery(
					"FROM Diagram D "
					+ " WHERE :userId in (SELECT userId FROM D.collaborators)");
			query.setParameter("userId", userId);
			collabDiagrams.addAll(query.list());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return collabDiagrams;
	}

}
