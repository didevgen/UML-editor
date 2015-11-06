package ua.nure.sigma.dao;

import java.util.List;

import ua.nure.sigma.db_entities.Diagram;
import ua.nure.sigma.model.DiagramModel;

public interface DiagramDAO {
	
	DiagramModel createDiagram(DiagramModel diagram);
	
	DiagramModel getDiagramById(long id);
	
	void updateDiagram(DiagramModel diagram);
	
	void deleteDiagram(long id);
	
	List<Diagram> getUsersDiagrams(long userId);
	
	List<Diagram> getUsersCollaborationDiagram(long userId);
	
}
