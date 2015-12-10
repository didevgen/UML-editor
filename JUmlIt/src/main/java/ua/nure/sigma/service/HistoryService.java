package ua.nure.sigma.service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import ua.nure.sigma.dao.HistoryDAO;
import ua.nure.sigma.dao.impl.HistoryDAOImpl;
import ua.nure.sigma.dao.impl.UserDAOImpl;
import ua.nure.sigma.db_entities.Diagram;
import ua.nure.sigma.db_entities.DiagramHistory;

public class HistoryService {
	public void insertHistory(Principal principal, Diagram d, String action) {
		HistoryDAO dao = new HistoryDAOImpl();
		DiagramHistory history = new DiagramHistory();
		history.setUser(new UserDAOImpl().getUserByLogin(principal.getName()));
		history.setDiagram(d);
		history.setAction(action);
		history.setTimeStamp(new Date());
		dao.insertHistory(history);
	}
	
	public List<DiagramHistory> getHistoryByDiagramId(long diagramId) {
		return null;
	}
	
	public List<DiagramHistory> getHistoryByUserId(long userId) {
		return null;
	}
}
