package ua.nure.sigma.dao;

import java.util.List;

import ua.nure.sigma.db_entities.DiagramHistory;

public interface HistoryDAO {
	void insertHistory(DiagramHistory history);
	
	List<DiagramHistory> getHistoryByDiagramId(long diagramId);
	
	List<DiagramHistory> getHistoryByUserId(long userId);
	
}
