package ua.nure.sigma.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import ua.nure.sigma.dao.ClassDiagramDAO;
import ua.nure.sigma.db_entities.diagram.Clazz;
import ua.nure.sigma.db_entities.diagram.Field;
import ua.nure.sigma.db_entities.diagram.Method;
import ua.nure.sigma.util.HibernateUtil;

public class ClassDiagramDAOImpl  implements ClassDiagramDAO{

	@Override
	public Clazz insertClazz(Clazz clazz) {
		return (Clazz) insertObject(clazz);
	}

	@Override
	public void updateClazz(Clazz clazz) {
		updateObject(clazz);
	}

	@Override
	public void removeClazz(long clazzId) {
		deleteObject("Clazz", clazzId);
	}

	@Override
	public Clazz getClazz(long clazzId) {
		return (Clazz) getObject(clazzId, Clazz.class);
	}

	@Override
	public Method insertMethod(Method method) {
		return (Method) insertObject(method);
	}

	@Override
	public void updateMethod(Method method) {
		updateObject(method);	
	}

	@Override
	public void removeMethod(long methodId) {
		deleteObject("method", methodId);
	}

	@Override
	public Method getMethod(long methodId) {
		return (Method) getObject(methodId,Method.class);
	}

	@Override
	public Field insertField(Field field) {
		return (Field) insertObject(field);
	}

	@Override
	public void updateField(Field field) {
		updateObject(field);
	}

	@Override
	public void removeField(long fieldId) {
		deleteObject("field", fieldId);
	}

	@Override
	public Field getField(long fieldId) {
		return (Field) getObject(fieldId, Field.class);
	}
	
	private Object insertObject(Object obj) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return obj;
	}
	
	private void updateObject(Object obj) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	private void deleteObject(String tableName, long id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query q = session.createQuery("delete "+tableName+" where class_id = " + id);
			q.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}		
	}
	
	private Object getObject (long id, Class<?> clazzName) {
		Session session = null;
		Object clazz = new Object();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			clazz = session.load(clazzName, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return clazz;
	}

}
