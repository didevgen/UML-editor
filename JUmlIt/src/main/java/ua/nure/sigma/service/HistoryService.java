package ua.nure.sigma.service;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import ua.nure.sigma.dao.HistoryDAO;
import ua.nure.sigma.dao.impl.HistoryDAOImpl;
import ua.nure.sigma.dao.impl.UserDAOImpl;
import ua.nure.sigma.db_entities.Diagram;
import ua.nure.sigma.db_entities.DiagramHistory;
import ua.nure.sigma.db_entities.User;

public class HistoryService {

	private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	public HistoryService(SimpMessagingTemplate template) {
		this.messagingTemplate = template;
	}

	public void insertHistory(Principal principal, Diagram diagram, String action) {
		HistoryDAO dao = new HistoryDAOImpl();
		DiagramHistory history = new DiagramHistory();
		User user = new UserDAOImpl().getUserByLogin(principal.getName());
		history.setUser(user);
		history.setDiagram(diagram);
		history.setAction(action);
		history.setTimeStamp(new Date());
		dao.insertHistory(history);
		Map<String, Object> headers = new HashMap<>();
		headers.put("fromUserId", user.getUserId());
		messagingTemplate.convertAndSend("/topic/diagram/" + diagram.getDiagramId(), diagram, headers);
	}
	
	public List<DiagramHistory> getHistoryByDiagramId(long diagramId) {
		return null;
	}
	
	public List<DiagramHistory> getHistoryByUserId(long userId) {
		return null;
	}
}
