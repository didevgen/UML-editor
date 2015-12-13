package ua.nure.sigma.util;

import java.security.Principal;

import ua.nure.sigma.dao.DiagramDAO;
import ua.nure.sigma.dao.UserDao;
import ua.nure.sigma.dao.impl.DiagramDAOImpl;
import ua.nure.sigma.dao.impl.UserDAOImpl;
import ua.nure.sigma.db_entities.Diagram;
import ua.nure.sigma.db_entities.User;

public class UserAccessibility {
	private static UserDao dao = new UserDAOImpl();
	private static DiagramDAO diagramDao = new DiagramDAOImpl();
	public static boolean hasAccess(Principal principal, long diagramId) {
		if (principal == null) {
			return false;	
		}
		User user = dao.getUserByLogin(principal.getName());
		Diagram diagram = diagramDao.getDiagramById(diagramId);
		if (diagram.getOwner().equals(user)) {
			return true;
		}
		if (diagram.getCollaborators().contains(user)) {
			return true;
		}
		return false;
	}
}
