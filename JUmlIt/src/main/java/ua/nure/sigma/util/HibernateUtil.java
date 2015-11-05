package ua.nure.sigma.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * класс HibernateUtil, который будет отвечать за обработку данного xml файла и
 * установление соединения с нашей базой данных
 * 
 * @author Евгений
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
