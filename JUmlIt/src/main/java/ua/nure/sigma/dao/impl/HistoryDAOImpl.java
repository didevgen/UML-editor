package ua.nure.sigma.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ua.nure.sigma.dao.HistoryDAO;
import ua.nure.sigma.db_entities.HistorySession;
import ua.nure.sigma.util.HibernateUtil;

public class HistoryDAOImpl implements HistoryDAO {

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

	private Object getObject(long id, Class<?> clazzName) {
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
	public HistorySession updateSession(HistorySession ses) {
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
		return ses;
	}

	@Override
	public List<HistorySession> getLatestOpenSession(long userId) {
		Session session = null;
		List<HistorySession> result = new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(HistorySession.class);
			cr.add(Restrictions.eq("user.userId", userId));
			cr.add(Restrictions.isNull("timeFinish"));
			result = cr.list();
//			for (Object obj : res) {
//				result.add((HistorySession) obj);
//			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return result;
	}

}
