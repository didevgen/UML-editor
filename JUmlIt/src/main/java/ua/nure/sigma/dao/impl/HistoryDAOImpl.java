package ua.nure.sigma.dao.impl;

import org.hibernate.Session;

import ua.nure.sigma.dao.HistoryDAO;
import ua.nure.sigma.db_entities.HistorySession;
import ua.nure.sigma.util.HibernateUtil;

public class HistoryDAOImpl implements HistoryDAO{

	@Override
	public HistorySession insertSession(HistorySession ses) {
		return (HistorySession) insertObject(ses);
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
		Object object = new Object();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			object = session.get(clazzName, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return object;
	}

	@Override
	public HistorySession getSessionById(long sessionId) {
		HistorySession session = (HistorySession) getObject(sessionId, HistorySession.class);
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
