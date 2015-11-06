package ua.nure.sigma.dao;

import ua.nure.sigma.model.DiagramModel;

public interface DiagramDAO {
	
	DiagramModel createDiagram(DiagramModel diagram);
	
	DiagramModel getDiagramById(long id);
	
	void updateDiagram(DiagramModel diagram);
	
	void deleteDiagram(long id);
	
}
