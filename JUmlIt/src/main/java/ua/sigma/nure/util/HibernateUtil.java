package ua.sigma.nure.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * ����� HibernateUtil, ������� ����� �������� �� ��������� ������� xml ����� �
 * ������������ ���������� � ����� ����� ������
 * 
 * @author �������
 *
 */
@SuppressWarnings("deprecation")
public class HibernateUtil {
	private static SessionFactory sessionFactory = null;

	static {
		try {
			// creates the session factory from hibernate.cfg.xml
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
