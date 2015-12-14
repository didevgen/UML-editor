package ua.nure.sigma.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import ua.nure.sigma.dao.DiagramDAO;
import ua.nure.sigma.dao.HistoryDAO;
import ua.nure.sigma.dao.UserDao;
import ua.nure.sigma.dao.impl.DiagramDAOImpl;
import ua.nure.sigma.dao.impl.HistoryDAOImpl;
import ua.nure.sigma.dao.impl.UserDAOImpl;
import ua.nure.sigma.db_entities.HistorySession;

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
	
	public void updateSession(HistorySession session) {
		session.setTimeFinish(new Date());
		dao.updateSession(session);
	}
	
}
