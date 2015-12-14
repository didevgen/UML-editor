package ua.nure.sigma.dao;

import java.util.List;

import ua.nure.sigma.db_entities.HistorySession;

public interface HistoryDAO {
	
	HistorySession insertSession(HistorySession session);
	
	HistorySession getSessionById(long sessionId);
	
	HistorySession updateSession(HistorySession session);
	
	List<HistorySession> getLatestOpenSession(long userId);
	
	List<HistorySession> getAllUserSessions(long userId);
	
	List<HistorySession> getAllDiagramSessions(long deiagramId);
}
