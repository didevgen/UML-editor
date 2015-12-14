package ua.nure.sigma.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import ua.nure.sigma.dao.DiagramDAO;
import ua.nure.sigma.dao.HistoryDAO;
import ua.nure.sigma.dao.UserDao;
import ua.nure.sigma.dao.impl.DiagramDAOImpl;
import ua.nure.sigma.dao.impl.HistoryDAOImpl;
import ua.nure.sigma.dao.impl.UserDAOImpl;
import ua.nure.sigma.db_entities.HistorySession;
import ua.nure.sigma.db_entities.User;

public class HistoryService {

	private SimpMessagingTemplate messagingTemplate;
	private HistoryDAO dao = new HistoryDAOImpl();
	private DiagramDAO diagrDAO = new DiagramDAOImpl();
	private UserDao userDAO = new UserDAOImpl();
	@Autowired
	public HistoryService(SimpMessagingTemplate template) {
		this.messagingTemplate = template;
	}
	public HistoryService() {
		
	}
	
	public HistorySession insertSession(String userName, long diagramId) {
		HistorySession session = new HistorySession();
		session.setDiagram(diagrDAO.getDiagramById(diagramId));
		session.setUser(userDAO.getUserByLogin(userName));
		session.setTimeStart(new Date());
		return dao.insertSession(session);
	}
	
	public List<HistorySession> updateSession(String userName) {
		User user = userDAO.getUserByLogin(userName);
		List<HistorySession> sessions = dao.getLatestOpenSession(user.getUserId());
		for (HistorySession sess : sessions) {
			sess.setTimeFinish(new Date());
			sess = dao.updateSession(sess);
		}
		return sessions;
	}
	
}
