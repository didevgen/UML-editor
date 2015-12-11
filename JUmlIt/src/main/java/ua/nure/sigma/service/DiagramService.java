package ua.nure.sigma.service;

import ua.nure.sigma.dao.DiagramDAO;
import ua.nure.sigma.dao.impl.DiagramDAOImpl;
import ua.nure.sigma.db_entities.Diagram;

public class DiagramService {
	
	private DiagramDAO dao = new DiagramDAOImpl();
	
	public Diagram createDiagram(Diagram diagram) {
		return dao.createDiagram(diagram);
	}
	
	public Diagram getDiagramById(long id){
		return dao.getDiagramById(id);
	}
	
	public void updateDiagram(Diagram diagram) {
		dao.updateDiagram(diagram);
	}
	
	public void deleteDiagram(long id){
		dao.deleteDiagram(id);
	}
}
