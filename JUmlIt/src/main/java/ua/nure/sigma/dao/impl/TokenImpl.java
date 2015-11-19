package ua.nure.sigma.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ua.nure.sigma.dao.TokenDAO;
import ua.nure.sigma.db_entities.Token;
import ua.nure.sigma.util.HibernateUtil;

public class TokenImpl implements TokenDAO {

	@Override
	public Token createToken(Token token) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.saveOrUpdate(token);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return token;
	}

	@Override
	public boolean checkTokenExisting(Token token) {
		Session session = null;
		List<Token> users = new ArrayList<Token>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			users = session.createCriteria(Token.class).add(Restrictions.eq("tokenValue", token.getToken()))
					.add(Restrictions.eq("userId", token.getUserId())).list();
		} catch (Exception e) {
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return users.size()!=0;
	}

	@Override
	public void updateToken(Token token) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(token);
			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public void deleteToken(Token token) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(token);
			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	@Override
	public void deleteToken(long userId) {
		Session session = null;
		try {
			Token token = getTokenByUserId(userId);
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(token);
			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}		
	}
	
	private Token getTokenByUserId(long userId) {
		Session session = null;
		List<Token> tokens = new ArrayList<Token>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tokens = session.createCriteria(Token.class).add(Restrictions.eq("userId", userId)).list();
		} catch (Exception e) {
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return tokens.get(0);
	}

}
