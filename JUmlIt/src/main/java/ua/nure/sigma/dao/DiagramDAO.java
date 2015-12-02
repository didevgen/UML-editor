package ua.nure.sigma.dao;

import java.util.List;

import ua.nure.sigma.db_entities.Diagram;

public interface DiagramDAO {
	
	Diagram createDiagram(Diagram diagram);
	
	Diagram getDiagramById(long id);
	
	void updateDiagram(Diagram diagram);
	
	void deleteDiagram(long id);
	
	List<Diagram> getUsersDiagrams(long userId);
	
	List<Diagram> getUsersCollaborationDiagram(long userId);
	
}
