package ua.nure.sigma.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import ua.nure.sigma.dao.HistoryDAO;
import ua.nure.sigma.db_entities.Diagram;
import ua.nure.sigma.db_entities.DiagramHistory;
import ua.nure.sigma.util.HibernateUtil;

public class HistoryDAOImpl implements HistoryDAO{

	@Override
	public void insertHistory(DiagramHistory history) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(history);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
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

}
