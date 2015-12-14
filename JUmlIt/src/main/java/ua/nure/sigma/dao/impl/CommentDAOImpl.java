package ua.nure.sigma.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ua.nure.sigma.dao.CommentDAO;
import ua.nure.sigma.db_entities.Comment;
import ua.nure.sigma.util.HibernateUtil;

public class CommentDAOImpl implements CommentDAO{

	@Override
	public Comment insertComment(Comment comment) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(comment);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return comment;
	}

	@Override
	public List<Comment> getAllDiagramComments(long diagramId) {
		Session session = null;
		List<Comment> result = new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(Comment.class);
			cr.add(Restrictions.eq("diagram.diagramId", diagramId));
			result = cr.list();
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
