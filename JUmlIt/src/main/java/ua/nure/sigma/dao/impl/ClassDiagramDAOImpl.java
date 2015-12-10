package ua.nure.sigma.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import ua.nure.sigma.dao.ClassDiagramDAO;
import ua.nure.sigma.db_entities.diagram.Clazz;
import ua.nure.sigma.db_entities.diagram.Field;
import ua.nure.sigma.db_entities.diagram.Method;
import ua.nure.sigma.db_entities.relationship.Relationship;
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
		deleteObject("Clazz", "classId", clazzId);
	}

	@Override
	public Clazz getClazz(long clazzId) {
		Clazz clazz = (Clazz) getObject(clazzId, Clazz.class);
		Hibernate.initialize(clazz.getFields());
		Hibernate.initialize(clazz.getMethods());
		Hibernate.initialize(clazz.getPrimaryRelations());
		Hibernate.initialize(clazz.getSecondaryRelations());
		return clazz;
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
		deleteObject("Method", "id", methodId);
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
		deleteObject("Field", "id", fieldId);
	}

	@Override
	public Field getField(long fieldId) {
		return (Field) getObject(fieldId, Field.class);
	}
	
	@Override
	public Relationship insertRelationship(Relationship relation) {
		relation.setPrimaryMember(this.getClazz(relation.getPrimaryMember().getClassId()));
		relation.setSecondaryMember(this.getClazz(relation.getSecondaryMember().getClassId()));
		return (Relationship) insertObject(relation);
	}

	@Override
	public void updateRelationship(Relationship relation) {
		updateObject(relation);		
	}

	@Override
	public void removeRelationship(long relationId) {
		deleteObject("Relationship", "id", relationId);		
	}

	@Override
	public Relationship getRelationship(long relationId) {
		return (Relationship) getObject(relationId, Relationship.class);
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
	
	private void deleteObject(String tableName, String idCol, long id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query q = session.createQuery("delete "+tableName+" where " + idCol + " = " + id);
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

	
}
