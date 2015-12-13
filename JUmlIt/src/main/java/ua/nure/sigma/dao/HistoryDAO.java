package ua.nure.sigma.dao;

import java.util.List;

import ua.nure.sigma.db_entities.DiagramHistory;
import ua.nure.sigma.db_entities.HistorySession;

public interface HistoryDAO {
	void insertHistory(DiagramHistory history);
	
	HistorySession insertSession(HistorySession session);
	
	List<DiagramHistory> getHistoryByDiagramId(long diagramId);
	
	List<DiagramHistory> getHistoryByUserId(long userId);
	
	HistorySession getSessionById(long sessionId);
	
	void updateSession(HistorySession session);
}
