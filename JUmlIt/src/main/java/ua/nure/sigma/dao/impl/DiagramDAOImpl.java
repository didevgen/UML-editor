package ua.nure.sigma.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

import ua.nure.sigma.dao.DiagramDAO;
import ua.nure.sigma.dao.UserDao;
import ua.nure.sigma.db_entities.Collaborator;
import ua.nure.sigma.db_entities.Diagram;
import ua.nure.sigma.db_entities.User;
import ua.nure.sigma.model.DiagramModel;
import ua.nure.sigma.util.HibernateUtil;

public class DiagramDAOImpl implements DiagramDAO {

	@Override
	public DiagramModel createDiagram(DiagramModel diagram) {
		Diagram d = diagram.getDiagram();
		d.setCreationDate(new DateTime(System.currentTimeMillis()));
		d.setLastUpdated(new DateTime(System.currentTimeMillis()));
		d.setOwnerId(diagram.getOwner().getUserId());
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.saveOrUpdate(d);
			session.getTransaction().commit();
			for (int i = 0; i < diagram.getCollaborators().size(); i++) {
				Collaborator col = new Collaborator();
				col.setDiagramId(d.getDiagramId());
				col.setUserId(diagram.getCollaborators().get(i).getUserId());
				session.beginTransaction();
				session.save(col);
				session.getTransaction().commit();
			}
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
	public DiagramModel getDiagramById(long id) {
		Session session = null;
		Diagram diagram = null;
		DiagramModel model = new DiagramModel();
		UserDao dao = new UserDAOImpl();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			diagram = (Diagram) session.load(Diagram.class, id);
			model.setDiagram(diagram);
			model.setOwner(dao.getUserById(diagram.getOwnerId()));
			List<Collaborator> colls = session.createCriteria(Collaborator.class)
					.add(Restrictions.eq("diagramId", id)).list();
			for (Collaborator coll : colls) {
				model.getCollaborators().add(dao.getUserById(coll.getUserId()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "������ I/O", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return model;
	}

	@Override
	public void updateDiagram(DiagramModel diagram) {
		Diagram d = diagram.getDiagram();
		d.setLastUpdated(new DateTime(System.currentTimeMillis()));
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.saveOrUpdate(d);
			session.getTransaction().commit();
			for (int i = 0; i < diagram.getCollaborators().size(); i++) {
				Collaborator col = new Collaborator();
				col.setDiagramId(d.getDiagramId());
				col.setUserId(diagram.getCollaborators().get(i).getUserId());
				session.beginTransaction();
				session.saveOrUpdate(col);
				session.getTransaction().commit();
			}
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
			Query q = session.createQuery("delete Diagram where diagram_id = " + id);
			q.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "������ I/O", JOptionPane.OK_OPTION);
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
			result.addAll(getUserOwnDiagrams(userId,models));
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
		List<Diagram> models = new ArrayList<Diagram>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			models = session.createCriteria(Diagram.class).list();
			result.addAll(getUserCollDiagrams(userId,models));
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
	
	//Additional logic
	private List<Diagram> getUserCollDiagrams(long userId,List<Diagram> models) {
		User user = new User();
		user.setUserId(userId);
		List<Diagram> list = new ArrayList<>();
		for (Diagram diagram : models) {
			if (diagram.getCollaborators().contains(user)) {
				list.add(diagram);
			}
		}
		return list;
	}
	private List<Diagram> getUserOwnDiagrams(long userId,List<Diagram> models) {
		User user = new User();
		user.setUserId(userId);
		List<Diagram> list = new ArrayList<>();
		for (Diagram diagram : models) {
			if (diagram.getOwnerId()==userId) {
				list.add(diagram);
			}
		}
		return list;
	}
	
	

}
