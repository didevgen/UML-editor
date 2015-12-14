package ua.nure.sigma.dao;

import ua.nure.sigma.db_entities.HistorySession;

public interface HistoryDAO {
	
	HistorySession insertSession(HistorySession session);
	
	HistorySession getSessionById(long sessionId);
	
	void updateSession(HistorySession session);
}
