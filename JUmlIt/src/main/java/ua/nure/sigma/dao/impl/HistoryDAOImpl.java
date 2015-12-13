package ua.nure.sigma.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import ua.nure.sigma.dao.HistoryDAO;
import ua.nure.sigma.db_entities.DiagramHistory;
import ua.nure.sigma.db_entities.HistorySession;
import ua.nure.sigma.util.HibernateUtil;

public class HistoryDAOImpl implements HistoryDAO{

	@Override
	public void insertHistory(DiagramHistory history) {
		insertObject(history);
	}
	@Override
	public HistorySession insertSession(HistorySession ses) {
		return (HistorySession) insertObject(ses);
	}
	@Override
	public List<DiagramHistory> getHistoryByDiagramId(long diagramId) {
		Session session = null;
		List<DiagramHistory> ownDiagrams = new ArrayList<DiagramHistory>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			Query query = session.createQuery("FROM DiagramHistory D WHERE D.diagram.diagramId = :diagramId");
			query.setParameter("diagramId", diagramId);
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
	public List<DiagramHistory> getHistoryByUserId(long userId) {
		Session session = null;
		List<DiagramHistory> ownDiagrams = new ArrayList<DiagramHistory>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			Query query = session.createQuery("FROM DiagramHistory D WHERE D.user.userId = :userId");
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

	private Object insertObject(Object ses) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(ses);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return ses;
	}
	
	private Object getObject (long id, Class<?> clazzName) {
		Session session = null;
		Object clazz = new Object();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			clazz = session.get(clazzName, id);
			session.refresh(clazz);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return clazz;
	}

	@Override
	public HistorySession getSessionById(long sessionId) {
		HistorySession session = (HistorySession) getObject(sessionId, HistorySession.class);
		Hibernate.initialize(session.getActions());
		Hibernate.initialize(session.getDiagram());
		Hibernate.initialize(session.getUser());
		return session;
	}
	@Override
	public void updateSession(HistorySession ses) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(ses);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}		
	}
	

}
